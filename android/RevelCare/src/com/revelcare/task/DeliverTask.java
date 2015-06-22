package com.revelcare.task;

import android.content.Context;
import android.os.AsyncTask;

import com.revelcare.listener.ApiResponse;
import com.revelcare.utills.ProgressDialog;
import com.revelcare.utills.WebserviceHandler;

public class DeliverTask extends AsyncTask<String, String, String> {
	private Context context;
	private ApiResponse response;
	private ProgressDialog dialog;
	private String message;
	private boolean is_deliver ;

	public DeliverTask(Context context, ApiResponse response, String message,boolean is_deliver) {
		this.context = context;
		this.response = response;
		this.message = message;
		this.is_deliver = is_deliver;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = new ProgressDialog(context, message);
		dialog.show();
	}

	@Override
	protected String doInBackground(String... params) {
		String reponse = null;
		WebserviceHandler handler = new WebserviceHandler(context);
		try {
		if(is_deliver)
		 reponse = handler.postData(params[0], params[1], params[2], context);
		else{
			reponse = handler.postData(params[0], context, "556c4791af1dd1c9448b456c");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reponse;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (dialog != null) {
			dialog.dismiss();
		}
		if (result != null) {
			response.onSuccess(result);
		} else {
			response.onFailure();
		}
	}
}
