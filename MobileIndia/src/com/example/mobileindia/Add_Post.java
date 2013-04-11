package com.example.mobileindia;


import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

//this class is in charge of adding post objects to the back end
public class Add_Post extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__post);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__post, menu);
		return true;
	}
	//does the adding
	public void AddPost(View view){
		//create post parse object
		ParseObject post = new ParseObject("Post");
		String add = "";
		//get all info stored
		add = ((EditText) findViewById(R.id.post_title_add)).getText().toString();
        post.put("title", add);
        add = ((EditText) findViewById(R.id.post_summary_add)).getText().toString();    
        post.put("summary", add);
        add = ((EditText) findViewById(R.id.post_author_add)).getText().toString();   
        post.put("author",add); 
        add = ListViewCategory.CATEGORY;      
        post.put("category",add);
        //slightly different call that uses current user
        if(ParseUser.getCurrentUser() != null){
          post.put("user", ParseUser.getCurrentUser());
        }
        add = ListViewCategory.CITY;
        post.put("city", add);   
        //use calender to get date
        Calendar cal = Calendar.getInstance(); 
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
        String date = month + "/" + dayofmonth + "/" + year;
        post.put("date", date);
        //get current location if gps available
        Location myLocation=null;
        GeoLocation myGeoLocator = new GeoLocation(this);
		myLocation = myGeoLocator.getBestCurrentLocation();
		if(myLocation == null){
    		//myLocationField.setText(" Current Location not available.");
    		}else{
    			double latitude = myLocation.getLatitude();
    			double longitude = myLocation.getLongitude();
				post.put("longitude", longitude);
				post.put("latitude", latitude);
    		}
        
		// save next time there is Internet
	    post.saveEventually();
	    
	    //launch list of posts
        Intent back = new Intent(this,ListViewCategory.class);
        startActivity(back);
       
	}
	// do not add anything
	public void Cancel(View view){
		Intent back = new Intent(this,ListViewCategory.class);
        startActivity(back);
	}

}

