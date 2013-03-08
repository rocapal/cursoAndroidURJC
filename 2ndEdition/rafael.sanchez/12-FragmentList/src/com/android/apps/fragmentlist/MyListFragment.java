package com.android.apps.fragmentlist;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MyListFragment extends ListFragment {

	private static String[] TITLES = {
		"Imagen1",
		"Imagen2",
		"Imagen3",
		"Imagen4",
		"Imagen5",
		"Imagen6"
	};
	
	private static int[] IMAGES = {
		R.drawable.image1,
		R.drawable.image2,
		R.drawable.image3,
		R.drawable.image4,
		R.drawable.image5,
		R.drawable.image6,		
	};

	
	public interface IListFragment
	{
		void itemClick (Integer imageResource);
	}
	
	
	public MyListFragment() {}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		
		super.onActivityCreated(savedInstanceState);

		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, TITLES));
				
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.i("MyListFragment", "Item clicked: " + id);
		
		IListFragment activity = (IListFragment) getActivity();
		if (activity != null)
			activity.itemClick(IMAGES[position]);
	}
}
