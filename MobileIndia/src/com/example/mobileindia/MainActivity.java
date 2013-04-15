package com.example.mobileindia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MainActivity extends Activity {
    public static boolean anonSuccess;
    public static boolean foundUserPostsSuccess;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Setup Parse Android SDK
	    Parse.initialize(this, "mxi3f2SxGpnfFCxW4FKz5D8f9i97BSVnLQ0PR6L9", "2VRMIqpKdwzfimui1EpUfwC6wUvlWHv2O84q87NC");
		setContentView(R.layout.activity_main);
	}
	protected void onResume (){
		super.onResume();
		rebuildMainMenuAndLabel();	
	}
	protected void onPostCreate (Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		rebuildMainMenuAndLabel();
	}
	/*
	 * This method is called during onResume() and onPostCreate() to rebuild the buttons on the main menu as
	 * well as the label on the top right of the view.  Depending on if the user is an anonymous user or not
	 * certain buttons should be hidden or displayed. An example of this is the 'Your Posts' button which does
	 * not show up on anonymous user but will on a real user.
	 */
	private void rebuildMainMenuAndLabel() {
		TextView t = (TextView) findViewById(R.id.mainActivityUserLabel);
		//If there is no signed in or anon user
		if (ParseUser.getCurrentUser() == null) {
			t.setText("Not logged in");
	    //If user is anonymous
		}else if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){ 
	    	t.setText("Logged in anonymously");
	    	// Hide sign up button
	    	findViewById(R.id.btnSignUp).setVisibility(8);
	    	TextView btnLogin = (TextView) findViewById(R.id.btnLogin);
	    	// Show search button
	    	findViewById(R.id.btnSearch).setVisibility(0);
	    	// Anon users should not be able to add posts
	    	ListViewCategory.hideAdd = true;
	    	// Change login button text
	    	btnLogin.setText("Logout");
	    // If user is a real user
	    }else {
	    	t.setText("Logged in as: " + ParseUser.getCurrentUser().getUsername());
	    	findViewById(R.id.btnSearch).setVisibility(0);
	    	findViewById(R.id.logged_in_user_posts_button).setVisibility(0);
	    	findViewById(R.id.btnSignUp).setVisibility(8);
	    	TextView btnLogin = (TextView) findViewById(R.id.btnLogin);
	    	btnLogin.setText("Logout");
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	/*
	 * Method called when user clicks login/logout button.  This method
	 * knows when a user is signed in or not and will take the correct action
	 * as described by the button text
	 */
	public void loginUserActivity(View view) {
		//If no current user then launch sign in activity
		 if(ParseUser.getCurrentUser() == null){
			 Intent i = new Intent(this, LoginActivity.class);
			 startActivity(i);
	     // Else logout user and rebuild menu options accordingly.
		 }else{
			 ParseUser.logOut();
			 // If not signed in you cannot add posts
			 ListViewCategory.hideAdd = true;
			 findViewById(R.id.btnSignUp).setVisibility(0);
			 TextView btnLogin = (TextView) findViewById(R.id.btnLogin);
			 TextView userLabel = (TextView) findViewById(R.id.mainActivityUserLabel);
		     findViewById(R.id.logged_in_user_posts_button).setVisibility(8);
		     findViewById(R.id.btnSearch).setVisibility(8);
		     // Change label and button text accordingly.
			 userLabel.setText("Not logged in");
		     btnLogin.setText("Login");
		 }
	 }
	 /*
	  * Browse button click action.  This method will fire the CitySelect activity and
	  * will sign in the user anonymously if he/she is not signed in already.
	  */
	 public void cityBrowse(View view){
		 final Context context = getApplicationContext();
		 final int duration = Toast.LENGTH_LONG;
		 final Intent i = new Intent(this, CitySelect.class);
		 // IMPORTANT: This setClickable will prevent people from firing the anon login multiple times in a row
		 findViewById(R.id.btnBrowse).setClickable(false);
		 if(ParseUser.getCurrentUser() == null){
			 // Anonymous user login action
			 ParseAnonymousUtils.logIn(new LogInCallback() {
				 @Override
				 public void done(ParseUser user, ParseException e) {
					 if (e == null){
						 // Anon login success
						 Toast toast = Toast.makeText(context, "Logged in as anonymous user", duration);
						 findViewById(R.id.btnBrowse).setClickable(true);
						 startActivity(i);
						 toast.show();
					 }else{
						 // Anon login failure
						 Toast toast = Toast.makeText(context, "Could not log in anonymously", duration);
						 findViewById(R.id.btnBrowse).setClickable(true);
						 startActivity(i);
						 toast.show();
					 }

				 }
			 });
		 }else{
			 // If user is signed in do not do anon
			 findViewById(R.id.btnBrowse).setClickable(true);
			 startActivity(i);
		 }
	 }

	 /*
	  * Signup button action, straightforward.
	  */
	 public void createUserActivity(View view){
		 Intent i = new Intent(this, CreateUserActivity.class);
		 startActivity(i);
	 }
	 /*
	  * 'Your Posts' button click action.  This method will load the users
	  * post and display them with the ListViewCategory Activity.  We
	  * use an override variable to prevent the default functionality of the
	  * ListViewCategory activity.
	  */
	 public void viewUserPosts(View view){
		 //Null out override variable
		 ListViewCategory.parsePostList = null;
		 // Initialize boolean
		 MainActivity.foundUserPostsSuccess = false;
		 // Set override variable for back button action, otherwise will not back to the 
		 // correct activity.
		 ListViewCategory.forceHome = true;
		 ParseQuery query = new ParseQuery("Post");
		 query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		 // Get posts associated with current user
		 query.whereEqualTo("user", ParseUser.getCurrentUser());
		 try {
			// Query success
			ListViewCategory.parsePostList = query.find();
			MainActivity.foundUserPostsSuccess = true;
		} catch (ParseException e1) {
			// Query failure
			ListViewCategory.parsePostList = null;
			MainActivity.foundUserPostsSuccess = false;
		}
		 if(MainActivity.foundUserPostsSuccess){
			 Intent i = new Intent(this,ListViewCategory.class);
			 // Should not see add button on 'Your posts' list
			 ListViewCategory.hideAdd = true;
			 startActivity(i);
		 }
	 }
	 /*
	  * Search posts button action
	  */
	 public void searchPosts(View view){
		Intent i = new Intent(this,SearchPostActivity.class);
		startActivity(i);
	 }
	 /*
	  * Do not allow back button on home menu.
	  * (non-Javadoc)
	  * @see android.app.Activity#onBackPressed()
	  */
	 @Override
	 public void onBackPressed() {
	 }
}
