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
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SinglePostView extends ListActivity {
	
	public static String TITLE = "";
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
   static ArrayList<ArrayList<String>> listItems=new ArrayList<ArrayList<String>>();
   
    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    Item_Adapter adapter0;
  
    int clickCounter=0;

    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	
//    	try {
//			populate_list();
//		} catch (ParseException e) {
//			/// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
        super.onCreate(savedInstanceState);      
        setContentView(R.layout.activity_single_post_view);
        TextView title = (TextView) findViewById(R.id.singlePost_Title);
    	title.setText(TITLE);
    	
 
      // adapter0 = new Item_Adapter(this, R.layout.comment, listItems);
      //  setListAdapter(adapter0);
    	
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Log.v("Post", "POST : create ended    APPCLASS");
    }
    
   public static void  populate_list() throws ParseException{
	   
    	ParseQuery get = new ParseQuery("Post");
    	get.whereEqualTo("category", ListViewCategory.CATEGORY);
    	get.whereEqualTo("city", ListViewCategory.CITY);

    				List<ParseObject> objects = get.find();
    				listItems.clear();
    				
    				for (ParseObject parseObject : objects) {
    					ArrayList<String> temp = new ArrayList<String>();
    					temp.add(parseObject.getString("title"));
    					temp.add(parseObject.getString("summary"));
    					temp.add(parseObject.getString("author"));
    					listItems.add(temp);
    					
					}

    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
      
        Intent intent = new Intent(this, Add_Post.class);
		startActivity(intent);
       // adapter0.notifyDataSetChanged();
    }
    

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent back = new Intent(this,ListViewCategory.class);
        startActivity(back);
        return super.onOptionsItemSelected(item);
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

