package com.example.mislistas;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ComplexList extends ListActivity {

		
	Node testValues[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.complex_list);
		testValues = new Node[3];
		testValues[0] = new Node ("URJC","Universidad Rey Juan Carlos", R.drawable.urjc);
		testValues[1] = new Node ("UC3M","Universidad Carlos III", R.drawable.uc3m);
		testValues[2] = new Node ("UPM","Universidad Politecnica de Madrid", R.drawable.upm);
		BaseAdapter adapter = new ComplexListAdapter(this, testValues); 
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(this, testValues[position].getDesc(), Toast.LENGTH_SHORT).show();
	}
}
