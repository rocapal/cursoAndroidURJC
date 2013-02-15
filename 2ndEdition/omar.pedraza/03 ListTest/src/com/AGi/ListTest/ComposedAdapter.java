//
//  ComposedAdapter.java
//  03 ListTest
//
//  Created by Omar Pedraza on 2/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//
package com.AGi.ListTest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ComposedAdapter extends BaseAdapter {
    private Context context;

    public ComposedAdapter(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return ComposedList.elements.size();
    }

    @Override
    public Node getItem(int idx) {
        return ComposedList.elements.get(idx);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parentView) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.composed_cell, null);
        }
        else {
            view = convertView;
        }

        ImageView image = (ImageView) view.findViewById(R.id.image);
        image.setImageDrawable(context.getResources().getDrawable(ComposedList.elements.get(pos).image));

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(ComposedList.elements.get(pos).title);

        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(ComposedList.elements.get(pos).text);

        return view;
    }
}
