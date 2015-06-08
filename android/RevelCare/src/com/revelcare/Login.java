package com.revelcare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.revelcare.fragments.WelcomeFragment;

public class Login extends BaseActivity {

	private static double longitute;
	private static double latitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		addFragment(R.id.fragmentLayout, new WelcomeFragment());
//		addFragment(R.id.fragmentLayout ,new Insurance());
		
	}
	
	@Override
	public void addFragment(int id, Fragment fragment) {
		super.addFragment(id, fragment);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.replace(R.id.fragmentLayout, fragment);
		fragmentTransaction.commit();
		
	}
	

	   
}
