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



public class CitySelect extends Activity implements OnClickListener {

	public double longitude;
	public double latitude;
	
	//Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_select);
//		addCityButtons();

	}
	@Override
	protected void onPostCreate (Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		//TextView labelUser = (TextView) findViewById(R.id.loginUserLabelCity);
		//labelUser.setText(ParseUser.getCurrentUser().getUsername());
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
	
	//City selected
	public void CatAct(View v){
		Intent i = new Intent(this, Categories2.class);
		String cityName = "";
		try{
			cityName = (String) v.getTag();
		} catch(Exception e){
			Log.e("catAct", "Error getting city name");
		}
		Log.d("BTest","Clicked " + cityName);
		ListViewCategory.CITY = cityName;
		startActivity(i);
		
	 }
	
	public void LocateMe(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, LocateMeActivity.class);
		
		String cityName = "";
		try{
			cityName = (String) v.getTag();
		} catch(Exception e){
			Log.e("catAct", "Error getting city name");
		}
		Log.d("BTest","Clicked Locate" + cityName);
		ListViewCategory.CITY = cityName;
		
		cityLocal(v);
		
		if(longitude == 0 || latitude == 0){
			   
		   }else{
			// TODO Auto-generated method stub
			 
			String locationstr = v.getTag().toString();
			LocateMeActivity.LocationNow =locationstr;
			LocateMeActivity.post_long = longitude;
			LocateMeActivity.post_lat = latitude;
			LocateMeActivity.post_location = true;
			
			startActivity(i);
		   }
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, LocateMeActivity.class);
		startActivity(intent);				
	}
	
	//Cords for city
	public void cityLocal(View v){
		String cityName = v.getTag().toString();
		
		if (cityName.equals("Delhi")){
			longitude = 29.0167;
			latitude = 77.3833;
		}else if(cityName.equals("Mumbai")){
			longitude = 18.9647;
			latitude = 72.8258;
		}else if(cityName.equals("Kolkata")){
			longitude = 22.5697;
			latitude = 88.3697;
		}else if(cityName.equals("Chennai")){
			longitude = 13.0810;
			latitude = 80.2740;
		}else if(cityName.equals("Banglore")){
			longitude = 12.9833;
			latitude = 77.5833;
		}else if(cityName.equals("Pune")){
			longitude = 18.5236;
			latitude = 73.8478;
		}else if(cityName.equals("Nagpur")){
			longitude = 21.1438;
			latitude = 79.0926;
		}else if(cityName.equals("Indore")){
			longitude = 22.7287;
			latitude = 75.8654;
		}else if(cityName.equals("Jaipur")){
			longitude = 26.9200;
			latitude = 75.8200;
		}else if(cityName.equals("Kanpur")){
			longitude = 26.4583;
			latitude = 80.3173;
		}
		else{
			longitude = 0;
			latitude = 0;
		}
	}

}
