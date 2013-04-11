package com.example.mobileindia;


import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;

//This class adds posts to the back-end parse server
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
	
	
	public void AddComment(View view){
		// creates a new comment object
		ParseObject comment = new ParseObject("Comment");
		String add = "";
		// adds fields to the back-end parse object
		add = ((EditText) findViewById(R.id.comment_author_add)).getText().toString();
		comment.put("author", add);   
        add = ((EditText) findViewById(R.id.comment_content_add)).getText().toString();    
        comment.put("content", add);    
        Calendar cal = Calendar.getInstance(); 
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
        String date = month + "/" + dayofmonth + "/" + year;
        comment.put("date",date);
        //save the parse object
        comment.put("postNum", Single_Post.NUM);
        try {
        	comment.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //restart the single post view
        Intent back = new Intent(this,Single_Post.class);
        startActivity(back);
       
	}
	
	public void Cancel(View view){
		//go back to single post without adding comment
		Intent back = new Intent(this,Single_Post.class);
        startActivity(back);
        //
	}

}

