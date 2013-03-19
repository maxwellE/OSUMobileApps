package com.example.mobileindia;


import java.util.ArrayList;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Add_Post extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__post);
	
	  // Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    		// Show the Up button in the action bar.
    		getActionBar().setDisplayHomeAsUpEnabled(true);
    	}
    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__post, menu);
		return true;
	}
	
	  @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case android.R.id.home:
	            NavUtils.navigateUpFromSameTask(this);
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	
	
	public void AddPost(View view){
		ArrayList<String> temp = new ArrayList<String>();
		String add = "";
		add = ((EditText) findViewById(R.id.editText2)).getText().toString();
        temp.add(add);
        add = ((EditText) findViewById(R.id.editText3)).getText().toString();
        temp.add(add);
        add = ((EditText) findViewById(R.id.editText1)).getText().toString();
        temp.add(add);
        ListViewCategory.listItems.add(temp);
        NavUtils.navigateUpFromSameTask(this);
       
	}
	
	public void Cancel(View view){
		NavUtils.navigateUpFromSameTask(this);
	}

}

