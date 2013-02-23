package com.example.list;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SimpleClass extends ListActivity{
	
	private String[] testValues = new String[] {
			"URJC","UPM", "UCM"
	};
	private ListView lv1 = null;
	private ListAdapter la1 = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		lv1 = (ListView)findViewById(android.R.id.list);
		la1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				                       testValues);
		lv1.setAdapter(la1);
		registerForContextMenu(this.getListView());
	}
	
	protected void onListItemClick (ListView l, View v, int position, long id){
		Toast.makeText(this, String.valueOf(position)+ " - " +
	                   testValues[position], Toast.LENGTH_SHORT).show();
	}
	
	/*
	public boolean onContextItemSelected(MenuItem item){
		AdapterContextMenuInfo info
	}*/
}
