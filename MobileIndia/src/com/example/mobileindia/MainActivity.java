package com.example.mobileindia;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	 public void loginButton(View view) {
	     
	 }
	 public void browseButton(View view) {
		 Intent i = new Intent(this, CategoriesActivity.class);
		 startActivity(i); 
	 }
	 
	 public void cityBrowse(View view){
		 Intent i = new Intent(this, CitySelect.class);
		 startActivity(i);
	 }
}
