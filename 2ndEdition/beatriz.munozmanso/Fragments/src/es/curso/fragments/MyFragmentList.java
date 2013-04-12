package es.curso.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyFragmentList extends ListFragment{
	
	private IListFragment listener;
	
	private static String IMAGEN1 = "img1";
	private static String IMAGEN2 = "img2";
	private static String IMAGEN3 = "img3";
	
	private static String[] TITLE ={
		IMAGEN1,
		IMAGEN2,
		IMAGEN3
	};
	
	private static int[] IMAGES = {
		R.drawable.ic_launcher,
		R.drawable.ic_launcher,
		R.drawable.ic_launcher
	};
	
	public MyFragmentList() {}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, TITLE));

		
		
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	
	}
	
}
