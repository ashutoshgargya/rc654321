package com.revelcare.fragments;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.revelcare.BaseActivity;
import com.revelcare.MenuScreen;
import com.revelcare.R;
import com.revelcare.utills.Preferences;

public class Insurance extends Fragment implements OnClickListener {
	private ImageView insurance_front, insurance_back;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_FRONT = 100;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_BACK = 200;
	private Uri fileUri;
	private Button btn_nxt;
	private Preferences preferences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_insurance,
				container, false);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		init();
	}

	private void init() {
		preferences = Preferences.getInstance(getActivity());
		insurance_front = (ImageView) getView().findViewById(R.id.imageView_front);
		insurance_back = (ImageView) getView().findViewById(R.id.imageView_back);
		btn_nxt = (Button) getView().findViewById(R.id.btn_nxt);
		btn_nxt.setOnClickListener(this);
		insurance_back.setOnClickListener(this);
		insurance_front.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageView_back:
//			captureImage(CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_BACK);
			Intent intent = new Intent(getActivity(), MenuScreen.class);
			startActivity(intent);
			break;
		case R.id.imageView_front:
			captureImage(CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_FRONT);
			break;
		case R.id.btn_nxt:
			addFragment(R.id.fragment_container, new Deliver());
			break;
		}
	}
	
	private void captureImage(int capture_code){
		
		// create Intent to take a picture and return control to the calling application
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // start the image capture Intent
	    startActivityForResult(intent, capture_code);
	}
	
	  @Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
		  System.out.println("Activity result");
	        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_FRONT) {
	            if (resultCode == Activity.RESULT_OK) {

	                Bitmap bmp = (Bitmap) data.getExtras().get("data");
	                ByteArrayOutputStream stream = new ByteArrayOutputStream();

	                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
	                byte[] byteArray = stream.toByteArray();

	                // Convert ByteArray to Bitmap::

	                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,byteArray.length);
	                
	                saveToLocalSDCard(bitmap, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_FRONT);
	                insurance_front.setImageBitmap(bitmap);
	                }
	        	}else{
	            	  if (resultCode == Activity.RESULT_OK) {

	  	                Bitmap bmp = (Bitmap) data.getExtras().get("data");
	  	                ByteArrayOutputStream stream = new ByteArrayOutputStream();

	  	                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
	  	                byte[] byteArray = stream.toByteArray();

	  	                // Convert ByteArray to Bitmap::
	  	                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,byteArray.length);
	  	                saveToLocalSDCard(bitmap, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE_BACK);
	  	                insurance_back.setImageBitmap(bitmap);
	            }
	        }        
	    }    
	
	  
	private void saveToLocalSDCard(Bitmap _bitmapScaled, int imagetype) {
		
		File f;
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		_bitmapScaled.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		System.out.println("Environment.getExternalStorageDirectory()+ File.separator + "+Environment.getExternalStorageDirectory()+ File.separator + "front.jpg");
		// you can create a new file name "test.jpg" in sdcard folder.
		if(imagetype == 100){
			f = new File(Environment.getExternalStorageDirectory()+ File.separator + "front.jpg");
			System.out.println("pathh"+f.getAbsolutePath());
			preferences.setImg_path(f.getAbsolutePath());
		}
		else
			f = new File(Environment.getExternalStorageDirectory()+ File.separator + "back.jpg");
		try {
			f.createNewFile();
			// write the bytes in file
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(bytes.toByteArray());
			// remember close de FileOutput
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addFragment(int id, Fragment fragment) {
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	    fragmentTransaction.replace(R.id.fragmentLayout, fragment);
		fragmentTransaction.commit();
	}
	
}