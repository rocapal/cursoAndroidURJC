//
//  simple_list.java
//  03 ListTest
//
//  Created by Omar Pedraza on 2/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//
package com.AGi.ListTest;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SimpleList extends ListActivity {
    private String[] elements = new String[] {"URJC", "EOI", "Android"};

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.simple_list);

        ListView list = (ListView) this.findViewById(android.R.id.list);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements));
    }

    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        Toast toast = Toast.makeText(this, position + ". " + elements[position], Toast.LENGTH_LONG);
        toast.show();
    }
}
