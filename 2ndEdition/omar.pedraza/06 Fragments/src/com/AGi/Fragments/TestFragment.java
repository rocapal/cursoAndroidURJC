//
//  TestFragment.java
//  06 Fragments
//
//  Created by Omar Pedraza on 3/1/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestFragment extends Fragment {
    private static EditText mTextField = null;
    private static FragmentListener mListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FragmentListener) {
            mListener = (FragmentListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.test_fragment, container, false);

        mTextField = (EditText) layout.findViewById(R.id.text_field);

        ((Button) layout.findViewById(R.id.ok_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushButton(view);
            }
        });

        ((Button) layout.findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushButton(view);
            }
        });

        ((Button) layout.findViewById(R.id.test_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushButton(view);
            }
        });

        return layout;
    }

    private void pushButton(View view) {
        if (mListener != null) {
            if (view.getId() == R.id.ok_button) {
                mListener.pushOK(mTextField.getText().toString());
            }
            else if (view.getId() == R.id.cancel_button) {
                mListener.pushCancel("If you cancel, you can't see what the user has written");
            }
            else {
                mListener.pushTest("This is only a test, this button does nothing, please use the other ones");
            }
        }
    }
}