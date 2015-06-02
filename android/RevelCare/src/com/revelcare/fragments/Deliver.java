package com.revelcare.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.revelcare.BaseActivity;
import com.revelcare.R;

public class Deliver extends Fragment implements OnClickListener {
	Button btn_nxt ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_deliver, container, false);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
	}
	
	private void init(){
		btn_nxt =  (Button) getView().findViewById(R.id.btn_nxt);
		btn_nxt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_nxt:
			BaseActivity base = new BaseActivity();
			base.replaceFragment(R.id.fragment_container, new Call());
			break;

		}
	}
}
