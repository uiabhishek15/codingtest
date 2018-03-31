package com.uninsured.data.entity;

import java.io.Serializable;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="sku")
@TypeAlias("sku")
public class SKU implements Serializable{

	private static final long serialVersionUID = 1103350484894685667L;
	
	@Field("SKU")
	private String sku;	
	
	@Field("NAME")
	private String name;
	
	@Field("LOCATION")
	private String location;
	
	@Field("DEPARTMENT")
	private String department;
	
	@Field("CATEGORY")
	private String category;
	
	@Field("SUBCATEGORY")
	private String subcategory;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	
	
}
