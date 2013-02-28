package es.android.listas;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Lista extends ListActivity {

	private String[] testValues = new String[] {
			"URJC",
			"EDI",
			"Android"
	};
	
	private ListView lv1= null;
	private ListAdapter la1 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista);
		
		lv1 = (ListView) findViewById(android.R.id.list);
		la1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,testValues);
		lv1.setAdapter(la1);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Toast.makeText(this, String.valueOf(position) + "- " +testValues[position] , 
				Toast.LENGTH_SHORT).show();
	}

}
