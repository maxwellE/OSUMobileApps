package com.example.mobileindia;


import java.util.ArrayList;
import java.util.Calendar;

import android.location.Location;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseQuery;


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
	
	public void AddPost(View view){
		ParseObject post = new ParseObject("Post");
		String add = "";
		add = ((EditText) findViewById(R.id.post_title_add)).getText().toString();
        post.put("title", add);
        
        add = ((EditText) findViewById(R.id.post_summary_add)).getText().toString();    
        post.put("summary", add);
                
        
        add = ((EditText) findViewById(R.id.post_author_add)).getText().toString();   
        post.put("author",add);
        
        add = ListViewCategory.CATEGORY;      
        post.put("category",add);
        if(ParseUser.getCurrentUser() != null){
          post.put("user", ParseUser.getCurrentUser());
        }
        add = ListViewCategory.CITY;
        post.put("city", add);
        
        ParseQuery get = new ParseQuery("Post");
        int num = 0;
		try {
			num = get.count();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        post.put("post_num", num);
        if(ParseUser.getCurrentUser() != null && !ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
            post.put("user", ParseUser.getCurrentUser());
          }
        
        Calendar cal = Calendar.getInstance(); 

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
        
        String date = month + "/" + dayofmonth + "/" + year;
        post.put("date", date);
        
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
        
	    post.saveEventually();
	    
	    
        Intent back = new Intent(this,ListViewCategory.class);
        startActivity(back);
       
	}
	
	public void Cancel(View view){
		Intent back = new Intent(this,ListViewCategory.class);
        startActivity(back);
	}

}

