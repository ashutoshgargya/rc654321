package com.revelcare.utills;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.revelcare.bean.NewsDetails;
import com.revelcare.bean.PerscribedMedicine;
import com.revelcare.bean.PharmacyDetails;

public class RevelCareResponseParser {
	
	String error = "error", message = "message", ID = "_id", code ="code";
	String id = "$id", name = "name", description = "description";
	String address = "address", phone = "phone";
	String topic = "topic", text = "text";
	
	String dosage = "dosage",other = "other_field1";
	
	public String parseInviteCode(String response, Context context) {
		try {

			JSONObject result = new JSONObject(response);
			if (result.has(error)) {
				String message = getErrorMessage(result);
				return message;
			}else{
				getID(result, context);
				return "working";
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String  getErrorMessage(JSONObject result) {
		String errors, server_message = null;
		try{
		if (result.has(error)) {
			errors = result.getString(error);
		}
		if (result.has(message)) {
			server_message = result.getString(message);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(server_message != null)
		return server_message;
		else
		return "null";
	}
	
	public String getID(JSONObject result, Context context){
		String unique_id = null, codes;
		try{
			if (result.has(ID)) {
//				unique_id = result.getString(ID);
				JSONObject $id = result.getJSONObject(ID);
				if($id.has(id)){
					unique_id = $id.getString(id);
					Preferences preferences = Preferences.getInstance(context);
					preferences.setID(unique_id);
				}
			}
			if (result.has(code)) {
				codes = result.getString(code);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
		if(unique_id != null)
		return unique_id;
		else
			return "null";
	}
	
	public ArrayList<PharmacyDetails> getAllPharmacies(JSONArray result){
		ArrayList<PharmacyDetails> pharmacies = new ArrayList<PharmacyDetails>();
		
		
		for (int counter = 0; counter < result.length(); counter++) {
			PharmacyDetails details = new PharmacyDetails();
		try {
				JSONObject pharmacy = result.getJSONObject(counter);
				if(pharmacy.has(name)){
				details.setName(pharmacy.getString(name));
				}
				
				if(pharmacy.has(description)){
					details.setDescription(pharmacy.getString(description));
				}
				if(pharmacy.has(address)){
					details.setAddress(pharmacy.getString(address));
				}
				
				if(pharmacy.has(phone)){
					details.setPhone(pharmacy.getString(phone));
				}
				
				pharmacies.add(details);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return pharmacies;
	}
	
	
	public String saveIDS(String response) throws JSONException{
		JSONObject result = new JSONObject(response);
		String id = null ;
		if(result.has("_id")){
		JSONObject object = result.getJSONObject("_id");
			id = object.getString("$id");
			
		}
		return id;
	}
	
	
	public ArrayList<NewsDetails> getNews(JSONArray result){
		ArrayList<NewsDetails> news = new ArrayList<NewsDetails>();
		try {
		for (int counter = 0; counter < result.length(); counter++) {
			NewsDetails details = new NewsDetails();
		    JSONObject single_news = result.getJSONObject(counter);
		    if(single_news.has(topic)){
		    	details.setTopic(single_news.getString(topic));
		    }
		    if(single_news.has(text)){
		    	details.setDescription(single_news.getString(text));
		    }
		    news.add(details);
		  }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news;
		
	}
	
	
	public ArrayList<PerscribedMedicine> getPrescribeMedicine(JSONArray result){
		ArrayList<PerscribedMedicine> perscribed_medicines= new ArrayList<PerscribedMedicine>();
		try {
		for (int counter = 0; counter < result.length(); counter++) {
			PerscribedMedicine prescribed = new PerscribedMedicine();
		    JSONObject medicine = result.getJSONObject(counter);
		    
		    if(medicine.has(name)){
		    	prescribed.setName(medicine.getString(name));
		    } if(medicine.has(dosage)){
		    	prescribed.setDosage(medicine.getString(dosage));
		    } if(medicine.has(other)){
		    	prescribed.setOthers(other);
		    }
		    perscribed_medicines.add(prescribed);
		  }
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return perscribed_medicines;
	}
}
