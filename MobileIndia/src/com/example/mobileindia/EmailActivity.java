package com.example.mobileindia;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends Activity {

	Button sendEmailB;
	EditText msg;
	public static String Title="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
		
		sendEmailB = (Button)findViewById(R.id.SendEmail);
		sendEmailB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				msg = (EditText)findViewById(R.id.EmailMsg);
				String message = msg.getText().toString();
				sendEmail(message);
			}
		});
	}

	protected void sendEmail(String message) {
		// TODO Auto-generated method stub
		EditText toId = (EditText)findViewById(R.id.EmailTo);
		String Emailto = toId.getText().toString();
		String[] to = new String[]{Emailto};
		String subject = ("Mail from your app!") ;
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		message = message + Title;
		emailIntent.putExtra(Intent.EXTRA_TEXT, message);
		emailIntent.setType("message/rfc822");
		startActivity(Intent.createChooser(emailIntent, "Email"));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email, menu);
		return true;
	}
}