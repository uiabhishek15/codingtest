package com.codingtest.web.common;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.codingtest.data.entity.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@ManagedBean(name = "json")
@ViewScoped
public class JSONBean implements Serializable {

	private static final long serialVersionUID = 25924094432794817L;

	private List<User> users;

	private List<User> filterusers;

	private String key;
	
	public void onEdit(RowEditEvent event) {
		 FacesMessage msg = new FacesMessage("Car Edited", ((User) event.getObject()).getName());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	       
	        String updateurl = "http://localhost:8080//codingtestrestapi/api/v1/location/updatedata/"+ ((User) event.getObject()).getName() +"/"+((User) event.getObject()).getLocation()+"/"+((User) event.getObject()).getDepartment()+"/"+((User) event.getObject()).getCategory()+"/"+((User) event.getObject()).getSubcategory();
	       
	        try {
	        	//encodedUrl = URLEncoder.encode(updateurl, "UTF-8");
	        	//System.out.println("encoded url"+java.net.URLEncoder.encode(updateurl, "UTF-8").replaceAll("\\+", "%20"));
	        	Jsoup.connect(updateurl).header("Content-Type","application/x-www-form-urlencoded;charset=UTF-8")
	        	.post();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	public void onCancel(RowEditEvent event1) {
		 FacesMessage msg = new FacesMessage("Edit Cancelled", ((User) event1.getObject()).getName());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getFilterusers() {
		return filterusers;
	}

	public void setFilterusers(List<User> filterusers) {
		this.filterusers = filterusers;
	}
	

/*	public void onrowEdit(RowEditEvent event) {
		
        FacesMessage msg = new FacesMessage("Car Edited", ((User) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
       
        String updateurl = "http://localhost:8080//codingtestrestapi/updatedata/"+ ((User) event.getObject()).getName() +"/"+((User) event.getObject()).getLocation()+"/"+((User) event.getObject()).getDepartment()+"/"+((User) event.getObject()).getCategory()+"/"+((User) event.getObject()).getSubcategory();
        try {
        	Jsoup.connect(updateurl).post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
     
    public void onrowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((User) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }*/

	@PostConstruct
	public void init() {

		String url = "http://localhost:8080//codingtestrestapi/api/v1/location";

		String login_url = "http://localhost:8080//codingtestrestapi/login";

		String filterurl = "http://localhost:8080//codingtestrestapi/api/v1/location/"
				+ key + "/department";
		try {

			Response res = Jsoup.connect(login_url).ignoreContentType(true)
					.data("username", "user").data("password", "userPass")
					.method(Method.POST).execute();

		
			Document doc = res.parse();

			// Keep logged in
			Map<String, String> cookies = res.cookies();

			Document data = Jsoup.connect(url).ignoreContentType(true)
					.cookies(cookies).get();

			// Document data =
			// Jsoup.connect(url).ignoreContentType(true).header("Authorization",
			// "Basic " + base64login).get();
			String json = data.select("body").text();
			users = new Gson().fromJson(json, new TypeToken<List<User>>() {
			}.getType());
			
			
			Document filterdata = Jsoup.connect(filterurl)
					.ignoreContentType(true).get();
			String filterjson = filterdata.select("body").text();
			filterusers = new Gson().fromJson(filterjson,
					new TypeToken<List<User>>() {
					}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
     
}
