package com.revelcare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;

import com.revelcare.fragments.PrescriptionPickUpFragment;

public class PrescriptionActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_prescription);
		addFragment(R.id.fragmentLayout,new PrescriptionPickUpFragment());
	}
	
	@Override
	public void addFragment(int id, Fragment fragment) {
		super.addFragment(id, fragment);
		/*FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.add(R.id.fragmentLayout, fragment);
	    fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();*/
		
	}
}
