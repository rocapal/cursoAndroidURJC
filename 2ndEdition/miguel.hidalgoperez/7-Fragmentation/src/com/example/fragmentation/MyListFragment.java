package com.example.fragmentation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListFragment extends FragmentActivity{
	
	private static int[] IMAGES = {
		R.drawable.ic_launcher,
		R.drawable.ic_launcher
	};
	
	private static String[] TITLES = {
		"t1","t2"
	};
	
	public interface IListFragment{
		void itemClick (Integer imageResource);
	}
	
	public MyListFragment() {}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, TITLES));
	}
	
	public void inListItemClick(ListView l, View v, int position, long id){
		Log.i("MyListFragment", "Item clicked" + id);
		
		IListFragment activity = (IListFragment) getActivity();
		if ()
	}
	
}
