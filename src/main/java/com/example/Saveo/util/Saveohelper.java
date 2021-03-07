package com.example.Saveo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.Saveo.model.CsvFileAttributesModel;

public class Saveohelper {
	public static String TYPE = "text/csv";

	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static List<CsvFileAttributesModel> csvToAttributeModel(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
	      List<CsvFileAttributesModel> attributeList = new ArrayList<CsvFileAttributesModel>();
	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	      for (CSVRecord csvRecord : csvRecords) {
	    	  CsvFileAttributesModel attributeModel = new CsvFileAttributesModel();
	    	  attributeModel.setCBatchNo(csvRecord.get("c_batch_no"));
	    	  attributeModel.setCManufacturer(csvRecord.get("c_manufacturer"));
	    	  attributeModel.setCName(csvRecord.get("c_name"));
	    	  attributeModel.setCPackaging(csvRecord.get("c_packaging"));
	    	  attributeModel.setCSchemes(csvRecord.get("c_schemes"));
	    	  attributeModel.setCUniqueCode(csvRecord.get("c_unique_code"));
	    	  attributeModel.setDExpiryDate(csvRecord.get("d_expiry_date"));
	    	  attributeModel.setHsnCode(csvRecord.get("hsn_code"));
	    	  attributeModel.setNBalanceQty(csvRecord.get("n_balance_qty"));
	    	  attributeModel.setNMrp(csvRecord.get("n_mrp"));

	    	  attributeList.add(attributeModel);
	      }
	      return attributeList;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }

}
