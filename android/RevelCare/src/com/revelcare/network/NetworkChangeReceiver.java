package com.revelcare.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent intent) {

		String status = NetworkUtil.getConnectivityStatusString(context);
		// Wifi enabled
		if (status.equalsIgnoreCase("Wifi enabled")) {
			Toast.makeText(context, "Wifi is enabled", Toast.LENGTH_LONG).show();
		}
		// we can upload data from mobile data
		if (status.equalsIgnoreCase("Mobile data enabled")) {
			Toast.makeText(context, "Mobile data is enabled", Toast.LENGTH_LONG).show();
		}
		// Internet is not connected 
		if (status.equalsIgnoreCase("Not connected to Internet")) {
			Toast.makeText(context, "Not connected to Internet", Toast.LENGTH_LONG).show();
		}
	}
}// mobile data upload ends

