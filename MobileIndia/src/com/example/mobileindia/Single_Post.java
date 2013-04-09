package com.example.mobileindia;

import java.util.ArrayList;
import java.util.List;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Single_Post extends ListActivity {

	public double longitude;
	public double latitude;
	public static String NUM = "";
	public static boolean hideAdd = false;
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    static ArrayList<ArrayList<String>> listItems=new ArrayList<ArrayList<String>>();
   
    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    CommentAdapter adapter0;
  
    int clickCounter=0;

    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.v("Single Post", "made it");
    	try {
			populate_list();
		} catch (ParseException e) {
			/// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        super.onCreate(savedInstanceState);      
        setContentView(R.layout.activity_single__post);
        Log.v("Single Post", "view set");
        ParseQuery get = new ParseQuery("Post");
    	get.whereEqualTo("post_num", Integer.parseInt(NUM));
    	
    	List<ParseObject> objects = null;
		try {
			objects = get.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	Log.v("Single Post", "got all parse objects");
    	ParseObject parseObject = objects.get(0);
        TextView title = (TextView) findViewById(R.id.singlePost_Title);
        TextView summary = (TextView) findViewById(R.id.singlePost_Summary);
        TextView author = (TextView) findViewById(R.id.single_Post_Author);
        TextView category = (TextView) findViewById(R.id.singlePostCategory);
        TextView number = (TextView) findViewById(R.id.singlePost_Number);
    	title.setText(parseObject.getString("title"));
    	summary.setText(parseObject.getString("summary"));
    	author.setText(parseObject.getString("author"));
    	category.setText(parseObject.getString("category"));
    	number.setText(NUM);
    	longitude = parseObject.getDouble("longitude");
    	latitude = parseObject.getDouble("latitude");
    	if(longitude == 0 || latitude == 0){
    		findViewById(R.id.button5).setVisibility(8);
    	}
		
		
       adapter0 = new CommentAdapter(this, R.layout.comment, listItems);
       setListAdapter(adapter0);
       
    	
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    
   public void shareSMS(View view) {
		// TODO Auto-generated method stub
	   TextView title = (TextView) findViewById(R.id.singlePost_Title);
		String titlestr = title.getText().toString();
		SMSActivity.Title=titlestr;
    	Intent SmsShare = new Intent(this, SMSActivity.class);
        startActivity(SmsShare);
        
    }
  
   public void shareEmail(View view) {
		// TODO Auto-generated method stub
	    TextView title = (TextView) findViewById(R.id.singlePost_Title);
		String titlestr = title.getText().toString();
		EmailActivity.Title=titlestr;
      	Intent EmailShare = new Intent(this, EmailActivity.class);
        startActivity(EmailShare);
       
   }
   
   public void shareLocation(View view) {
	   if(longitude == 0 || latitude == 0){
		   
	   }else{
		// TODO Auto-generated method stub
	    TextView location = (TextView) findViewById(R.id.button5);
		String locationstr = location.getText().toString();
		LocateMeActivity.LocationNow =locationstr;
		LocateMeActivity.post_long = longitude;
		LocateMeActivity.post_lat = latitude;
		LocateMeActivity.post_location = true;
     	Intent LocationShare = new Intent(this, LocateMeActivity.class);
        startActivity(LocationShare);
	   }
      
  }
    
   public static void  populate_list() throws ParseException{
	   
    	ParseQuery get = new ParseQuery("Comment");
    	get.whereEqualTo("postNum", NUM);

    				List<ParseObject> objects = get.find();
    				listItems.clear();
    				
    				for (ParseObject parseObject : objects) {
    					ArrayList<String> temp = new ArrayList<String>();
    					temp.add(parseObject.getString("date"));
    					temp.add(parseObject.getString("content"));
    					temp.add(parseObject.getString("author"));
    					listItems.add(temp);
    					
					}

    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
      
        Intent intent = new Intent(this, Add_Comment.class);
		startActivity(intent);
       // adapter0.notifyDataSetChanged();
    }
    
    protected void onPostCreate (Bundle savedInstanceState){
 		super.onPostCreate(savedInstanceState);
 		if(Single_Post.hideAdd){
 			findViewById(R.id.logged_in_user_posts_button).setVisibility(8);
 		}
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent back = new Intent(this,ListViewCategory.class);
        startActivity(back);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
           // Log.d(this.getClass().getName(), "back button pressed");
        	Intent back = new Intent(this,ListViewCategory.class);
            startActivity(back);
        }
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
	public void onPause(){
		super.onPause();
		//
		Log.v("LIST", "PAUSED LIST APPCLASS");

	}
	
	@Override
	public void onResume(){
		Log.v("List", "RESUMED List APPCLASS");
		super.onResume();
		//adapter0.notifyDataSetChanged();
		
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.v("LIST", "Stopped LIST APPCLASS");
	}
	

}

