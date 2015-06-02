package com.revelcare.fragments;

import com.revelcare.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Call extends Fragment {
	
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
		
	}
}
