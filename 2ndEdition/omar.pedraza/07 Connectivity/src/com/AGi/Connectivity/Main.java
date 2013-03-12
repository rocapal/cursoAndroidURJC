package com.AGi.Connectivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Main extends Activity implements PlacesListener {
    private static Context mContext = null;
    private static ListAdapter mAdapter = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        mContext = this;

        LocationService.setContext(mContext);
        startService(new Intent(mContext, LocationService.class));

        mAdapter = new ListAdapter(this, R.layout.list_cell, new ArrayList<Place>());
        ListView list = (ListView) findViewById(R.id.places_list);
        list.setAdapter(mAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place item = mAdapter.getItem(position);

                Intent intent = new Intent(Intent.ACTION_VIEW, item.getInfo());
                startActivity(intent);
            }
        });
    }

    @Override
    public void refreshData(ArrayList<Place> places) {
        mAdapter.clear();

        if (places != null) {
            for (Place place : places) {
                mAdapter.insert(place, mAdapter.getCount());
            }
        }

        mAdapter.notifyDataSetChanged();
    }
}
