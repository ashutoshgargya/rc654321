package com.revelcare.utills;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

public class RevelCareGlobal {
 
	public static String Base_URL = "https://www.revelcare.com/api/api.php?action=";
	
	//get Invite code 
	public static String Invite_Code = Base_URL + "requestInviteCode";
	
	// validate invite code 
	public static String Validate_Invite_Code = Base_URL + "validateInviteCode&code=%s";
	
	// insert user to server 
	public static String InsertUser = Base_URL + "insertUser";
	
	// insert user data code
	public static String Required_Data_User = "{\"email_address\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}";
	
	// get nearest pharmacy list 
	public static String Pharmacies = Base_URL + "pharmacyList";
	
	// update user list 
	public static String UpdateUser = Base_URL + "updateUser";
	
	// insert pickupwindow or time window 
	public static String Insert_Pickup = Base_URL + "insertPickup";
	
	//add insurance details
	public static String InsuranceDetails = Base_URL+ "updateInsuranceDetails";
	
	// latest news 
	public static String Latest_News = Base_URL + "getNews" ;
	
	public static String  Get_Prescription= Base_URL + "getPrescriptions";
	
	
	public static String Calling_Number = "";
	
	
	public static void toastShow(String message, Context context) {
		Toast display = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		display.setGravity(Gravity.CENTER, 0, 0);
		display.show();
	}
	
	public static void openActivity(Context context, Class<?> instance) {
		Intent intent = new Intent(context, instance);
		context.startActivity(intent);
	}
	
}
