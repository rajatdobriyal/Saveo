package com.example.Saveo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.Saveo.dao.MedicineDetailDao;
import com.example.Saveo.model.CsvFileAttributesModel;
import com.example.Saveo.model.PlaceOrderModel;

@Repository
public class MedicineDetailDaoImpl implements MedicineDetailDao {

	@Autowired
	private DataSource dataSource;

	@Value("${insert.query}")
	private String insertQuery;

	@Value("${search.medicine.query}")
	private String searchMedicineNameQuery;

	@Value("${search.medicine.detail.query}")
	private String searchMedicineDetailQuery;
	
	@Value("${place.order.query}")
	private String placeOrderQuery;
	

	@Override
	public String saveDetailToDb(List<CsvFileAttributesModel> csvAttributeList) throws Exception {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(insertQuery);) {
			for (CsvFileAttributesModel csvFileAttributesModel : csvAttributeList) {
				prepareStatement.setString(1, csvFileAttributesModel.getCName());
				prepareStatement.setString(2, csvFileAttributesModel.getCBatchNo());
				prepareStatement.setString(3, csvFileAttributesModel.getDExpiryDate());
				prepareStatement.setString(4, csvFileAttributesModel.getNBalanceQty());
				prepareStatement.setString(5, csvFileAttributesModel.getCPackaging());
				prepareStatement.setString(6, csvFileAttributesModel.getCUniqueCode());
				prepareStatement.setString(7, csvFileAttributesModel.getCSchemes());
				prepareStatement.setString(8, csvFileAttributesModel.getNMrp());
				prepareStatement.setString(9, csvFileAttributesModel.getCManufacturer());
				prepareStatement.setString(10, csvFileAttributesModel.getHsnCode());
				prepareStatement.addBatch();
			}
			prepareStatement.executeBatch();
			return "Data Uploaded to Db successfully.";

		} catch (Exception ex) {
			ex.printStackTrace();
			return "Exception while storing the data in db : " + ex.getMessage();
		}
	}

	@Override
	public List<String> searchMedicineInDb(String medicineToSearch) throws Exception {
		medicineToSearch = "%" + medicineToSearch + "%";
		List<String> medicineList = new ArrayList<String>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(searchMedicineNameQuery);) {
			prepareStatement.setString(1, medicineToSearch);
			prepareStatement.execute();

			ResultSet resultSet = prepareStatement.getResultSet();
			while (resultSet.next()) {
				medicineList.add(resultSet.getString("c_name"));
			}
			return medicineList;
		} catch (Exception ex) {
			ex.printStackTrace();
			return medicineList;
		}

	}

	@Override
	public List<CsvFileAttributesModel> getmedicineDetail(String medicineToSearch) throws Exception {
		List<CsvFileAttributesModel> medicineDetailList = new ArrayList<CsvFileAttributesModel>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(searchMedicineDetailQuery);) {
			prepareStatement.setString(1, medicineToSearch);
			prepareStatement.execute();

			ResultSet resultSet = prepareStatement.getResultSet();
			while (resultSet.next()) {
				CsvFileAttributesModel attributesModel = new CsvFileAttributesModel();
				attributesModel.setCBatchNo(resultSet.getString("c_batch_no"));
				attributesModel.setCManufacturer(resultSet.getString("c_manufacturer"));
				attributesModel.setCName(resultSet.getString("c_name"));
				attributesModel.setCPackaging(resultSet.getString("c_packaging"));
				attributesModel.setCSchemes(resultSet.getString("c_schemes"));
				attributesModel.setCUniqueCode(resultSet.getString("c_unique_code"));
				attributesModel.setDExpiryDate(resultSet.getString("d_expiry_date"));
				attributesModel.setHsnCode(resultSet.getString("hsn_code"));
				attributesModel.setNBalanceQty(resultSet.getString("n_balance_qty"));
				attributesModel.setNMrp(resultSet.getString("n_mrp"));
				medicineDetailList.add(attributesModel);
			}
			return medicineDetailList;
		} catch (Exception ex) {
			ex.printStackTrace();
			return medicineDetailList;
		}
	}

	@Override
	public String placeOderFromDb(PlaceOrderModel placeOrderModel) throws Exception {
		String message="";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(placeOrderQuery);) {
			prepareStatement.setString(1, placeOrderModel.getC_name());
			prepareStatement.setString(2, String.valueOf(placeOrderModel.getQuantity()));
			prepareStatement.setString(3, placeOrderModel.getC_unique_id());
			prepareStatement.execute();
			
			ResultSet resultSet = prepareStatement.getResultSet();
			while(resultSet.next()) {
				message="Order Successfully placed.";
			}
			return message;
		}catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

}
