package com.example.mobileindia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CitySelect extends Activity implements OnClickListener {

	//Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_select);
		addCityButtons();

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
	
	public void addCityButtons(){
		int i = 0;
		String[] cityName = new String[10];
		cityName = getCity();
		
		while(i < 10){
				final String NameOfCity = cityName[i];
				Button cityButton = new Button(this);
				cityButton.setText(NameOfCity);
				Button cityLocate = new Button(this);
				cityLocate.setText("LocateMe");
				cityLocate.setOnClickListener(this);
				
				cityButton.setOnClickListener(new Button.OnClickListener() {
					
				    public void onClick(View v) {
				    	CatAct(v, NameOfCity);
				    }
				});
					
				LinearLayout ll = (LinearLayout)findViewById(R.id.CityList);
				LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				ll.addView(cityButton, lp);
				ll.addView(cityLocate);
								
				i++;
		}		
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
           // Log.d(this.getClass().getName(), "back button pressed");
        	Intent back = new Intent(this,MainActivity.class);
            startActivity(back);
        }
        return super.onKeyDown(keyCode, event);
    }
	
	public void CatAct(View view, String cityName){
		Intent i = new Intent(this, Categories2.class);
		//i.putExtra("CityName", cityName);
		ListViewCategory.CITY = cityName;
		startActivity(i);
	 }
	
	public String[] getCity(){
		String[] city;
		city = new String[10];
		
		city[0] = "Delhi";
		city[1] = "Mumbai";
		city[2] = "Kolkata";
		city[3] = "Chennai";
		city[4] = "Banglore";
		city[5] = "Pune";
		city[6] = "Nagpur";
		city[7] = "Indore";
		city[8] = "Jaipur";
		city[9] = "Kanpur";
		
		
		return city;
		
	}
	
/*	public void sendMessage(View view) {

		// Do something in response to button
		Intent intent = new Intent(this, LocateMeActivity.class);
		startActivity(intent);
	}*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stu
		
		Intent intent = new Intent(this, LocateMeActivity.class);
		startActivity(intent);				
	}

}
