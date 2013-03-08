package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListFragment extends ListFragment {
	private static String[] TITLES = {
		"Image1",
		"Image2"
	};
	
	private static int[] IMAGES = {
		R.drawable.ic_launcher,
		R.drawable.ic_launcher
	};
	
	public MyListFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, TITLES));
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		IListFragment activity = (IListFragment) getActivity();
		activity.onClickItem(IMAGES[position]);
	}
}
