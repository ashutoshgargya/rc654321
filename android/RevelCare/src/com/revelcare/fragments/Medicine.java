package com.revelcare.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.revelcare.R;
import com.revelcare.adapter.MedicineItem;
import com.revelcare.bean.PerscribedMedicine;
import com.revelcare.listener.ApiResponse;
import com.revelcare.task.DeliverTask;
import com.revelcare.utills.RevelCareGlobal;
import com.revelcare.utills.RevelCareResponseParser;

public class Medicine extends Fragment {
	
	private String error = "error", message = "message", ID = "_id",
			code = "code";
	
	private ArrayList<PerscribedMedicine> medicines = new ArrayList<>();
	
	private ListView listView;
	
	private TextView textView_medice_header;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_medicine, container,false);
		return rootView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		init();
	}
	
	private void init(){
		textView_medice_header = (TextView) getView().findViewById(R.id.textView_medice_header);
		textView_medice_header.setPaintFlags(textView_medice_header.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		listView = (ListView) getView().findViewById(R.id.listView_medicine);
		String URL = RevelCareGlobal.Get_Prescription;
		DeliverTask updateuser = new DeliverTask(getActivity(), response, "Saving",false);
		updateuser.execute(URL);
	}
	
	private ApiResponse response = new ApiResponse() {
		public void onFailure() {};
		public void onSuccess(String response) {
			System.out.println("response" + response);
			if (response != null) {
				boolean jobject = response.startsWith("{");
				boolean jsarray = response.startsWith("[");

				if (jobject) {
					getError(response);
				}
				if (jsarray) {
					try {
						
						// Got News data,  parse it and show
						JSONArray medicineList = new JSONArray(response);
						RevelCareResponseParser parser = new RevelCareResponseParser();
						medicines = parser.getPrescribeMedicine(medicineList);
						System.out.println("ajksdhasjkd"+medicines.size());
						MedicineItem items = new MedicineItem(getActivity(), medicines);
						listView.setAdapter(items);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		};
	};
	
	public void getError(String response) {
		try {

			JSONObject result = new JSONObject(response);
			if (result.has(error)) {
				String message = getErrorMessage(result);
				RevelCareGlobal.toastShow(message, getActivity()
						.getApplicationContext());
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	private String getErrorMessage(JSONObject result) {
		String errors, server_message = null;
		try {
			if (result.has(error)) {
				errors = result.getString(error);
			}
			if (result.has(message)) {
				server_message = result.getString(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (server_message != null)
			return server_message;
		else
			return "null";
	}
}
