package com.uninsured.rest.responses;

import com.uninsured.data.entity.SKU;



public class SingleUserResponse {
	
	private boolean success;
	private SKU tutor;
	
	public SingleUserResponse(boolean success,SKU tutor){
		this.success = success;
		this.setTutor(tutor);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public SKU getTutor() {
		return tutor;
	}

	public void setTutor(SKU tutor) {
		this.tutor = tutor;
	}


}
