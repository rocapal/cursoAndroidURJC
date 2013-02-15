package es.curso.listasimple;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class listSimp extends ListActivity{
	private String[] values = new String[] {
			"Casa",
			"Perro",
			"Coche"
		};

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			ListView lview = (ListView) findViewById(android.R.id.list);
			
			ListAdapter ladap = new ArrayAdapter<String>(this, 
					android.R.layout.simple_list_item_1, values);
			
			lview.setAdapter(ladap);
			
		}
		
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(this, position + "_" + values[position], Toast.LENGTH_SHORT).show();
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_main, menu);
			return true;
		}

}
