package com.example.mobileindia;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;



public class CategoriesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);
		
//		Bundle extras = getIntent().getExtras();
//		if (extras != null) {
//		    String value = extras.getString("CityName");
//		    
//		    Context context = getApplicationContext();
//		    CharSequence text = "You Clicked" + value;
//		    int duration = Toast.LENGTH_SHORT;
//
//		    Toast toast = Toast.makeText(context, text, duration);
//		    toast.show();
//		    
//		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// Git test
		getMenuInflater().inflate(R.menu.activity_catagories, menu);
		return true;
	}

}
