package com.example.appfragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListFragments extends ListFragment {

	private static String[] TITLES = {
		"Imagen 1",
		"Imagen 2",
		"Imagen 3",
		"Imagen 4",
		"Imagen 5",
		"Imagen 6",
		
	};
	private static int[] IMAGES = {
		R.drawable.imagen1,
		R.drawable.imagen2,
		R.drawable.imagen3,
		R.drawable.imagen4,
		R.drawable.imagen5,
		R.drawable.imagen6,
	};
	
	public MyListFragments() {
		
	};
	
	public interface IListFragment
	{
		void itemClick (Integer imagenResource);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, TITLES));
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		IListFragment activity = (IListFragment) getActivity();
	}
}
