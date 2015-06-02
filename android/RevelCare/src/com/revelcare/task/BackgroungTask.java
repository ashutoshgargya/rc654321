package com.revelcare.task;

import android.content.Context;
import android.os.AsyncTask;

import com.revelcare.listener.ApiResponse;
import com.revelcare.utills.ProgressDialog;
import com.revelcare.utills.WebserviceHandler;

public class BackgroungTask extends AsyncTask<String, Void, String> {
	private Context context;
	private ApiResponse response;
	private ProgressDialog dialog;
	private String message;
private boolean ispost;
	public BackgroungTask(Context context,ApiResponse response , String message, boolean ispost) {
		this.context = context;
		this.response = response;
		this.message = message;
		this.ispost = ispost;
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
		WebserviceHandler handler = new WebserviceHandler(context);
		try {
		if(!ispost)
		 reponse = handler.hitUrl(params[0]);
		else{
			reponse = handler.postData(params[0],params[1], params[2]);
		}
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
