package com.revelcare.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.revelcare.R;
import com.revelcare.utills.RevelCareGlobal;

public class Call extends Fragment implements OnClickListener {
	ImageView call_btn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_call, container, false);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		init();
	}
	
	private void init(){
		call_btn = (ImageView) getView().findViewById(R.id.call_btn);
		call_btn.setOnClickListener(this);
	}
	
	
	private void callToProvider(){
		Intent call_intent = new Intent(Intent.ACTION_CALL);
		call_intent.setData(Uri.parse(RevelCareGlobal.Calling_Number));
	}

	@Override
	public void onClick(View v) {
		callToProvider();		
	}
}
