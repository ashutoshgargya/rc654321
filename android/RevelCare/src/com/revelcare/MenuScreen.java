package com.revelcare;

import com.revelcare.fragments.Contacts;
import com.revelcare.fragments.Health;
import com.revelcare.fragments.Medicine;
import com.revelcare.fragments.News;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuScreen extends BaseActivity implements OnClickListener {
	private Button news_btn, medicine_btn, health_btn, contacts_btn;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_menu_screen);
		init();
	}
	
	@Override
	public void init() {
		super.init();
		news_btn = (Button) findViewById(R.id.btn_news);
		medicine_btn = (Button) findViewById(R.id.btn_medicine);
		health_btn = (Button) findViewById(R.id.btn_health);
		contacts_btn = (Button) findViewById(R.id.btn_contacts);
		
		news_btn.setOnClickListener(this);
		medicine_btn.setOnClickListener(this);
		health_btn.setOnClickListener(this);
		contacts_btn.setOnClickListener(this);
		addFragment(R.id.fragment_container, new News());
	}

	@Override
	public void onClick(View v) {
		int ID = R.id.fragment_container;
		switch (v.getId()) {
		case R.id.btn_news:
			removeFragment(ID);
			addFragment(ID, new News());
			break;
		case R.id.btn_contacts:
			removeFragment(ID);
			addFragment(ID, new Contacts());
			break;
		case R.id.btn_medicine:
			removeFragment(ID);
			addFragment(ID, new Medicine());
			break;
		case R.id.btn_health:
			removeFragment(ID);
			addFragment(ID, new Health());
			break;

		}
	}
	
	@Override
	public void addFragment(int id, Fragment fragment) {
		super.addFragment(id, fragment);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.add(id, fragment);
		fragmentTransaction.commit();
	}
	
	@Override
	public void removeFragment(int id) {
		super.removeFragment(id);
		getSupportFragmentManager().beginTransaction()
		.remove(getSupportFragmentManager().findFragmentById(id))
		.commit();
	}
}
