package com.example.mobileindia;

import com.parse.Parse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

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
		 Intent i = new Intent(this, CitySelect.class);
		 startActivity(i);
	 }

	 public void createUserActivity(View view){
		 Intent i = new Intent(this, CreateUserActivity.class);
		 startActivity(i);
	 }
	 
	 public void gotoCategoriesActivity(View view){
		 Intent i = new Intent(this, CategoriesActivity.class);
		 startActivity(i);
	 }
}
