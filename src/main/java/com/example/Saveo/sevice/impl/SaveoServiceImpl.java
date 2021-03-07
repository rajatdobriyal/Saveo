package com.example.Saveo.sevice.impl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Saveo.dao.MedicineDetailDao;
import com.example.Saveo.model.CsvFileAttributesModel;
import com.example.Saveo.model.PlaceOrderModel;
import com.example.Saveo.service.SaveoService;
import com.example.Saveo.util.Saveohelper;

@Service
public class SaveoServiceImpl implements SaveoService {

	@Autowired
	private MedicineDetailDao medicinesDetailDao;

	@Override
	public String save(MultipartFile file) throws Exception {
		try {
			List<CsvFileAttributesModel> csvAttributeList = Saveohelper.csvToAttributeModel(file.getInputStream());
			String message = medicinesDetailDao.saveDetailToDb(csvAttributeList);
			return message;
		} catch (IOException e) {
			e.printStackTrace();
			return "Exception while processing the data : " + e.getMessage();
		}

	}

	@Override
	public List<String> searchMedicine(String medicineToSearch) throws Exception {
		return medicinesDetailDao.searchMedicineInDb(medicineToSearch);
	}

	@Override
	public List<CsvFileAttributesModel> getMedicineDetail(String medicineToSearch) throws Exception {
		return medicinesDetailDao.getmedicineDetail(medicineToSearch);
	}

	@Override
	public String placeOrder(List<PlaceOrderModel> placeOrderList) {
		try {
			for (PlaceOrderModel placeOrderModel : placeOrderList) {
				String orderResult = medicinesDetailDao.placeOderFromDb(placeOrderModel);
				if (orderResult.equalsIgnoreCase("")) {
					return "No Data found in db for one of the order you placed.";
				}
			}
			return UUID.randomUUID().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Exception while processing the data" + e.getMessage();
		}
	}

}
