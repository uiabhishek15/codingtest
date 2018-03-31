package com.uninsured.rest.responses;

import java.util.List;

import com.uninsured.data.entity.SKU;
import com.uninsured.data.entity.SKU;


public class MultipleUserResponse {
	
	private boolean success;
	private List<SKU> tutors;
	
	public MultipleUserResponse(boolean success,List<SKU> tutors){
		this.success = success;
		this.setTutors(tutors);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<SKU> getTutors() {
		return tutors;
	}

	public void setTutors(List<SKU> tutors) {
		this.tutors = tutors;
	}

}
