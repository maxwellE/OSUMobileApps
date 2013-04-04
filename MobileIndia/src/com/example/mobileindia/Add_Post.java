package com.example.mobileindia;


import java.util.ArrayList;
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
	
	  // Make sure we're running on Honeycomb or higher to use ActionBar APIs
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//    		// Show the Up button in the action bar.
//    		getActionBar().setDisplayHomeAsUpEnabled(true);
//    	}
    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__post, menu);
		return true;
	}
	
//	  @Override
//	    public boolean onOptionsItemSelected(MenuItem item) {
//	        switch (item.getItemId()) {
//	        case android.R.id.home:
//	            NavUtils.navigateUpFromSameTask(this);
//	            return true;
//	        }
//	        return super.onOptionsItemSelected(item);
//	    }
	
	
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
        if(ParseUser.getCurrentUser() != null && !ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){
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
	    post.saveEventually();
        Intent back = new Intent(this,ListViewCategory.class);
        startActivity(back);
       
	}
	
	public void Cancel(View view){
		//NavUtils.navigateUpFromSameTask(this);
		Intent back = new Intent(this,ListViewCategory.class);
        startActivity(back);
        //
	}

}

