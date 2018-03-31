package com.uninsured.data.entity;

import java.io.Serializable;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="admin")
@TypeAlias("admin")
public class Admin implements Serializable{

	private static final long serialVersionUID = -7232969043892893402L;
	
	@Field("username")
	private String username;
	
	@Field("password")
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
	
}
