package es.android.curso.apps;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main extends ListActivity {

	public String[] valores = new String[] {
		"URJC",
		"UC3M",
		"Otro"
	};
	
	private ListView lv = null;
	private ListAdapter la = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		lv = (ListView) this.findViewById( android.R.id.list);
		la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valores);
		
		lv.setAdapter(la);
		 		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Toast.makeText(this, "Clicked on: " + valores[position], 
				Toast.LENGTH_SHORT).show();
	}

}
