package com.example.mislistas;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EasyList extends ListActivity {

	String testValues[] = new String[] { "URJC", "UC3M", "UPM" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.easy_list);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testValues);
		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setAdapter(arrayAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(this, "Hola " + testValues[position], Toast.LENGTH_SHORT).show();
	}
}
