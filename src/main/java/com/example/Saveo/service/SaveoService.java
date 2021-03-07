package com.example.Saveo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.Saveo.model.CsvFileAttributesModel;
import com.example.Saveo.model.PlaceOrderModel;

public interface SaveoService {

	public String save(MultipartFile file) throws Exception;

	public List<String> searchMedicine(String medicineToSearch) throws Exception;

	public List<CsvFileAttributesModel> getMedicineDetail(String medicineToSearch) throws Exception;

	public String placeOrder(List<PlaceOrderModel> placeOrder);

}
