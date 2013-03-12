//
//  ListAdapter.java
//  07 Connectivity
//
//  Created by Omar Pedraza on 3/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Connectivity;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Place> {
    private ArrayList<Place> mData = null;

    public ListAdapter(Context context, int textViewResourceId, ArrayList<Place> objects) {
        super(context, textViewResourceId, objects);

        mData = objects;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Place getItem(int idx) {
        return mData.get(idx);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parentView) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.list_cell, null);

        ImageView thumbnail = (ImageView) layout.findViewById(R.id.thumbnail);
        thumbnail.setImageBitmap(null);
        getImageFromURL(thumbnail, mData.get(pos).getThumbnail());

        TextView name = (TextView) layout.findViewById(R.id.name);
        name.setText(mData.get(pos).getName());

        TextView date = (TextView) layout.findViewById(R.id.date);
        date.setText(mData.get(pos).getDate());

        return layout;
    }

    public void getImageFromURL(ImageView imageView, Uri url) {
        ImageAsyncTask asyncTask = new ImageAsyncTask(imageView, url);
        asyncTask.execute(null, null, null);
    }
}
