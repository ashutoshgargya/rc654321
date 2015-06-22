package com.revelcare.utills;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.database.CursorJoiner.Result;
import android.util.Log;
import android.widget.Toast;

public class WebserviceHandler {
	Context context;
	
	public WebserviceHandler(Context context) {
	  this.context = context;
	}
	
	public  DefaultHttpClient getHttpClient() {
		
        HttpParams params = new BasicHttpParams();
        
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "UTF-8");

        HttpConnectionParams.setStaleCheckingEnabled(params, false);
       
        //Sets the timeout until a connection is etablished.
        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
        //  Sets the default socket timeout (SO_TIMEOUT) in milliseconds which is the timeout for waiting for data.
        HttpConnectionParams.setSoTimeout(params, 10 * 1000);
        
        HttpConnectionParams.setSocketBufferSize(params, 1024 * 10);

        HttpClientParams.setRedirecting(params, false);

        return new DefaultHttpClient(params);
    }

	public String hitUrl(String url) {

		try {
			System.out.println("url >> " + url);

			HttpClient httpclient = /* new DefaultHttpClient(); */getHttpClient();

			HttpGet httppost = new HttpGet(url);
			HttpResponse response = httpclient.execute(httppost);

			int code = response.getStatusLine().getStatusCode();
			if (code == 200 || code == 201)
				System.out.println("we got the response ");
			else if (code == 400 || code == 406 || code == 404 || code == 401
					|| code == 502 || code == 504 || code == 503)
				System.out.println("Un able to get response & request code is");
			System.out.println("status in first "
					+ response.getStatusLine().getStatusCode());

			HttpEntity entity = response.getEntity();

			return EntityUtils.toString(entity);

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection" + e.toString());
		}
		return null;
	}

	public String hitUrlget(String url) {
		String result = null;
		try {
			System.out.println("url >> " + url);

			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);

			HttpResponse response = httpclient.execute(httpget);

			System.out.println("url >>response>>>>>>>>>>>.service  " + response);
			System.out.println("status in first "+ response.getStatusLine().getStatusCode());

			HttpEntity entity = response.getEntity();

			System.out.println(".............." + EntityUtils.toString(entity));

			int code = response.getStatusLine().getStatusCode();
			if (code == 200 || code == 201)
				System.out.println("we got the response ");
			else if (code == 400 || code == 406 || code == 404 || code == 401
					|| code == 502 || code == 504 || code == 503)
				Toast.makeText(context,"Un able to get response & request code is" + code,Toast.LENGTH_LONG).show();

			return EntityUtils.toString(entity);
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection" + e.toString());
		}
		System.out.println("result>>>>>" + result);
		return result;
	}

	public  String post(String url, String body) throws ClientProtocolException, IOException, URISyntaxException{
		
		HttpPost httppost = new HttpPost();
		httppost.setURI(new URI(url));
		
		StringEntity se = new StringEntity(body,HTTP.UTF_8);
		System.out.println("urlfasfsadf "+url+body);
		se.setContentType("text/json");  
		
		httppost.setHeader("Content-Type","application/json; charset=UTF-8");
		httppost.setEntity(se);  
		
		DefaultHttpClient httpClient = getHttpClient();
		HttpResponse httpResponse  = httpClient.execute(httppost);
		
		if(httpResponse.getStatusLine().getStatusCode() == 200) 
		{
			String result = EntityUtils.toString(httpResponse.getEntity());
			int maxLogSize = 1000;
		    for(int i = 0; i <= result.length() / maxLogSize; i++) {
		        int start = i * maxLogSize;
		        int end = (i+1) * maxLogSize;
		        end = end > result.length() ? result.length() : end;
		        Log.v("JOB", result.substring(start, end));
		    }
			System.out.println(" -- reslutant>>>>>"+ result);
			return result;
		}else{
			genError(httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().toString());
		}
		return null;
	}
	
	public String postData(String url, String email, String name) {
		HttpEntity entity = null;
		String result = null;
	  
		  // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("email_address", email));
	        nameValuePairs.add(new BasicNameValuePair("password", "secret is Cool!"));
	        nameValuePairs.add(new BasicNameValuePair("name",name));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	         entity = response.getEntity();
	          result = EntityUtils.toString(entity);
	         System.out.println("sss"+result);
	        
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    if(entity != null)
			try {
				return result;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		else 
	    	return "null";
		return "null";
	} 
	public  void genError(Integer code, String message) {
		System.out.println("errorerrorerrorerrorerrorerror" +code.toString());
		Log.d("API","{error: {code: \"" + code.toString() + "\", message: \"" + message + "\"}}");
	}
	
	public String post(String url, ArrayList<String> patientINFO){
		Preferences preferences = Preferences.getInstance(context);
		
//		{"name",address","city","pincode","DOB","phone",""}
		
		HttpEntity entity = null;
		String result = null;
	  
		  // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("id", preferences.getID()));
	        nameValuePairs.add(new BasicNameValuePair("name",patientINFO.get(0)));
	        nameValuePairs.add(new BasicNameValuePair("address", patientINFO.get(1)));
	        nameValuePairs.add(new BasicNameValuePair("city", patientINFO.get(2)));
	        nameValuePairs.add(new BasicNameValuePair("pincode",patientINFO.get(3)));
	        nameValuePairs.add(new BasicNameValuePair("DOB", patientINFO.get(4)));
	        nameValuePairs.add(new BasicNameValuePair("phone", patientINFO.get(5)));

	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	         entity = response.getEntity();
	          result = EntityUtils.toString(entity);
	         System.out.println("sss"+result);
	        
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    if(entity != null)
			try {
				return result;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		else 
	    	return "null";
		return "null";
	}

	public String uploadFiles(String URL, File file, Context ctx) {
		Preferences preferences = Preferences.getInstance(context);
		try {

			HttpContext localContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(URL);
			
			httpPost.setHeader("Content-Disposition", "multipart/form-data");
			DefaultHttpClient httpClient = getHttpClient();
			
			MultipartEntity entity = new MultipartEntity();
			entity.addPart("filedata", new FileBody(file, "image/jpeg"));
			entity.addPart("id",new StringBody(preferences.getID()));
			entity.addPart("insurance_fileid",new StringBody("55599c61af1dd17d038b4567"));
			
			httpPost.setEntity(entity);
			
			HttpResponse response = httpClient.execute(httpPost, localContext);
			String responses = EntityUtils.toString(response.getEntity());
			System.out.println("response"+responses);
			return responses;
		} catch (Exception e) {
			Log.e(e.getClass().getName(), e.getMessage(), e);
			return null;
		}

	}

	public String postData(String url, String pickup_window, String date, Context context) {
		Preferences preferences = Preferences.getInstance(context);
		HttpEntity entity = null;
		String result = null;
	  
		  // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("userid", preferences.getID()));
	        nameValuePairs.add(new BasicNameValuePair("pickup_window", pickup_window));
	        nameValuePairs.add(new BasicNameValuePair("date", date));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	         entity = response.getEntity();
	          result = EntityUtils.toString(entity);
	         System.out.println("sss"+result);
	        
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    if(entity != null)
			try {
				return result;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		else 
	    	return "null";
		return "null";
	} 

	public String postData(String url, Context context,String userid) {
//		Preferences preferences = Preferences.getInstance(context);
		HttpEntity entity = null;
		String result = null;
	  
		  // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("userid", userid));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	         entity = response.getEntity();
	          result = EntityUtils.toString(entity);
	         System.out.println("sss"+result);
	        
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    if(entity != null)
			try {
				return result;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		else 
	    	return "null";
		return "null";
	}
}
