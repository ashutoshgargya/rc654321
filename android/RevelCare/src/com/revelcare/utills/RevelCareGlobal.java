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
	
	public static String InsertUser = Base_URL + "insertUser";
	
	public static String Required_Data_User = "{\"email_address\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}";
	
	public static String Pharmacies = Base_URL + "pharmacyList";
	
	public static String UpdateUser = Base_URL + "updateUser";
	
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
