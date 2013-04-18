package com.example.mobileindia;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends Activity {

	EditText pnumber, msg;
	Button send;
	IntentFilter filter;
	public static String Title="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		
		filter = new IntentFilter();
		filter.addAction("SMS_Received");
		
		pnumber = (EditText)findViewById(R.id.editText1);
		msg = (EditText)findViewById(R.id.editText2);
		send = (Button)findViewById(R.id.button1);
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 // actual message that will be sent
				String myMsg = " Hey check out this post titled :" + "   "+ Title +"  "+ msg.getText().toString();
				String myNo = pnumber.getText().toString();
				
				sendMsg(myNo,myMsg);
			}			
		});
	}

	// method executed to share a post by SMS
	protected void sendMsg(String myNo, String myMsg) {
	
		String SENT = "Message sent";
		String DELIVERED = "Message delivered";
		
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
		PendingIntent deliPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
		
		//sends a Broadcast of message through Intent for Message sending
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "Msg Sent", Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(), "Generic Failure",Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), "No service",Toast.LENGTH_LONG).show();	
					break;
				}
				
			}
		},new IntentFilter(SENT));
		
		//sends a Broadcast of message through Intent for Message delivering
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "Msg Sent", Toast.LENGTH_LONG).show();
					break;
				
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(), "No SMS",Toast.LENGTH_LONG).show();	
					break;
				}
				
			}
		},new IntentFilter(DELIVERED));
		
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(myNo, null, myMsg, sentPI, deliPI); //uses the SMSManager method to send the SMS to the specified cell number
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sm, menu);
		return true;
	}

	protected void onResume()
	{
		super.onResume();
	}
}
