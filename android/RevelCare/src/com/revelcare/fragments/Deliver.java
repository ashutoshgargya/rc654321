package com.revelcare.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.revelcare.R;
import com.revelcare.listener.ApiResponse;
import com.revelcare.task.DeliverTask;
import com.revelcare.task.UpdatePatientINFOTask;
import com.revelcare.utills.RevelCareGlobal;

public class Deliver extends Fragment implements OnClickListener, OnCheckedChangeListener {
private	Button btn_nxt ;
private RadioGroup today_time_slot_radio_grp;
private	RadioButton lunch_rdo_btn_today,eve_rdo_btn_today,nyt_rdo_btn_today;
private	RadioButton morn_rdo_btn_tomm,lunch_rdo_btn_tomm,eve_rdo_btn_tomm,nyt_rdo_btn_tomn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_deliver, container, false);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		init();
	}
	
	private void init(){
		btn_nxt =  (Button) getView().findViewById(R.id.btn_nxt);
		btn_nxt.setOnClickListener(this);
		
		today_time_slot_radio_grp = (RadioGroup) getView().findViewById(R.id.radioGroup_today);
		today_time_slot_radio_grp.setOnCheckedChangeListener(this);
		
		String today = getTodayDate();
		System.out.println("date "+today);
		// initailise radio button
		radioInit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_nxt:
		/*	String date_of_delivery = getTodayDate();
			String time_of_delivery =  getTimeOFDelivery();
			String radiovalue=  ((RadioButton)getActivity().findViewById(today_time_slot_radio_grp.getCheckedRadioButtonId())).getText().toString(); 
			System.out.println("date of delivery "+date_of_delivery+"time of delivery"+radiovalue);
			addFragment(R.id.fragment_container, new Call());*/
			updateUser();
			break;

		}
	}
	
	private void radioInit(){
		
		lunch_rdo_btn_today = (RadioButton) getView().findViewById(R.id.rd_btn_lunch_today);
		eve_rdo_btn_today = (RadioButton) getView().findViewById(R.id.rd_btn_evening_today);
		nyt_rdo_btn_today = (RadioButton) getView().findViewById(R.id.rd_btn_nyt_today);
		
		morn_rdo_btn_tomm = (RadioButton) getView().findViewById(R.id.rd_btn_morn_tm);
		lunch_rdo_btn_tomm = (RadioButton) getView().findViewById(R.id.rd_btn_lunch_tm);
		eve_rdo_btn_tomm = (RadioButton) getView().findViewById(R.id.rd_btn_evening_tm);
		nyt_rdo_btn_tomn =  (RadioButton) getView().findViewById(R.id.rd_btn_nyt_tm);
	}
	
	private String getTodayDate(){
		Calendar c = Calendar.getInstance();
		System.out.println("Current time => " + c.getTime());

		SimpleDateFormat df = new SimpleDateFormat("dd+MMM+yyyy");
		String formattedDate = df.format(c.getTime());
		return formattedDate;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		switch (checkedId) {
		case R.id.rd_btn_lunch_today:
			break;
		case R.id.rd_btn_evening_today:
			break;
		case R.id.rd_btn_nyt_today:
			break;
		case R.id.rd_btn_morn_tm:
			break;
		case  R.id.rd_btn_lunch_tm:
			break;
		case R.id.rd_btn_evening_tm:
			break;
		case R.id.rd_btn_nyt_tm:
			break;
		}
		
	}
	
	private String getTimeOFDelivery(){
		String today = null;
		int today_id = today_time_slot_radio_grp.getCheckedRadioButtonId();
		System.out.println("today_id"+today_id);
		if(today_id != -1)
		today =  ((RadioButton)getActivity().findViewById(today_time_slot_radio_grp.getCheckedRadioButtonId())).getText().toString(); 
		
		if(today != null){
			return today;
		}else{
			return "null";
		}
	}
	
	public void addFragment(int id, Fragment fragment) {
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.replace(R.id.fragmentLayout, fragment);
		fragmentTransaction.commit();
	}
	
	private String getPickUpTime() {
		String date_of_delivery = getTodayDate();
		String time_of_delivery = getTimeOFDelivery();
		String time = null ;

		if (time_of_delivery.contains("PM")) {
			time =	time_of_delivery.replace("PM", "");
		}
		if (time.contains("AM")) {
			time.replace("AM", "");
		}
		return  time ;
	}

	private void updateUser(){
		String URL = RevelCareGlobal.Insert_Pickup;
		if(getTodayDate()!= null && !getTimeOFDelivery().equals("null")){
		String pickup = getPickUpTime();
		String date = getTodayDate();
		System.out.println("URL"+URL);
		System.out.println("pickup"+pickup);
		System.out.println("date"+date);
		DeliverTask updateuser = new DeliverTask(getActivity(), response, "Saving",true);
		updateuser.execute(URL,pickup,date);
		}else{
			Toast.makeText(getActivity(), "unable to send ", Toast.LENGTH_LONG).show();
		}
		
	}
	
	
	private ApiResponse response = new ApiResponse() {
		
		@Override
		public void onSuccess(String response) {
			System.out.println("response "+response);
			addFragment(R.id.fragmentLayout, new  Call());
		}
		
		@Override
		public void onFailure() {
			
		}
	};
	
	
}
