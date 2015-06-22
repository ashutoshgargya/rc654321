package com.revelcare.fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.revelcare.R;
import com.revelcare.listener.ApiResponse;
import com.revelcare.listener.DatePickListener;
import com.revelcare.network.NetworkUtil;
import com.revelcare.task.UpdatePatientINFOTask;
import com.revelcare.utills.DateIntervelPicker;
import com.revelcare.utills.RevelCareGlobal;

public class PatientInformation extends Fragment implements OnClickListener {
	private EditText name_editText, addres_editText, city_editText,state_editText,
	                 zip_editText,phone_editText, DOB_editText;
	private Button next_btn;
	String name, address, city, pincode, DOB, phone;
	Resources resources;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootview = inflater.inflate(R.layout.fragment_patient, container, false);
		return rootview;
		
	}

	@Override
	public void onStart() {
		super.onStart();
		init();
	}
	
	//initialise all the view of class 
	private void init() {
		resources = getActivity().getResources();
		name_editText = (EditText)getView().findViewById(R.id.editText_name);
		addres_editText = (EditText)getView().findViewById(R.id.editText_address);
		city_editText = (EditText)getView().findViewById(R.id.editText_city);
		state_editText = (EditText) getView().findViewById(R.id.editText_state);
		zip_editText = (EditText) getView().findViewById(R.id.editText_zipcode);
		phone_editText = (EditText) getView().findViewById(R.id.editText_phone);
		DOB_editText = (EditText) getView().findViewById(R.id.editText_DOB);
		DOB_editText.setOnClickListener(this);
		next_btn = (Button) getView().findViewById(R.id.btn_nxt);
		next_btn.setOnClickListener(this);
		
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_nxt:
			if (isFieldsEmpty()) {
				showError();
			}else{
				if(!NetworkUtil.getConnectivityStatusString(getActivity()).equals(
				NetworkUtil.TYPE_NOT_CONNECTED))
				updateUser();
				else{
				RevelCareGlobal.toastShow(NetworkUtil.NotConnect, getActivity().getApplicationContext());
				
				}
			}
			break;
			
		case R.id.editText_DOB:
		DateIntervelPicker dateIntervelPicker = new DateIntervelPicker(datePickListener, "nothing", "type");
		dateIntervelPicker.show(getActivity().getFragmentManager(), "datepicker");
			break;

		}
	}
	
	public void addFragment(int id, Fragment fragment) {
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.replace(R.id.fragmentLayout, fragment);
		fragmentTransaction.commit();
	}
	
	@SuppressLint("NewApi")
	private boolean isFieldsEmpty(){
		 name = name_editText.getText().toString();
		 address = addres_editText.getText().toString();
		 city = city_editText.getText().toString();
		 pincode =  state_editText.getText().toString()+"-"
				    + zip_editText.getText().toString();
		 phone = phone_editText.getText().toString();
		 DOB = DOB_editText.getText().toString();
		if(name.isEmpty() || address.isEmpty() || city.isEmpty() ||
				pincode.isEmpty() || phone.isEmpty() || DOB.isEmpty()){
			
			return true;
			
		}else{
			return false;
		}
		
	}

	@SuppressLint("NewApi")
	private void showError() {
		if (name.isEmpty()) {
			name_editText.setError(resources.getString(R.string.name_empty));
		}
		if (address.isEmpty()) {
			addres_editText.setError(resources.getString(R.string.address_empty));
		}
		if (city.isEmpty()) {
			city_editText.setError(resources.getString(R.string.city_empty));
		}
		if (pincode.isEmpty()) {
			state_editText.setError(resources.getString(R.string.zip_empty));
			zip_editText.setError(resources.getString(R.string.zip_empty));
		}
		if (phone.isEmpty()) {
			phone_editText.setError(resources.getString(R.string.phone_number));
		}
		if (DOB.isEmpty()) {
			DOB_editText.setError(resources.getString(R.string.dob_empty));
		}
	}

	private void updateUser(){
		String URL = RevelCareGlobal.UpdateUser;
		UpdatePatientINFOTask updateuser = new UpdatePatientINFOTask(getActivity(), response, "Saving");
		updateuser.execute(URL,name, address, city, pincode, phone, DOB);
	}
	
	
	private ApiResponse response = new ApiResponse() {
		
		@Override
		public void onSuccess(String response) {
			System.out.println("response "+response);
			addFragment(R.id.fragmentLayout, new Insurance());
		}
		
		@Override
		public void onFailure() {
			
		}
	};
	
	DatePickListener datePickListener = new DatePickListener() {
		
		@Override
		public void onDateChange(int year, int monthOfYear, int dayOfMonth,
				String typeofpicker, String type) {
			System.out.println("year"+year+"monthOfYear"+monthOfYear+"dayOfMonth"+dayOfMonth);	
			
			DOB_editText.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
		}
	};
}
