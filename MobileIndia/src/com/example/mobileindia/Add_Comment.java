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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class Add_Comment extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__comment);
	
	  // Make sure we're running on Honeycomb or higher to use ActionBar APIs
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//    		// Show the Up button in the action bar.
//    		getActionBar().setDisplayHomeAsUpEnabled(true);
//    	}
    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__comment, menu);
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
	
	
	public void AddComment(View view){
		ParseObject comment = new ParseObject("Comment");
		String add = "";
		add = ((EditText) findViewById(R.id.comment_author_add)).getText().toString();
		comment.put("author", add);
        
        add = ((EditText) findViewById(R.id.comment_content_add)).getText().toString();    
        comment.put("content", add);
        
        add = ((EditText) findViewById(R.id.comment_date_add)).getText().toString();   
        comment.put("date",add);
        
        comment.put("postNum", Single_Post.NUM);
       // post.saveInBackground();
        try {
        	comment.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Intent back = new Intent(this,Single_Post.class);
        startActivity(back);
       
	}
	
	public void Cancel(View view){
		//NavUtils.navigateUpFromSameTask(this);
		Intent back = new Intent(this,Single_Post.class);
        startActivity(back);
        //
	}

}

