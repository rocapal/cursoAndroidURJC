//
//  TestListFragment.java
//  06 Fragments
//
//  Created by Omar Pedraza on 3/5/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Fragments.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.AGi.Fragments.R;

import java.util.ArrayList;

public class TestListFragment extends ListFragment {
    protected static ArrayList<Node> elements = new ArrayList<Node>();
    private static ListListener mListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ListListener) {
            mListener = (ListListener) activity;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fillElements();

        TestListAdapter adapter = new TestListAdapter(getActivity(), elements);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.list_fragment, null);

        return layout;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mListener.chooseItem(elements.get(position));
    }

    private void fillElements() {
        Node element_1 = new Node();
        element_1.image = R.drawable.boofle;
        element_1.title = "Boofle";

        elements.add(element_1);

        Node element_2 = new Node();
        element_2.image = R.drawable.taylor_swift;
        element_2.title = "Taylor Swift";

        elements.add(element_2);
    }
}
