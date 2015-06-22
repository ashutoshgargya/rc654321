package com.revelcare;

import com.revelcare.fragments.Contacts;
import com.revelcare.fragments.Health;
import com.revelcare.fragments.Medicine;
import com.revelcare.fragments.News;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuScreen extends ActionBarActivity implements OnClickListener {
	private Button news_btn, medicine_btn, health_btn, contacts_btn;
	private String news = "news", medicine = "medicine",health =  "health", contacts = "contacts"; 
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_menu_screen);
		ActionBar bar = getSupportActionBar();
		bar.hide();
		init();
	}
	
	public void init() {
		news_btn = (Button) findViewById(R.id.btn_news);
		medicine_btn = (Button) findViewById(R.id.btn_medicine);
		health_btn = (Button) findViewById(R.id.btn_health);
		contacts_btn = (Button) findViewById(R.id.btn_contacts);
		
		news_btn.setOnClickListener(this);
		medicine_btn.setOnClickListener(this);
		health_btn.setOnClickListener(this);
		contacts_btn.setOnClickListener(this);
		addFragment(R.id.fragment_container, new News(),news);
	}

	@Override
	public void onClick(View v) {
		int ID = R.id.fragment_container;
		switch (v.getId()) {
		case R.id.btn_news:
			removeFragment(ID);
			addFragment(ID, new News(),news);
			break;
		case R.id.btn_contacts:
			removeFragment(ID);
			addFragment(ID, new Contacts(),contacts);
			break;
		case R.id.btn_medicine:
			removeFragment(ID);
			addFragment(ID, new Medicine(),medicine);
			break;
		case R.id.btn_health:
			removeFragment(ID);
			addFragment(ID, new Health(),health);
			break;

		}
	}
	
	public void addFragment(int id, Fragment fragment, String fragment_tag) {
		changeActiveFragmentIcon(fragment_tag);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.add(id, fragment);
		fragmentTransaction.commit();
	}
	
	public void removeFragment(int id) {
		getSupportFragmentManager().beginTransaction()
		.remove(getSupportFragmentManager().findFragmentById(id))
		.commit();
	}
	
	@SuppressLint("NewApi")
	private void changeActiveFragmentIcon(String fragment){
		if(fragment.equals(news)){
			news_btn.setBackground(getResources().getDrawable(R.drawable.news_phone_select));
			medicine_btn.setBackground(getResources().getDrawable(R.drawable.folder_phone));
			health_btn.setBackground(getResources().getDrawable(R.drawable.calender_phone));
			contacts_btn.setBackground(getResources().getDrawable(R.drawable.calling_phone));
		}
		if(fragment.equals(medicine)){
			news_btn.setBackground(getResources().getDrawable(R.drawable.news_phone));
			medicine_btn.setBackground(getResources().getDrawable(R.drawable.folder_phone_select));
			health_btn.setBackground(getResources().getDrawable(R.drawable.calender_phone));
			contacts_btn.setBackground(getResources().getDrawable(R.drawable.calling_phone));
		}
		if(fragment.equals(health)){
			news_btn.setBackground(getResources().getDrawable(R.drawable.news_phone));
			medicine_btn.setBackground(getResources().getDrawable(R.drawable.folder_phone));
			health_btn.setBackground(getResources().getDrawable(R.drawable.calender_phone_select));
			contacts_btn.setBackground(getResources().getDrawable(R.drawable.calling_phone));
			
		}
		if(fragment.equals(contacts)){
			news_btn.setBackground(getResources().getDrawable(R.drawable.news_phone));
			medicine_btn.setBackground(getResources().getDrawable(R.drawable.folder_phone));
			health_btn.setBackground(getResources().getDrawable(R.drawable.calender_phone));
			contacts_btn.setBackground(getResources().getDrawable(R.drawable.calling_phone_select));
			
		}
	}
}
