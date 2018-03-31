package com.codingtest.web.common;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.codingtest.data.entity.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@ManagedBean(name = "loginbean")
public class AdminLoginBean {
	
	private NavigationController nc = new NavigationController();
	
	private String username;

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login(ActionEvent event) {
		FacesMessage message = null;
		
		boolean loggedIn = false;
		System.out.println("username and password:"+getUsername()+getPassword());
		String url = "http://localhost:8080/uninsuredrest/login/"+getUsername()+"/"+getPassword();

		try {
			Document data = Jsoup.connect(url).ignoreContentType(true).get();
			String json = data.select("body").text();
			if (username != null && json.equals("true") && password != null) {
				loggedIn = true;
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome",
						username);
				moveToadminpage();
				 FacesContext context = FacesContext.getCurrentInstance();
				    try {
						context.getExternalContext().redirect("admin.xhtml");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else {
				loggedIn = false;
				message = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Loggin Error", "Invalid credentials");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	/*	if (username != null && username.equals("admin") && password != null
				&& password.equals("admin")) {
			loggedIn = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome",
					username);
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Loggin Error", "Invalid credentials");
		}*/

		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("loggedIn", message);
        		// PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
	}
	
	public String moveToadminpage(){
	    return "admin.xhtml"; //outcome
	}
}
