package com.revelcare.task;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;

import com.revelcare.listener.ApiResponse;
import com.revelcare.utills.ProgressDialog;
import com.revelcare.utills.WebserviceHandler;

public class UpdatePatientINFOTask extends AsyncTask<String, Void, String> {
	private Context context;
	private ApiResponse response;
	private ProgressDialog dialog;
	private String message;
	
	
	public UpdatePatientINFOTask(Context context,ApiResponse response , String message) {
		this.context = context;
		this.response = response;
		this.message = message;
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = new ProgressDialog(context,message);
		dialog.show();
	}

	@Override
	protected String doInBackground(String... params) {
		String reponse = null;
		ArrayList<String> patient_info = new ArrayList<>();
		patient_info.add(params[1]);
		patient_info.add(params[2]);
		patient_info.add(params[3]);
		patient_info.add(params[4]);
		patient_info.add(params[5]);
		patient_info.add(params[6]);
		
		WebserviceHandler handler = new WebserviceHandler(context);
		try {
		 reponse = handler.post(params[0], patient_info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponse;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if(dialog != null){
			dialog.dismiss();
		}
		if(result != null){
			response.onSuccess(result);
		}else{
			response.onFailure();
		}
	}
}
