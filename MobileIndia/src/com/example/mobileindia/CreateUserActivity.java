package com.example.mobileindia;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class CreateUserActivity extends Activity {
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mPasswordConfirmView;
    private EditText mPhoneView;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_user);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_user, menu);
		return true;
	}
	 public void attemptCreateUser(View view){
		 mUsernameView = (EditText) findViewById(R.id.username);
		 mPasswordView = (EditText) findViewById(R.id.password_create);
		 mPasswordConfirmView = (EditText) findViewById(R.id.password_confirm_create);
		 mPhoneView = (EditText) findViewById(R.id.phone);
		 boolean cancel = false;
		 View focusView = null;
		 if (TextUtils.isEmpty(mUsernameView.getText().toString())){
				mUsernameView.setError(getString(R.string.error_field_required));
				focusView = mUsernameView;
				cancel = true;
		 }
		 if (TextUtils.isEmpty(mPasswordView.getText().toString())){
				mPasswordView.setError(getString(R.string.error_field_required));
				focusView = mPasswordView;
				cancel = true;
		 }
		 if (TextUtils.isEmpty(mPasswordConfirmView.getText().toString())){
				mPasswordConfirmView.setError(getString(R.string.error_field_required));
				focusView = mPasswordConfirmView;
				cancel = true;
		 }else if (!TextUtils.equals(mPasswordView.getText().toString(), mPasswordConfirmView.getText().toString())){
				mPasswordConfirmView.setError("password and password confirm must match");
				focusView = mPasswordConfirmView;
				cancel = true;
		 }
		 if(TextUtils.isEmpty(mPhoneView.getText().toString())){
				mPhoneView.setError("A phone number is required");
				focusView = mPhoneView;
				cancel = true;
		 }else if (!PhoneNumberUtils.isGlobalPhoneNumber(mPhoneView.getText().toString())){
				mPhoneView.setError("phone number is invalid");
				focusView = mPhoneView;
				cancel = true;
		 }

	     if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		 } else {
				// Show a progress spinner, and kick off a background task to
				// perform the user login attempt.
				//mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			//	showProgress(true);
				//mAuthTask = new UserLoginTask();
			//	mAuthTask.execute((Void) null);
			}
	 }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
