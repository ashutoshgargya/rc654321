package com.revelcare.utills;

import com.revelcare.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MessageDialog extends Dialog  implements OnClickListener{
	TextView msg_txt;
	String message;
	Button btn_okay;

	public MessageDialog(Context context, String message) {
		super(context);
		this.message = message;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.msg_dialog);
		msg_txt = (TextView) findViewById(R.id.textView_message);
		msg_txt.setText(message);
		btn_okay = (Button) findViewById(R.id.btn_ok);
		btn_okay.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		dismiss();		
	}

}
