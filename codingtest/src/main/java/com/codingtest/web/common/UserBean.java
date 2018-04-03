package com.codingtest.web.common;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;

import com.codingtest.data.entity.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@ManagedBean(name = "userbean")
public class UserBean {

	private String sku;

	private String name;

	private String location;

	private String department;

	private String category;

	private String subcategory;
	
	@ManagedProperty(value="#{json}")
	JSONBean json;


	public JSONBean getJson() {
		return json;
	}

	public void setJson(JSONBean json) {
		this.json = json;
	}

	private static final ArrayList<User> orderList = new ArrayList<User>();

	public ArrayList<User> getOrderList() {
		return orderList;
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

	public String addAction() throws ClientProtocolException,
	IOException {
		//System.out.println("name111:" + orderitem.getName());
		
		User orderitem = new User(this.sku, this.name, this.location,
				this.department, this.category, this.subcategory);
		orderList.add(orderitem);

		System.out.println("name:" +getName());
		String newUrl = "http://localhost:8080//codingtestrestapi/api/v1/location/newsku/"+getSku()+"/"+getName()+"/"+getLocation()+"/"+getDepartment()+"/"+getCategory()+"/"+getSubcategory();
		
		URL url = new URL(newUrl);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		OutputStreamWriter out = new OutputStreamWriter(
		    httpCon.getOutputStream());
		out.write("Resource content");
		out.close();
		httpCon.getInputStream();
		json.init();
		clear();
		return null;
	}
	
	public void clear(){
	    setSku(null);
	    setName(null);
	    setLocation(null);
	    setDepartment(null);
	    setCategory(null);
	    setSubcategory(null);
	}


}
