package com.example.listas;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SimpleList extends ListActivity {

	private String[] testValues = new String[] { "URJC", "EOI", "Android" };

	private ListView lvl = null;
	private ListAdapter lal = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_simplelist);
	    
	    Log.d("OnCreate","lvl");
		lvl = (ListView) findViewById(android.R.id.list);
		Log.d("OnCreate","lal");
		lal = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, testValues);
		Log.d("OnCreate","set lal to lvl");
		lvl.setAdapter(lal);
	    // TODO Auto-generated method stub
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
				Toast.makeText(this, "Seleccionado el elemento " + position + " - "
				+ testValues[position], Toast.LENGTH_SHORT).show();
	}
}
