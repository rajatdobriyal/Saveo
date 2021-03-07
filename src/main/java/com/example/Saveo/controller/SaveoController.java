package com.example.Saveo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Saveo.model.CsvFileAttributesModel;
import com.example.Saveo.model.PlaceOrderModel;
import com.example.Saveo.service.SaveoService;
import com.example.Saveo.util.Saveohelper;

@RestController
public class SaveoController {
	@Autowired
	private SaveoService saveoService;

	//Service to upload the csv file and store the attributes in database.
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (Saveohelper.hasCSVFormat(file)) {
			try {
				System.out.println("yes");
				message = saveoService.save(file);

				return ResponseEntity.status(HttpStatus.OK).body(message);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}

	//Service to search medicine name based on the text searched.
	@GetMapping("/searchMedicine")
	public ResponseEntity<List<String>> searchMedicine(@RequestParam("searchMedicineName") String medicineToSearch) {
		try {
			List<String> searchMedicine = saveoService.searchMedicine(medicineToSearch);

			return ResponseEntity.status(HttpStatus.OK).body(searchMedicine);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}

	//Service to search the medicine detail on basis of medicine uniqueId.
	@GetMapping("/getMedicineDetails")
	public ResponseEntity<List<CsvFileAttributesModel>> getMedicineDetail(
			@RequestParam("searchMedicineDetail") String medicineToSearch) {
		try {
			List<CsvFileAttributesModel> medicineDetailList = saveoService.getMedicineDetail(medicineToSearch);
			return ResponseEntity.status(HttpStatus.OK).body(medicineDetailList);

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}

	}
	
	//Service to place order on basis of c_unique_id,quantity,c_name
	@PostMapping("/placeorder")
	public ResponseEntity<String> placeOrder(@RequestBody List<PlaceOrderModel> placeOrdermodelList){
		String message = saveoService.placeOrder(placeOrdermodelList);
		return ResponseEntity.status(HttpStatus.OK).body(message);
		
	}

}
