package com.codingtest.data.entity;

import java.io.Serializable;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

public class User implements Serializable {

	private static final long serialVersionUID = -7823225398591728685L;

	private String sku;

	private String name;

	private String location;

	private String department;

	private String category;

	private String subcategory;

	public User() {

	}

	public User(String sku, String name, String location, String department,
			String category, String subcategory) {

		this.sku = sku;
		this.name = name;
		this.location = location;
		this.department = department;
		this.category = category;
		this.subcategory = subcategory;
	}

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
