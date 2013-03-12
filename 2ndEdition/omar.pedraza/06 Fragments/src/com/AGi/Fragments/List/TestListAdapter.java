//
//  TestListAdapter.java
//  06 Fragments
//
//  Created by Omar Pedraza on 3/6/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Fragments.List;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TestListAdapter extends BaseAdapter {
    private ArrayList<Node> mElements = null;
    private Context mContext = null;

    public TestListAdapter(Context context, ArrayList<Node> elements) {
        mContext = context;
        mElements = elements;
    }

    @Override
    public int getCount() {
        return mElements.size();
    }

    @Override
    public Object getItem(int idx) {
        return mElements.get(idx);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parentView) {
        View view = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.simple_list_item_1, null);
        }
        else {
            view = convertView;
        }

        TextView title = (TextView) view.findViewById(R.id.text1);
        title.setText(mElements.get(pos).title);

        return view;
    }
}
