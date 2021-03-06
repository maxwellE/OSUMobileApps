package com.example.mobileindia;

import java.util.ArrayList;

import com.parse.ParseObject;
import com.parse.ParseUser;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

//This class dynamically fills the fields in comment.xml
public class CommentAdapter extends ArrayAdapter<ArrayList<String>> {

	// declaring our ArrayList of items
	private ArrayList<ArrayList<String>> objects;

	/* here we must override the constructor for ArrayAdapter
	* the only variable we care about now is ArrayList<Item> objects,
	* because it is the list of objects we want to display.
	*/
	
	public CommentAdapter(Context context, int textViewResourceId, ArrayList<ArrayList<String>> objects) {
		super(context, textViewResourceId, objects);
		this.objects = objects;		
	}

	/*
	 * we are overriding the getView method here - this is what defines how each
	 * list item will look.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent){

		// assign the view we are converting to a local variable
		View v = convertView;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.comment, null);
		}

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 * 
		 * Therefore, i refers to the current Item object.
		 */
		ArrayList<String> i = objects.get(position);

		if (i != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.

			TextView date = (TextView) v.findViewById(R.id.commentDate);
			TextView content = (TextView) v.findViewById(R.id.commentContent);
			TextView author = (TextView) v.findViewById(R.id.commentAuthor);
			Button b = (Button) v.findViewById(R.id.button1);
			
			// check to see if each individual textview is null.
			// if not, assign some text!
			if (date != null){
				date.setText(i.get(0));
			}
			if (content != null){
				content.setText(i.get(1));
			}
			if (author != null){
				author.setText(i.get(2));
			}
			// needed to set the button hint because the view becomes unreachable
			if (b != null){
				b.setHint(i.get(3));
			}
			
			
			//color the post
			int temp;
			if(position % 2 == 0){
				temp = Color.GRAY;
			}else{
				temp = Color.WHITE;
			}
			v.setBackgroundColor(temp);
			
			// hide delete button if not super user
			if(ParseUser.getCurrentUser() != null){
	 			ParseObject obj = ParseUser.getCurrentUser();
	 			
	 			if(!obj.getBoolean("SUPER")){
	 				v.findViewById(R.id.button1).setVisibility(8);
	 			}
	 		}
		}

		// the view must be returned to our activity
		return v;

	}

}