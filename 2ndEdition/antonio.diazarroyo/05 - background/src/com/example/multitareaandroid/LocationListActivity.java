package com.example.multitareaandroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LocationListActivity extends ListActivity {

	private static List <String> locations = new ArrayList<String>();
	
	public LocationListActivity() {
		locations = new ArrayList<String>();
		String testValues[] = new String[] { "URJC", "UC3M", "UPM" };
		locations = Arrays.asList(testValues);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_list);
		String[] array = (String[])locations.toArray();
		String testValues[] = new String[] { "URJC", "UC3M", "UPM" };
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testValues);
		ListView listView = (ListView)findViewById(android.R.id.list);		
		listView.setAdapter(arrayAdapter);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
	}

	protected static void addLocation (String newLocation) {
		Log.d(MainActivity.TAG, MainActivity.TAG + " addLocation...");
		//locations.add("date " + new Date());
	}
	
}
