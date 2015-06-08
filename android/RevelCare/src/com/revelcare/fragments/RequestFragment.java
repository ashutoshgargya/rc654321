package com.revelcare.fragments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.revelcare.PrescriptionActivity;
import com.revelcare.R;
import com.revelcare.listener.ApiResponse;
import com.revelcare.network.NetworkUtil;
import com.revelcare.task.BackgroungTask;
import com.revelcare.utills.MessageDialog;
import com.revelcare.utills.Preferences;
import com.revelcare.utills.RevelCareGlobal;
import com.revelcare.utills.RevelCareResponseParser;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class RequestFragment extends Fragment implements OnClickListener {

	private View contenerView;
	private EditText name_edt_txt, email_edt_txt;
    private Button send_btn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contenerView = inflater.inflate(R.layout.fragment_request, container,
				false);
		return contenerView;
	}

	@Override
	public void onStart() {
		super.onStart();
		init();
		
	}
	
	private void init(){
		name_edt_txt = (EditText) getView().findViewById(R.id.editText_name);
		email_edt_txt = (EditText) getView().findViewById(R.id.editText_email);
		send_btn = (Button) getView().findViewById(R.id.send_btn);
		send_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send_btn:
			sendRequest();
			break;

		}
	}

	private void sendRequest() {
		String URL = RevelCareGlobal.InsertUser;
		String Body = getPostData();
		if(Body != null){
		if (!NetworkUtil.getConnectivityStatusString(getActivity()).equals(
				NetworkUtil.TYPE_NOT_CONNECTED)) {
			String email = email_edt_txt.getText().toString();
			String name = name_edt_txt.getText().toString();
			BackgroungTask task = new BackgroungTask(getActivity(), response,"Insertting User",true);
			task.execute(URL,email,name);
		}else{
			MessageDialog dialog = new MessageDialog(getActivity(), "Internet is not found");
			dialog.show();
		}
	}else{
		System.out.println("empty ");
	}
	}

	ApiResponse response = new ApiResponse() {
		
		@Override
		public void onSuccess(String response) {
			RevelCareResponseParser parser = new RevelCareResponseParser();
			String resultss = parser.parseInviteCode(response,getActivity());
			getResult(resultss,response);
			
		
		}
		
		@Override
		public void onFailure() {
		}
	};
	
	private String getPostData(){
		String body = null;
		if(hasError()){
			String email = email_edt_txt.getText().toString();
			String name = name_edt_txt.getText().toString();
			body = String.format(RevelCareGlobal.Required_Data_User, email, "secret",name);
		}
		else{
			Toast.makeText(getActivity(), "empty", Toast.LENGTH_LONG).show();
		}
		return body;
	}
	
	private boolean hasError(){
		String email = email_edt_txt.getText().toString();
		String name = name_edt_txt.getText().toString();
	
		if(email.isEmpty()){
			email_edt_txt.setError("your email is required");
			return false;
		}else if(!emailValidator(email)){
			email_edt_txt.setError("please enter valiod");
			return false;
		}
		
		if(name.isEmpty()){
			name_edt_txt.setError("user name is required");
		return false;
		}
		return true;
	}
	
	/**
	 * validate your email address format. Ex-akhi@mani.com
	 */
	public boolean emailValidator(String email) {
		System.out.println("email"+email);
	    Pattern pattern;
	    Matcher matcher;
	    final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	    pattern = Pattern.compile(EMAIL_PATTERN);
	    matcher = pattern.matcher(email);
	    return matcher.matches();
	}
	
	private void getResult(String result, String response) {
		if (result.equals("working")) {
			String id = null ;
			RevelCareResponseParser responseParser = new RevelCareResponseParser();
			try {
				id = responseParser.saveIDS(response);
				Preferences preferences = Preferences.getInstance(getActivity());
				preferences.setID(id);
				System.out.println("idsd"+id);
				
				RevelCareGlobal.openActivity(getActivity(), PrescriptionActivity.class);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			}else {
			Toast.makeText(getActivity(), "" + result, Toast.LENGTH_LONG)
			.show();
		}
	}
}
