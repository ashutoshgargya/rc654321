package com.revelcare.utills;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Ongraph Class Save User Name , Image URl & Token
 * last share time 
 * */
public class Preferences {

	private static SharedPreferences sharedPreferences;

	private static final String PREFERENCE_FILE = "Prefs";

	private static Preferences preference;

	private static final String NAME = "NAME";
	
	private static final String ID = "_id";
	
	
	
	public static Preferences getInstance(final Context context) {
		if (preference == null) {
			preference = new Preferences();
		}
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE,
					Context.MODE_PRIVATE);
		}

		return preference;
	}

	public void setName(final String name) {
		System.out.println("Set Default fuel >> " + name);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(NAME, name);
		editor.commit();
	}

	public String getName() {

		return sharedPreferences.getString(NAME, null);
	}
	public void setID(final String name) {
		System.out.println("Set Default fuel >> " + name);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(ID, name);
		editor.commit();
	}

	public String getID() {

		return sharedPreferences.getString(ID, null);
	}
	


}
