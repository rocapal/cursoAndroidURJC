//
//  ComposedList.java
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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ComposedList extends ListActivity {
    protected static ArrayList<Node> elements = new ArrayList<Node>();
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.composed_list);

        fillElements();

        ComposedAdapter adapter = new ComposedAdapter(this);
        setListAdapter(adapter);
    }

    private void fillElements() {
        Node element_1 = new Node();
        element_1.image = R.drawable.boofle;
        element_1.title = "Boofle";
        element_1.text = "Take and customize your photos with this nice dog";

        elements.add(element_1);

        Node element_2 = new Node();
        element_2.image = R.drawable.just_wink;
        element_2.title = "justWink";
        element_2.text = "All the customizable cards you want!";

        elements.add(element_2);

        Node element_3 = new Node();
        element_3.image = R.drawable.stache;
        element_3.title = "Mustache Wisdom";
        element_3.text = "Let the stache be the best part of your day";

        elements.add(element_3);
    }
}
