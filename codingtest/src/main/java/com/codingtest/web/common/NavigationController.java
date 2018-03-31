package com.codingtest.web.common;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationbean")
@RequestScoped
public class NavigationController implements Serializable{

	private static final long serialVersionUID = 8003516584772053257L;

	public String moveToadminpage(){
	    return "admin"; //outcome
	}
}
