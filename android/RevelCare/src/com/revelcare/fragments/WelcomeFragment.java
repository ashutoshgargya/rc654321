package com.revelcare.fragments;


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
import android.widget.TextView;
import android.widget.Toast;

import com.revelcare.PrescriptionActivity;
import com.revelcare.R;
import com.revelcare.listener.ApiResponse;
import com.revelcare.network.NetworkUtil;
import com.revelcare.task.BackgroungTask;
import com.revelcare.utills.RevelCareGlobal;
import com.revelcare.utills.RevelCareResponseParser;

public class WelcomeFragment extends Fragment implements OnClickListener {

	private View contenerView;
	private Button requestBtn,submitBtn;
	private EditText editText_code;
	private String validate = "Validating";
	private TextView error_txt_view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		contenerView = inflater.inflate(R.layout.fragment_welcome, container,
				false);
		return contenerView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		init();
	}
	
	private void init(){
		requestBtn = (Button)getView().findViewById(R.id.requestBtn);
		submitBtn = (Button)getView().findViewById(R.id.submitBtn);
		editText_code = (EditText) getView().findViewById(R.id.editText_code);
		error_txt_view = (TextView) getView().findViewById(R.id.txtView_error);
		requestBtn.setOnClickListener(this);
		submitBtn.setOnClickListener(this);
	}
	
		

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.requestBtn:
			System.out.println("in the request button ");
			addFragment(R.id.fragmentLayout, new RequestFragment());
			break;

		case R.id.submitBtn:
			String code = editText_code.getText().toString();
			if(code.length() == 9){
			validateInviteCode(code);
		/*	Intent intent = new Intent(getActivity(), PrescriptionActivity.class);
			startActivity(intent);*/
			}else{
				editText_code.setError("request code should be 9 digits");
			}
			break;
		}
	}
	
	public void addFragment(int id, Fragment fragment) {
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.replace(R.id.fragmentLayout, fragment);
	    fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}
	
	private boolean validateInviteCode(String code){
		String URL = String.format(RevelCareGlobal.Validate_Invite_Code, code);
		if(!NetworkUtil.getConnectivityStatusString(getActivity()).equals(NetworkUtil.NotConnect)){
		BackgroungTask task = new BackgroungTask(getActivity(), response, validate, false);
		task.execute(URL);
		}
		
		return false;
		
	}
	
	ApiResponse response = new ApiResponse() {
		
		@Override
		public void onSuccess(String response) {
			RevelCareResponseParser parser = new RevelCareResponseParser();
			String result = parser.parseInviteCode(response, getActivity());
			getResult(result);
		}
		
		@Override
		public void onFailure() {
			
		}
	};

	private void getResult(String result) {
		if (result.equals("working")) {
			RevelCareGlobal.openActivity(getActivity(), PrescriptionActivity.class);
			}else {
			Toast.makeText(getActivity(), "" + result, Toast.LENGTH_LONG)
			.show();
			error_txt_view.setVisibility(View.VISIBLE);
			error_txt_view.setText("" + getResources().getString(R.string.error_msg));
		}
	}
}
