package com.example.mobileindia;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.AbsListView.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class CitySelect extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_select);
		addCityButtons();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_city_select, menu);
		return true;
	}
	
	public void addCityButtons(){
		int i = 0;
		String[] cityName = new String[10];
//		cityName = getCity();
		
		cityName[0] = "BriansBurgh";
		cityName[1] = "Home Town";
		cityName[2] = "Cbus";
		cityName[3] = "But For";
		cityName[4] = "Ohio State";
		cityName[5] = "NoWhere";
		cityName[6] = "Nerk";
		cityName[7] = "Cray Town";
		cityName[8] = "Test";
		cityName[9] = "ADKFailsVille";
		Log.d("test", "bwb NOT in while citybutton add");
		while(i < 10){
				Log.d("test", "bwb in while citybutton add");
				Button cityButton = new Button(this);
				cityButton.setText(cityName[i]);

				LinearLayout ll = (LinearLayout)findViewById(R.id.CityList);
				LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				ll.addView(cityButton, lp);
				
				i++;
		}
		
	}
	
	public String[] getCity(){
		String[] city;
		city = new String[10];
		
		city[0] = "BriansBurgh";
		city[1] = "Home Town";
		city[2] = "Cbus";
		city[3] = "But For";
		city[4] = "Ohio State";
		city[5] = "NoWhere";
		city[6] = "Nerk";
		city[7] = "Cray Town";
		city[8] = "Test";
		city[9] = "ADKFailsVille";
		
		
		return city;
		
	}

}
