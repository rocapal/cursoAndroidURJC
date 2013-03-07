package com.example.fragments.withoutfragments;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fragments.R;

public class ListNoFragmentsActivity extends ListActivity {

	private ListNoFragmentsActivity context;
	
	public static final String[] IMAGES_TXT = {
		"image 1",
		"image 2",
		"image 3",
		"image 4",
		"image 5",
		"image 6",
		"image 7"};
	
	public static final Integer[] IMAGES_SRC = {
		R.drawable.image_1,
		R.drawable.image_2,
		R.drawable.image_3,
		R.drawable.image_4,
		R.drawable.image_5,
		R.drawable.image_6,
		R.drawable.image_7,};

	public static final String ID_IMAGE = "ID_IMAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.no_fragments_list_activity);
		context = this;
		ListView listView = (ListView)findViewById(android.R.id.list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, IMAGES_TXT);
		listView.setAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(context, ImagenNoFragmentsActivity.class);
		intent.putExtra(ID_IMAGE, position);
		startActivity(intent);
	}
	
}
