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
	protected void onResume (){
		super.onResume();
		rebuildMainMenuAndLabel();	
	}
	protected void onPostCreate (Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		rebuildMainMenuAndLabel();
	}
	private void rebuildMainMenuAndLabel() {
		TextView t = (TextView) findViewById(R.id.mainActivityUserLabel);
		if (ParseUser.getCurrentUser() == null) {
			t.setText("Not logged in");
		}else if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){ 
	    	t.setText("Logged in anonymously");
	    	findViewById(R.id.btnSignUp).setVisibility(8);
	    	TextView btnLogin = (TextView) findViewById(R.id.btnLogin);
	    	btnLogin.setText("Logout");
	    }else {
	    	t.setText("Logged in as: " + ParseUser.getCurrentUser().getUsername());
	    	findViewById(R.id.btnSignUp).setVisibility(8);
	    	TextView btnLogin = (TextView) findViewById(R.id.btnLogin);
	    	btnLogin.setText("Logout");
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	 public void loginUserActivity(View view) {
		 if(ParseUser.getCurrentUser() == null){
			 Intent i = new Intent(this, LoginActivity.class);
			 startActivity(i);
		 }else{
			 ParseUser.logOut();
			 findViewById(R.id.btnSignUp).setVisibility(0);
			 TextView btnLogin = (TextView) findViewById(R.id.btnLogin);
			 TextView userLabel = (TextView) findViewById(R.id.mainActivityUserLabel);
			 userLabel.setText("Not logged in");
		     btnLogin.setText("Login");
		 }
	 }
	 
	 public void cityBrowse(View view){
		 final Context context = getApplicationContext();
		 final int duration = Toast.LENGTH_LONG;
		 final Intent i = new Intent(this, CitySelect.class);
		 if(ParseUser.getCurrentUser() == null || !ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
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
		 }else{
			 startActivity(i);
		 }
	 }

	 public void createUserActivity(View view){
		 Intent i = new Intent(this, CreateUserActivity.class);
		 startActivity(i);
	 }
	 
}
