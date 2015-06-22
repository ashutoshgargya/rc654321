package com.revelcare.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.revelcare.R;
import com.revelcare.bean.PerscribedMedicine;

public class MedicineItem extends BaseAdapter {
	ArrayList<PerscribedMedicine> medicines = new ArrayList<>();
	LayoutInflater inflater;
	Context context;

	public MedicineItem(Context context, ArrayList<PerscribedMedicine> medicines) {
		this.context = context;
		this.medicines = medicines; 
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return medicines.size();
	}

	@Override
	public Object getItem(int position) {
		return medicines.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
	if(convertView == null){
		convertView = inflater.inflate(R.layout.medicien_item, parent,false);
		holder = new ViewHolder(convertView);
		convertView.setTag(holder);
	}else{
		holder = (ViewHolder) convertView.getTag();
	}
	holder.setChanges(medicines.get(position),position);
	return convertView;
	}

	private class ViewHolder{
		private TextView dosage, medicine, other;

		private ViewHolder(View view) {
			dosage = (TextView) view.findViewById(R.id.textView_dosage);
			medicine = (TextView) view.findViewById(R.id.textView_information);
			other = (TextView) view.findViewById(R.id.textView_other);
		}
		
		public void setChanges(PerscribedMedicine medicine_pres, int position){
			dosage.setText("Dosage:"+medicine_pres.getDosage());
			medicine.setText(medicine_pres.getName());
			other.setText("over the counter drug");
		}
	}
}
