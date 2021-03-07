package com.example.Saveo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "medicines_detail")
public class MedicinesDetailEntity {
	
	@Id
	private String c_unique_code;
	private String c_name;
	private String c_batch_no;
	private String d_expiry_date;
	private String n_balance_qty;
	private String c_packaging;
	private String c_schemes;
	private String n_mrp;
	private String c_manufacturer;
	private String hsn_code;

}
