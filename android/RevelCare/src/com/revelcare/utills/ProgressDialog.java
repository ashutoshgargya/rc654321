package com.revelcare.utills;

import com.revelcare.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class ProgressDialog extends Dialog {
TextView promt_message;
String message;
	public ProgressDialog(Context context,String message) {
		super(context);
		this.message = message;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.progress_dialog);
		promt_message = (TextView) findViewById(R.id.textView_pormtmsg);
		promt_message.setText(message);
		setCancelable(false);
	}
}
