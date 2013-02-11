package es.curso.listasimple;

import java.util.ArrayList;
import es.curso.listasimple.Item;
import android.app.ListActivity;
import android.os.Bundle;

public class listComp extends ListActivity{
	
	
	private static ArrayList<Item> mArray = new ArrayList<Item>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setData();

		MyAdapter mAdapter = new MyAdapter(this, mArray);
		setListAdapter(mAdapter);
		
	}

	private void setData() {
		// TODO Auto-generated method stub
		
	}
	
}


