package com.revelcare.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.wifi.WifiEnterpriseConfig.Phase2;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.revelcare.R;
import com.revelcare.bean.PharmacyDetails;
import com.revelcare.listener.ApiResponse;
import com.revelcare.task.BackgroungTask;
import com.revelcare.utills.RevelCareGlobal;
import com.revelcare.utills.RevelCareResponseParser;

public class PrescriptionPickUpFragment  extends Fragment implements OnClickListener, OnItemClickListener {

	//*UI view*//
private View contenerView;
private Button next_btn;
private AutoCompleteTextView pharmacy_edittext;
private TextView phone_txt, description_txt, address_txt;

private String error = "error", message = "message", ID = "_id", code ="code";
private ArrayList<PharmacyDetails> pharmacies = new ArrayList<>();
private ArrayList<String> pharmacyName = new ArrayList<>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		contenerView = inflater.inflate(R.layout.fragment_prescription_picup, container, false);
		return contenerView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		init();
	}

	private void init() {
		
		next_btn = (Button) getView().findViewById(R.id.btn_next);
		pharmacy_edittext=  (AutoCompleteTextView) getView().findViewById(R.id.editText_pharmacy);
		phone_txt = (TextView) getView().findViewById(R.id.textView_phone);
		description_txt = (TextView) getView().findViewById(R.id.textView_description);
		address_txt = (TextView) getView().findViewById(R.id.textView_address);
		pharmacy_edittext.setOnItemClickListener(this);
		next_btn.setOnClickListener(this);
		String pharmacies_url = RevelCareGlobal.Pharmacies;
		getPharmacyList(pharmacies_url);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_next:
			addFragment(R.id.fragmentLayout, new PatientInformation());
			break;
		}
	}
	public void addFragment(int id, Fragment fragment) {
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.replace(R.id.fragmentLayout, fragment);
		fragmentTransaction.commit();
	}
	
	private void getPharmacyList(String url){
		BackgroungTask task = new BackgroungTask(getActivity(), response, "Locating pharmacies", false);
		task.execute(url);
	}
	
	private ApiResponse response = new ApiResponse() {
		
		@Override
		public void onSuccess(String response) {
			System.out.println("response"+response);
			if(response != null){
				boolean jobject = response.startsWith("{");
				boolean jsarray = response.startsWith("[");
			
			if(jobject){
					getError(response);
				}
			if(jsarray){
				try {
					JSONArray pharmacieslist = new JSONArray(response);
					RevelCareResponseParser parser = new RevelCareResponseParser();
					pharmacies = parser.getAllPharmacies(pharmacieslist);
					System.out.println("pharmacies size "+pharmacies.size());
					getPharmacyName(pharmacies);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
			
	}
		
		@Override
		public void onFailure() {
			
		}
	};
	
	public void getError(String response) {
		try {

			JSONObject result = new JSONObject(response);
			if (result.has(error)) {
			String message = getErrorMessage(result);
			RevelCareGlobal.toastShow(message, getActivity().getApplicationContext());
			}
				
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private String  getErrorMessage(JSONObject result) {
		String errors, server_message = null;
		try{
		if (result.has(error)) {
			errors = result.getString(error);
		}
		if (result.has(message)) {
			server_message = result.getString(message);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(server_message != null)
		return server_message;
		else
		return "null";
	}
	
	private void getPharmacyName(ArrayList<PharmacyDetails> pharmacyDetails){
		
		for (int i = 0; i < pharmacyDetails.size(); i++) {
			pharmacyName.add(pharmacyDetails.get(i).getName());
		}
		if(pharmacyDetails.size()>0){
		pharmacy_edittext.setText(pharmacyDetails.get(0).getName());
		onItemSelect(pharmacyDetails.get(0).getAddress(),
				     pharmacyDetails.get(0).getDescription()
				     ,pharmacyDetails.get(0).getPhone());
		}
		ArrayAdapter<String> pharmacies_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1,pharmacyName);
		pharmacy_edittext.setAdapter(pharmacies_adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String address = pharmacies.get(position).getAddress();
		String description = pharmacies.get(position).getDescription();
		String phone = pharmacies.get(position).getPhone();
		onItemSelect(address, description, phone);
		
	}
	
	private void onItemSelect(String address, String description, String phone){
		phone_txt.setText("\u2022 "+phone);
		address_txt.setText("\u2022 "+address);
		description_txt.setText("\u2022 "+description);
	}
}
