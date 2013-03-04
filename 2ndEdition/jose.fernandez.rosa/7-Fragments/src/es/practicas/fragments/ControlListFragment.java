package es.practicas.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ControlListFragment extends ListFragment {
	
	private static String[] TITLES={
		"Imagen 1",
		"Imagen 2"
	};
	
	private static int[] IMAGES={
		R.drawable.ic_launcher,
		R.drawable.ic_launcher2		
	};

	public interface IListFragment {
		void itemClick (Integer imageResource);
	}
	
	public ControlListFragment(){}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,TITLES));
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Log.i("MyListFragment","Item clicked: " + id);
		IListFragment activity = (IListFragment) getActivity();
		if (activity != null)
			activity.itemClick(IMAGES[position]);
	}
}
