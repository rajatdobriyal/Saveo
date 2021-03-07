package com.example.Saveo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class CsvFileAttributesModel {

	@JsonProperty("name")
	private String cName;
	@JsonProperty("batchno")
	private String cBatchNo;
	@JsonProperty("expiry")
	private String dExpiryDate;
	@JsonProperty("total stock quantity")
	private String nBalanceQty;
	@JsonProperty("packaging")
	private String cPackaging;
	@JsonProperty("unique_code")
	private String cUniqueCode;
	@JsonProperty("schemes")
	private String cSchemes;
	@JsonProperty("mrp")
	private String nMrp;
	@JsonProperty("manufacturer")
	private String cManufacturer;
	@JsonProperty("hsn no")
	private String hsnCode;
}
