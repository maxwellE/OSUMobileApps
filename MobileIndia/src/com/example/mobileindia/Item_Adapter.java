package com.example.mobileindia;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Item_Adapter extends ArrayAdapter<ArrayList<String>> {

	// declaring our ArrayList of items
	private ArrayList<ArrayList<String>> objects;

	/* here we must override the constructor for ArrayAdapter
	* the only variable we care about now is ArrayList<Item> objects,
	* because it is the list of objects we want to display.
	*/
	
	public Item_Adapter(Context context, int textViewResourceId, ArrayList<ArrayList<String>> objects) {
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
			v = inflater.inflate(R.layout.list, null);
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

			TextView title = (TextView) v.findViewById(R.id.post_title_list);
			TextView summary = (TextView) v.findViewById(R.id.post_summary_list);
			TextView author = (TextView) v.findViewById(R.id.post_author_list);
			Button b = (Button) v.findViewById(R.id.full_post_button);
			
			
			// check to see if each individual textview is null.
			// if not, assign some text!
			if (title != null){
				title.setText(i.get(0));
			}
			if (summary != null){
				summary.setText(i.get(1));
			}
			if (author != null){
				author.setText(i.get(2));
			}
			//if (b != null){
				b.setHint(i.get(3));
			//}else{
				//b.setHint(null);
			//}
			
			//color the post
			int temp;
			if(position % 2 == 0){
				temp = Color.GRAY;
			}else{
				temp = Color.WHITE;
			}
			v.setBackgroundColor(temp);
		}

		// the view must be returned to our activity
		return v;

	}

}