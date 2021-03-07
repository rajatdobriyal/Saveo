package com.example.Saveo.dao;

import java.util.List;

import com.example.Saveo.model.CsvFileAttributesModel;
import com.example.Saveo.model.PlaceOrderModel;

public interface MedicineDetailDao {
	public String saveDetailToDb(List<CsvFileAttributesModel> csvAttributeList) throws Exception;

	public List<String> searchMedicineInDb(String medicineToSearch) throws Exception;

	public List<CsvFileAttributesModel> getmedicineDetail(String medicineToSearch) throws Exception;

	public String placeOderFromDb(PlaceOrderModel placeOrderModel) throws Exception;

}
