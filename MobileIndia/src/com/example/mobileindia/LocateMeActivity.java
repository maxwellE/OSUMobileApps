package com.example.mobileindia;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.example.mobileindia.GeoLocation;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class LocateMeActivity extends MapActivity implements OnClickListener{


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
		
		locationEditableField= (EditText)findViewById(R.id.location);
		View buttonLocate = findViewById(R.id.button_locate);
		buttonLocate.setOnClickListener(this);
		
		myGeoLocator = new GeoLocation(this);
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
		myLocation = myGeoLocator.getBestCurrentLocation();
		if(myLocation == null){
		myLocationField.setText(" Current Location not available.");
		}else{
			
			myLocationField.setText(" Current Location");
			double latitude=myLocation.getLatitude();
			double longitude=myLocation.getLongitude();
			GeoPoint point = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
			
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
			
            GeoPoint point = myGeoLocator.getGeoPointFromName(locationName);
            
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
