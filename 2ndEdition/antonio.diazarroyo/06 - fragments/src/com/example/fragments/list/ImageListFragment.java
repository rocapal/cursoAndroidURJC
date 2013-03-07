package com.example.fragments.list;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fragments.R;

public class ImageListFragment extends ListFragment {

	public static final String[] IMAGES_TXT = {
		"image 1",
		"image 2",
		"image 3",
		"image 4",
		"image 5",
		"image 6",
		"image 7"};
	
	public static final Integer[] IMAGES_SRC = {
		R.drawable.image_1,
		R.drawable.image_2,
		R.drawable.image_3,
		R.drawable.image_4,
		R.drawable.image_5,
		R.drawable.image_6,
		R.drawable.image_7,};
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.image_list, container, false);
		ListView listView = (ListView)view.findViewById(android.R.id.list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, IMAGES_TXT);
		listView.setAdapter(adapter);
		return view;
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		IListFragment activity = (IListFragment)getActivity();
		if (activity != null) {
			activity.clickOnImage(IMAGES_SRC[position]);
		}
	}
	
}
