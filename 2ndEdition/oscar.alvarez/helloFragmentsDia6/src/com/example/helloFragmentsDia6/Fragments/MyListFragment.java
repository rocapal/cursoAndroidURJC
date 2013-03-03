package com.example.helloFragmentsDia6.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.helloFragmentsDia6.R;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 01/03/13
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */
public class MyListFragment extends ListFragment {

    private static String[] TITLES = {
            "Imagen1",
            "Imagen2",
            "Imagen3",
            "Imagen4"
    };

    private static int[] IMAGES = {
            R.drawable.bass,
            R.drawable.drums,
            R.drawable.guitar,
            R.drawable.piano,
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