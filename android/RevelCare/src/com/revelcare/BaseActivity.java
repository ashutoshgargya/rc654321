package com.revelcare;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class BaseActivity extends FragmentActivity {

	public void init(){
		
	}
	
	/**
	 * Use to remove Fragment
	 * @param interger Id Id of Fragment container 
	 * */
	public void removeFragment(int id) {
		getSupportFragmentManager().beginTransaction()
				.remove(getSupportFragmentManager().findFragmentById(id))
				.commit();
	}

	/**
	 * Add a new fragment to activity Replace if exist
	 * @param Fragment extended class object
	 * @author Ongraph
	 */
	public void addFragment(int id, Fragment fragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.add(R.id.fragmentLayout, fragment);
		fragmentTransaction.commit();
	}

	
	public void replaceFragment(int id, Fragment fragment){
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(id, fragment);
		fragmentTransaction.commit();
	}
}
