package com.example.Saveo.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PlaceOrderModel {
	
	@NotBlank(message = "c_name cannot be null/blank")
	private String c_name;
	@NotBlank(message = "quantity cannot be null/blank")
	private int quantity;
	@NotBlank(message = "c_unique_id cannot be null/blank")
	private String c_unique_id;

}
