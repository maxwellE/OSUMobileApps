package com.example.mobileindia;

import java.util.ArrayList;
import java.util.List;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;


public class Single_Post extends ListActivity {

	private static final ParseObject ParseObject = null;
	public double longitude;
	public double latitude;
	public static String NUM = "";
	public static boolean hideAdd = false;
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    static ArrayList<ArrayList<String>> listItems=new ArrayList<ArrayList<String>>();
   
    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    CommentAdapter adapter0;
  
    int clickCounter=0;
    
    private RatingBar ratebar;
    private TextView avgRating ;

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
    	get.whereEqualTo("objectId", NUM);
		get.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ONLY);
    	
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
        
        ratebar = (RatingBar) findViewById(R.id.rbSinglePost);
        ratebar.setRating((float) 0);
        
        try {
			setRating();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        addRatebarListner();
        
    }
    
    //Sets the Avg rating textview
   private void setRating() throws ParseException {
		
//	   ratebar = (RatingBar) findViewById(R.id.rbSinglePost);
	   avgRating = (TextView) findViewById(R.id.txtAvgRating);
	   float temp = 0;
	   temp = getRating();
	   avgRating.setText(Float.toString(temp) + " Avg Rating");
	   //TODO
	   ratebar = (RatingBar) findViewById(R.id.rbSinglePost);
	   
	   ratebar.setRating(temp);
	}

   private void addRatebarListner() {
	    
	   ratebar = (RatingBar) findViewById(R.id.rbSinglePost);
		
	   ratebar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
					setNewRating(rating);
				}
			});
	}
   
   public void setNewRating(float r){
	   
	   ratebar = (RatingBar) findViewById(R.id.rbSinglePost);
	   ratebar.setRating(r);
	   
	   Log.d("BTest","New rating of " + r + " starts");
	   
//	   try {
//		setRating();
//	   }catch (ParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	   }
	   
	   ParseUser user = ParseUser.getCurrentUser();
	   
	   String userid = user.getObjectId();
	   
	   ParseQuery pqRating = new ParseQuery("Rating");
	   
	   //pqRating.whereEqualTo("UserId", user.getObjectId());
	   pqRating.whereEqualTo("PostNum", NUM);
	   
	   List<ParseObject> objects = null;
	   try {
		   objects = pqRating.find();
	   } catch (ParseException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
		
	   Log.v("Single Post", "got all parse objects");
	   
//	   ParseObject postObj = objects.get(0);
	   ParseObject rateObj = ParseObject;
	   
	   for (ParseObject parseObject : objects) {
		   String temp = parseObject.getString("UserId");
		   
		   if (temp.equals(userid.toString())){
			   rateObj = parseObject;
		   }
		}
	   
	   if (rateObj != null){
		   rateObj.put("Rating", (Number) r);
		   rateObj.saveInBackground();
	   }
	   else{
		   ParseObject newPORating = new ParseObject("Rating");
		   newPORating.put("PostNum", NUM);
		   newPORating.put("Rating", (Number) r);
		   newPORating.put("UserId", userid);
		   newPORating.saveInBackground();
	   }
	   //JEFF
	   Intent LocationShare = new Intent(this, Single_Post.class);
       startActivity(LocationShare);
	   
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
	   // TextView location = (TextView) findViewById(R.id.button5);
		//String locationstr = location.getText().toString();
		//LocateMeActivity.LocationNow =locationstr;
		LocateMeActivity.post_long = longitude;
		LocateMeActivity.post_lat = latitude;
		LocateMeActivity.post_location = true;
     	Intent LocationShare = new Intent(this, LocateMeActivity.class);
        startActivity(LocationShare);
	   }
      
  }
   
   //Gets the Avg Rating for a single post
   public static float getRating() throws ParseException{
	   
	   ParseQuery get = new ParseQuery("Rating");
	   get.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ONLY);
	   get.whereEqualTo("PostNum", NUM);
	   
	   List<ParseObject> obj = get.find();
	   
	   float totalRatings = obj.size();
	   float Ratings = 0;
	   
	   if(totalRatings > 0){
		   for (ParseObject parseObject : obj) {
			   Number temp = parseObject.getNumber("Rating");
			   Ratings += temp.floatValue();
		   }
	   
		   Ratings /= totalRatings;
	   }
	   
	   return Ratings;
	   
   }
    
   public static void  populate_list() throws ParseException{
	   
    	ParseQuery get = new ParseQuery("Comment");
    	get.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ONLY);
    	get.whereEqualTo("postNum", NUM);

		List<ParseObject> objects = get.find();
		listItems.clear();
		
		for (ParseObject parseObject : objects) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(parseObject.getString("date"));
			temp.add(parseObject.getString("content"));
			temp.add(parseObject.getString("author"));
			temp.add(parseObject.getObjectId());
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
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.v("LIST", "Stopped LIST APPCLASS");
	}
	
	public void DeleteComment(View v){
		Button title = (Button) v.findViewById(R.id.button1);
	 	String id =  (String) title.getHint();
	 	if(id!= null){
	 		ParseQuery get = new ParseQuery("Comment");
	    	get.whereEqualTo("objectId", id);
	    	List<ParseObject> objects = null;
			try {
				objects = get.find();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	ParseObject parseObject = objects.get(0);
			parseObject.deleteEventually();		
	 		Intent intent = new Intent(this, Single_Post.class);
			startActivity(intent);
	 	}
	}
	
}


