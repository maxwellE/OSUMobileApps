package com.example.mobileindia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends Activity {
    public static boolean anonSuccess;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    Parse.initialize(this, "dlhBQJUyZsOPxkFdp8Uf7MWXY7IpRWXXZipSyO8f", "sa5JGVnNQXEoWawJPxw5TKk1xLmFirXcGMr5P5JK");
		setContentView(R.layout.activity_main);
	}
	protected void onPostCreate (Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		TextView t = (TextView) findViewById(R.id.mainActivityUserLabel);
		if (ParseUser.getCurrentUser() == null) {
			t.setText("Not logged in");
		}else if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){ 
			t.setText("Logged in anonymously");
	    }else {
			t.setText("Logged in as: " + ParseUser.getCurrentUser().getUsername());
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	 public void loginUserActivity(View view) {
		 Intent i = new Intent(this, LoginActivity.class);
		 startActivity(i);
	 }
	 
	 public void cityBrowse(View view){
		 final Context context = getApplicationContext();
		 final int duration = Toast.LENGTH_LONG;
		 final Intent i = new Intent(this, CitySelect.class);
		 ParseAnonymousUtils.logIn(new LogInCallback() {
			 
			  @Override
			  public void done(ParseUser user, ParseException e) {
				  
				  if (e == null){
						Toast toast = Toast.makeText(context, "Logged in as anonymous user", duration);
						startActivity(i);
						toast.show();
				  }else{
						Toast toast = Toast.makeText(context, "Could not log in anonymously", duration);
						startActivity(i);
						toast.show();
				  }
					  
			  }
		 });
	 }

	 public void createUserActivity(View view){
		 Intent i = new Intent(this, CreateUserActivity.class);
		 startActivity(i);
	 }
	 
}
