package com.example.mobileindia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;



public class CitySelect extends Activity implements OnClickListener {

	public double longitude;
	public double latitude;
	
	//Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_select);

	}
	@Override
	protected void onPostCreate (Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_city_select, menu);
		return true;
	}

	//Back button tap
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
        	Intent back = new Intent(this,MainActivity.class);
            startActivity(back);
        }
        Log.d(this.getClass().getName(), "back button pressed");
        return super.onKeyDown(keyCode, event);
    }
	
	//City click to next screen
	public void CatAct(View v){
		Intent i = new Intent(this, Categories2.class);
		String cityName = "";
		//Gets name of city from the tag attribute of the button
		try{
			cityName = (String) v.getTag();
		} catch(Exception e){
			Log.e("catAct", "Error getting city name");
		}
		Log.v("BTest","Clicked " + cityName);
		ListViewCategory.CITY = cityName;
		startActivity(i);
		
	 }
	
	//Launch LocateMe, Pass location
	public void LocateMe(View v) {
		// TODO Auto-generated method stub		
		String cityName = "";
		//Gets name of city from the tag attribute of the button
		try{
			cityName = (String) v.getTag();
		} catch(Exception e){
			Log.e("catAct", "Error getting city name");
		}
		Log.d("BTest","Clicked Locate" + cityName);
		ListViewCategory.CITY = cityName;
		
		//Get Lat Lon
		cityLocal(v);

		Log.v("Location longitude 0:", latitude + "App Class");
		Log.v("Location longitude 0:", longitude + "App Class");
	
		//Setup for locate me
		if(longitude == 0 || latitude == 0){
			Toast.makeText(getBaseContext(),"Cannot locate the city. ", Toast.LENGTH_LONG).show();			   
		   }else{
			 
			String locationstr = v.getTag().toString();
			LocateMeActivity.LocationNow =locationstr;
			LocateMeActivity.post_long = longitude;
			LocateMeActivity.post_lat = latitude;
			LocateMeActivity.post_location = true;
			Toast.makeText(getBaseContext(), cityName, Toast.LENGTH_LONG).show();
			Intent i1 = new Intent(this, LocateMeActivity.class);
			startActivity(i1);
		   }
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, LocateMeActivity.class);
		startActivity(intent);				
	}
	
	//Lat lon for city
	public void cityLocal(View v){
		String cityName = v.getTag().toString();
		Log.d("BTest", cityName);
		if (cityName.equals("Delhi")){
			latitude  = 28.635308;
			longitude    = 77.224960;
		}else if(cityName.equals("Mumbai")){
			 latitude = 18.9647;
			 longitude = 72.8258;
		}else if(cityName.equals("Kolkata")){
			 latitude = 22.5697;
			 longitude = 88.3697;
		}else if(cityName.equals("Chennai")){
			 latitude = 13.0810;
			 longitude = 80.2740;
		}else if(cityName.equals("Banglore")){
			 latitude = 12.9833;
			 longitude = 77.5833;
		}else if(cityName.equals("Pune")){
			latitude = 18.5236;
			longitude = 73.8478;
		}else if(cityName.equals("Nagpur")){
			latitude = 21.1438;
			longitude = 79.0926;
		}else if(cityName.equals("Indore")){
			 latitude = 22.7287;
			 longitude = 75.8654;
		}else if(cityName.equals("Jaipur")){
			 latitude = 26.9200;
			 longitude= 75.8200;
		}else if(cityName.equals("Kanpur")){
			 latitude= 26.4583;
			 longitude = 80.3173;
		}
		else{
			longitude = 0;
			latitude = 0;
		}
		Log.d("BTest", longitude + " " + latitude);
	}

}
