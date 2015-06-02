package com.revelcare.listener;

public interface ApiResponse {
	//when response is positive 
	public void onSuccess(String response);

	// called when response from api is negative
	public void onFailure();
}
