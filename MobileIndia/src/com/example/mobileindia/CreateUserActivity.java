package com.example.mobileindia;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.mobileindia.LoginActivity.UserLoginTask;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.NavUtils;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateUserActivity extends Activity {
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mPasswordConfirmView;
    private EditText mPhoneView;
    private EditText mEmailView;
	private View mCreateUserFormView;
	private View mCreateUserStatusView;
	private TextView mCreateUserStatusMessageView;
	private UserCreateTask mAuthTask = null;
	private static boolean loginSuccess;
	public static int loginErrorCode;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_user);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		mCreateUserFormView = findViewById(R.id.create_user_form);
		mCreateUserStatusView = findViewById(R.id.create_user_status);
		mCreateUserStatusMessageView = (TextView) findViewById(R.id.create_user_status_message);
	}
	
	/**
	 * method is used for checking valid email id format.
	 * 
	 * @param email
	 * @return boolean true for valid false for invalid
	 */
	public static boolean isEmailValid(String email) {
	    boolean isValid = false;

	    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = email;

	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = true;
	    }
	    return isValid;
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
		 mEmailView = (EditText) findViewById(R.id.email);
		 boolean cancel = false;
		 View focusView = null;

		 if (TextUtils.isEmpty(mUsernameView.getText().toString())){
				mUsernameView.setError(getString(R.string.error_field_required));
				focusView = mUsernameView;

		 if (TextUtils.isEmpty(mEmailView.getText().toString())) {
				mEmailView.setError("An email is required");
				focusView = mEmailView;
				cancel = true;
		 }else if(!isEmailValid(mEmailView.getText().toString())){
			 	mEmailView.setError("A valid email is required");
				focusView = mEmailView;

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
		 if (TextUtils.isEmpty(mPasswordConfirmView.getText().toString())){
				mPasswordConfirmView.setError(getString(R.string.error_field_required));
				focusView = mPasswordConfirmView;
				cancel = true;
		 }else if (!TextUtils.equals(mPasswordView.getText().toString(), mPasswordConfirmView.getText().toString())){
				mPasswordConfirmView.setError("password and password confirm must match");
				focusView = mPasswordConfirmView;
				cancel = true;
		 }
		 if (TextUtils.isEmpty(mPasswordView.getText().toString())){
				mPasswordView.setError(getString(R.string.error_field_required));
				focusView = mPasswordView;
				cancel = true;
		 }
		 if (TextUtils.isEmpty(mUsernameView.getText().toString())){
				mUsernameView.setError(getString(R.string.error_field_required));
				focusView = mUsernameView;
				cancel = true;
		 }
	     if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		 } else {
				// Show a progress spinner, and kick off a background task to
				// perform the user login attempt.
				mCreateUserStatusMessageView.setText(R.string.login_progress_signing_in);
			    showProgress(true);
			    mAuthTask = new UserCreateTask();
			    mAuthTask.execute((Void) null);
			}
		 }
	 }

	/**
		 * Shows the progress UI and hides the login form.
		 */
		@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
		private void showProgress(final boolean show) {
			// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
			// for very easy animations. If available, use these APIs to fade-in
			// the progress spinner.
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				int shortAnimTime = getResources().getInteger(
						android.R.integer.config_shortAnimTime);

				mCreateUserStatusView.setVisibility(View.VISIBLE);
				mCreateUserStatusView.animate().setDuration(shortAnimTime)
						.alpha(show ? 1 : 0)
						.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								mCreateUserStatusView.setVisibility(show ? View.VISIBLE
										: View.GONE);
							}
						});

				mCreateUserFormView.setVisibility(View.VISIBLE);
				mCreateUserFormView.animate().setDuration(shortAnimTime)
						.alpha(show ? 0 : 1)
						.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								mCreateUserFormView.setVisibility(show ? View.GONE
										: View.VISIBLE);
							}
						});
			} else {
				// The ViewPropertyAnimator APIs are not available, so simply show
				// and hide the relevant UI components.
				mCreateUserStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
				mCreateUserFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			}
		}
		/**
		 * Represents an asynchronous login/registration task used to authenticate
		 * the user.
		 */
		public class UserCreateTask extends AsyncTask<Void, Void, Boolean> {
			@Override
			protected Boolean doInBackground(Void... params) {
				try {
					ParseUser.logIn("test_user", "");
					CreateUserActivity.loginSuccess = true;
				} catch (ParseException e) {
					Log.d("PARSE","Signup FAILED");
					Log.d("PARSE", "ERROR CODE: " + String.valueOf(e.getCode()));
					CreateUserActivity.loginErrorCode = e.getCode();
				}
				return CreateUserActivity.loginSuccess;
			}

			@Override
			protected void onPostExecute(final Boolean success) {
				mAuthTask = null;
				showProgress(false);
				if (success) {
					finish();
					Context context = getApplicationContext();
					int duration = Toast.LENGTH_LONG;
					Toast toast = Toast.makeText(context, "Successfully logged in!", duration);
					toast.show();
				} else {
					switch (CreateUserActivity.loginErrorCode) {
					case ParseException.OBJECT_NOT_FOUND:
						mUsernameView.setError("No account with this username and password found");
						mUsernameView.requestFocus();
						break;
					case ParseException.VALIDATION_ERROR:
						mPasswordView.setError("Invalid password.");
						mPasswordView.requestFocus();
					default:
						break;
					}
				}
			}

			@Override
			protected void onCancelled() {
				mAuthTask = null;
				showProgress(false);
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
