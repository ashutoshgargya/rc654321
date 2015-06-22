package com.revelcare.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.revelcare.R;
import com.revelcare.adapter.NewsItem;
import com.revelcare.bean.NewsDetails;
import com.revelcare.listener.ApiResponse;
import com.revelcare.task.BackgroungTask;
import com.revelcare.utills.RevelCareGlobal;
import com.revelcare.utills.RevelCareResponseParser;

public class News extends Fragment {

	private String error = "error", message = "message", ID = "_id",
			code = "code";
	private ArrayList<NewsDetails> News = new ArrayList<NewsDetails>();
	
	private ListView latest_articles;
	private TextView textView_header;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_news, container,
				false);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		init();
	}
	private void init() {
		latest_articles  = (ListView) getView().findViewById(R.id.listView_news);
		textView_header = (TextView) getView().findViewById(R.id.textView_header);
		textView_header.setPaintFlags(textView_header.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
		String pharmacies_url = RevelCareGlobal.Latest_News;
		getNews(pharmacies_url);
	}
	private void getNews(String url) {
		BackgroungTask task = new BackgroungTask(getActivity(), response,
				"Getting Latest News", false);
		task.execute(url);
	}

	private ApiResponse response = new ApiResponse() {

		@Override
		public void onSuccess(String response) {
			System.out.println("response" + response);
			if (response != null) {
				boolean jobject = response.startsWith("{");
				boolean jsarray = response.startsWith("[");

				if (jobject) {
					getError(response);
				}
				if (jsarray) {
					try {
						
						// Got News data,  parse it and show
						JSONArray NewsList = new JSONArray(response);
						RevelCareResponseParser parser = new RevelCareResponseParser();
						News = parser.getNews(NewsList);
						System.out.println("pharmacies size "+News.size());
						NewsItem items = new NewsItem(getActivity(), News);
						latest_articles.setAdapter(items);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}

		}

		@Override
		public void onFailure() {

		}
	};

	public void getError(String response) {
		try {

			JSONObject result = new JSONObject(response);
			if (result.has(error)) {
				String message = getErrorMessage(result);
				RevelCareGlobal.toastShow(message, getActivity()
						.getApplicationContext());
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private String getErrorMessage(JSONObject result) {
		String errors, server_message = null;
		try {
			if (result.has(error)) {
				errors = result.getString(error);
			}
			if (result.has(message)) {
				server_message = result.getString(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (server_message != null)
			return server_message;
		else
			return "null";
	}

}
