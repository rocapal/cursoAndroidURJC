package com.example.listas;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class AdvancedList extends ListActivity {

	private MyAdapter mAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advancedlist);

		mAdapter = new MyAdapter(this);
		setData();
		setListAdapter(mAdapter);

	}

	private void setData() {
		for (Integer i = 0; i <= 5; i++)
			mAdapter.datos.add(mAdapter.new Node("Nodo " + i.toString(),
					"Este es el nodo" + i.toString(), R.drawable.ic_launcher));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(this,"Pulsado el item en la posición " + position, Toast.LENGTH_SHORT).show();
	}
}
