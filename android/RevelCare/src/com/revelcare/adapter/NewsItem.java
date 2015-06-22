package com.revelcare.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.revelcare.R;
import com.revelcare.bean.NewsDetails;

public class NewsItem extends BaseAdapter {
	ArrayList<NewsDetails> News = new ArrayList<>();
	Context context;
	LayoutInflater inflater;

	public NewsItem(Context context, ArrayList<NewsDetails> News) {
		this.context = context;
		this.News = News;
		  inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return News.size();
	}

	@Override
	public Object getItem(int position) {
		return News.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.news_item, parent, false);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
				} else {
				holder = (ViewHolder) convertView.getTag();
				}
			
			holder.setChanges(News.get(position),position);
			return convertView;
	}

	private class ViewHolder{
		TextView topic, description;
		
		private ViewHolder(View view) {
			topic = (TextView) view.findViewById(R.id.textView_topic);
			description = (TextView) view.findViewById(R.id.textView_description);
		}
		
		
		public void setChanges(NewsDetails detail, int position) {
			topic.setText(detail.getTopic());
			description.setText(detail.getDescription());       
		}
	}


}
