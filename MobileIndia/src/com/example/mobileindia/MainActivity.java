package com.example.mobileindia;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static boolean anonSuccess;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    Parse.initialize(this, "dlhBQJUyZsOPxkFdp8Uf7MWXY7IpRWXXZipSyO8f", "sa5JGVnNQXEoWawJPxw5TKk1xLmFirXcGMr5P5JK");
		setContentView(R.layout.activity_main);
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
		 MainActivity.anonSuccess = false;
		 ParseAnonymousUtils.logIn(new LogInCallback() {
			  @Override
			  public void done(ParseUser user, ParseException e) {
				  if (e == null){
					  MainActivity.anonSuccess = true;
				  }
			  }
		 });
			Context context = getApplicationContext();
			int duration = Toast.LENGTH_LONG;
			Intent i = new Intent(this, CitySelect.class);
			if (MainActivity.anonSuccess) {
				Toast toast = Toast.makeText(context, "Successfully logged in as anonymous user!", duration);
				startActivity(i);
				toast.show();
			} else {
				Toast toast = Toast.makeText(context, "Could not log in anonymously!", duration);
				startActivity(i);
				toast.show();
			}
	 }

	 public void createUserActivity(View view){
		 Intent i = new Intent(this, CreateUserActivity.class);
		 startActivity(i);
	 }
	 
}
