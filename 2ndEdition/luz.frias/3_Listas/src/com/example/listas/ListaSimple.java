package com.example.listas;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListaSimple extends ListActivity {
	
	private String[] listaElementos = new String[] {"elemento1", "elemento2", "elemento3"};
	
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listasimple);

		ListView lv = (ListView) findViewById(android.R.id.list);
		ListAdapter la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaElementos);

		lv.setAdapter(la);
		

	}
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	super.onListItemClick(l, v, position, id);
    	
    	Toast.makeText(ListaSimple.this, "" + position + ": " + ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
    }
}
