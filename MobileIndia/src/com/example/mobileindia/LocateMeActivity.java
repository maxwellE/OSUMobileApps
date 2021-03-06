package com.example.mobileindia;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.example.mobileindia.GeoLocation;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LocateMeActivity extends MapActivity implements OnClickListener{

	public static String LocationNow = " ";
	//for post added by jeff
	public static boolean post_location = false;
	public static double post_long;
	public static double post_lat;
	private MapController whereAmIController=null;
	private EditText locationEditableField=null;
	private TextView myLocationField=null;
	MapView whereamiView=null;
	GeoLocation myGeoLocator = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locate_me);
		
		whereamiView = (MapView) findViewById(R.id.locateMe);
		whereamiView.setSatellite(true);
		whereamiView.setBuiltInZoomControls(true);
		whereAmIController = whereamiView.getController();
		
		myLocationField= (TextView)findViewById(R.id.my_location);
		View buttonLocateMe = findViewById(R.id.button_locate_me);
		buttonLocateMe.setOnClickListener(this);
		
		myLocationField.setText(LocationNow);
		
		locationEditableField= (EditText)findViewById(R.id.location);
		View buttonLocate = findViewById(R.id.button_locate);
		buttonLocate.setOnClickListener(this);
		
		myGeoLocator = new GeoLocation(this);
		
		if(post_location){
			post_location = false;
			Log.d(this.getClass().getName(), "back button pressed   " + post_long + "�pp Class");
			GeoPoint point = new GeoPoint((int) (post_lat * 1E6) , (int) (post_long * 1E6));
			
			MyLocationOverlay locationOverlay = new MyLocationOverlay(this, whereamiView);
			whereamiView.getOverlays().add(locationOverlay);
			locationOverlay.enableMyLocation();
		
			whereAmIController.setZoom(19);
			whereAmIController.animateTo(point);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_locate_me, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	switch((v.getId())){
	case R.id.button_locate_me:
			
		Location myLocation=null;
		myLocation = myGeoLocator.getBestCurrentLocation();   // gets d latest searched location by user 
		if(myLocation == null){
		myLocationField.setText(" Current Location not available.");
		}else{
			Toast.makeText(getBaseContext(), "Current Location", Toast.LENGTH_LONG).show();
			myLocationField.setText("Current Location");
			double latitude = myLocation.getLatitude();
			double longitude = myLocation.getLongitude();
			String lat = Double.toString(latitude);
			String lon = Double.toString(longitude);
			Log.v("Location latitude 1 :", lat);
			Log.v("Location longitude 1:", lon);
			Log.v("Location latitude 2 :" + latitude, "");
			Log.v("Location longitude 2:"+ longitude,"");
			
			GeoPoint point = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
			
			//set a pointer over the current user location 
			MyLocationOverlay locationOverlay = new MyLocationOverlay(this, whereamiView);
			whereamiView.getOverlays().add(locationOverlay);
			locationOverlay.enableMyLocation();
		
			whereAmIController.setZoom(19);
			whereAmIController.animateTo(point);
		}
		break;
		
	case R.id.button_locate:
	try{
			String locationName = this.locationEditableField.getText().toString();
			
			//finds the location according to the location specified by user
            GeoPoint point = myGeoLocator.getGeoPointFromName(locationName);
            double lat = point.getLatitudeE6()/1E6;
			double lon = point.getLongitudeE6()/1E6;
			
            Log.v("Location latitude find button:", lat+ "");
            Log.v("Location longitude find button :", lon+ "");
            //Log.v("Location latitude :" + latitude, "");
			//Log.v("Location longitude :"+ longitude,"");
            
            Toast.makeText(getBaseContext(), locationName, Toast.LENGTH_LONG).show();
            //set a pointer over the specified user location
            MyLocationOverlay locationOverlay = new MyLocationOverlay(this, whereamiView);
			whereamiView.getOverlays().add(locationOverlay);
			locationOverlay.enableMyLocation();
			
			whereAmIController.setZoom(19);
			whereAmIController.animateTo(point);
		} catch (Exception e){
			// do nothing
			e.printStackTrace();
		}
		break;
	}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}