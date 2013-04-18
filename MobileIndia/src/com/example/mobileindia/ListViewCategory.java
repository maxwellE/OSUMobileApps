package com.example.mobileindia;

import java.util.ArrayList;
import java.util.List;

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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

//this class fills the list_view_XML. It dynamically uses the ItemAdapter to 
//fill list found in activity_list_view_category
public class ListViewCategory extends ListActivity {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
   static ArrayList<ArrayList<String>> listItems=new ArrayList<ArrayList<String>>();
   public static String CATEGORY = "";
   public static String CITY = "";
   public static boolean hideAdd = false;
   public static boolean forceHome = false;
   public static List<ParseObject> parsePostList =  new ArrayList<ParseObject>();
   public static boolean forceSearch;
   
    
    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    Item_Adapter adapter0;
  
    int clickCounter=0;

    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	try {
    		//choose which way to populate list.
    		//This was done when Maxwell tried to use my code......
    		if(ListViewCategory.parsePostList != null){
    			populate_list(ListViewCategory.parsePostList);
    	    }else{
    	    	populate_list(null);
    	    }
		} catch (ParseException e) {
			/// TODO Auto-generated catch block
			e.printStackTrace();
		}
        super.onCreate(savedInstanceState);      
        setContentView(R.layout.activity_list_view_category);
        
        //define adapter to fill list element XML
        adapter0 = new Item_Adapter(this, R.layout.list, listItems);
        setListAdapter(adapter0); 
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    //hides addbutton if anonymous
    protected void onPostCreate (Bundle savedInstanceState){
 		super.onPostCreate(savedInstanceState);
 		if(ListViewCategory.hideAdd){
 			findViewById(R.id.addBtn).setVisibility(8);
 		}
    }
    
    /// choose between correct list filling function. Either for post search or regular post list
   public static void  populate_list(List<ParseObject> parsePostList2) throws ParseException{
	    if(parsePostList2 == null){
	    	defaultPopulateList();
	    }else{
			populateYourPosts(parsePostList2);
	    }

    }

   //populate the arraylist that will be sent to the itemAdapter
private static void defaultPopulateList() throws ParseException {
	ParseQuery get = new ParseQuery("Post");
	get.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
	get.whereEqualTo("category", ListViewCategory.CATEGORY);
	get.whereEqualTo("city", ListViewCategory.CITY);
				List<ParseObject> objects = get.find();
				listItems.clear();
				for (ParseObject parseObject : objects) {
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(parseObject.getString("title"));
					temp.add(parseObject.getString("summary"));
					temp.add(parseObject.getString("author"));
					
					temp.add(parseObject.getObjectId());
					temp.add(parseObject.getString("date"));
					listItems.add(temp);	
				}
}

//Populates List with posts that were searched for
private static void populateYourPosts(List<ParseObject> parsePostList2) {
	listItems.clear();
	for (ParseObject parseObject : parsePostList2) {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(parseObject.getString("title"));
		temp.add(parseObject.getString("summary"));
		temp.add(parseObject.getString("author"));
		//String num_string = Integer.toString(parseObject.getInt("post_num"));
		//temp.add(num_string);
		//change to object ID
		temp.add(parseObject.getObjectId());
		temp.add(parseObject.getString("date"));
		//Log.v("Post", "POST : post_num LIST   APPCLASS = " + num_string);
		listItems.add(temp);			
	}
}

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        Intent intent = new Intent(this, Add_Post.class);
		startActivity(intent);
        adapter0.notifyDataSetChanged();
    }
    
    // only for higher api
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent back = new Intent(this,Categories2.class);
        startActivity(back);
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (ListViewCategory.forceHome) {
        	Intent back = new Intent(this,MainActivity.class);
            startActivity(back);
        }else if (forceSearch) {
        	Intent back = new Intent(this,SearchPostActivity.class);
            startActivity(back);
            //override back button
		}else if(keyCode == KeyEvent.KEYCODE_BACK){
        	Intent back = new Intent(this,Categories2.class);
            startActivity(back);
        }
        ListViewCategory.forceSearch = false;
        ListViewCategory.forceHome = false;
        return super.onKeyDown(keyCode, event);
    }

    @Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public void onResume(){
		super.onResume();		
	}
	
	@Override
	public void onStop(){
		super.onStop();
	}
	
	// method to start the single post that was clicked
	public void SinglePost(View view){
			//the view is the button so objectId I sent as a hint
		 	Button title = (Button) view.findViewById(R.id.full_post_button);
		 	//send that id to single post
		 	Single_Post.NUM =  (String) title.getHint();
		 	if(Single_Post.NUM != null){
		 		Intent intent = new Intent(this, Single_Post.class);
				startActivity(intent);
		 	}
		}

	// same thing but removes the parse object from post
	public void DeletePost(View view){
		Button title = (Button) view.findViewById(R.id.button1);
	 	String id =  (String) title.getHint();
	 	if(id!= null){
	 		ParseQuery get = new ParseQuery("Post");
	    	get.whereEqualTo("objectId", id);
	    	List<ParseObject> objects = null;
			try {
				objects = get.find();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	ParseObject parseObject = objects.get(0);
			try {
				parseObject.delete();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	 		Intent intent = new Intent(this, ListViewCategory.class);
			startActivity(intent);
	 	}
	}

}

