//
//  FragmentsActivity.java
//  06 Fragments
//
//  Created by Omar Pedraza on 3/5/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentsActivity extends FragmentActivity implements DialogFragmentListener, FragmentListener {
    private static FragmentManager mFragmentManager = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragments_activity);

        mFragmentManager = getSupportFragmentManager();

        final Button dialog_button = (Button) findViewById(R.id.dialog_button);
        dialog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        ((Button) findViewById(R.id.fragment_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFragmentManager.findFragmentByTag("form") == null) {
                    showFragment();

                    ((Button) view).setText(R.string.hide_fragment);
                    dialog_button.setVisibility(View.INVISIBLE);
                }
                else {
                    hideFragment();

                    ((Button) view).setText(R.string.show_fragment);
                    dialog_button.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void hideFragment() {
        Fragment form = mFragmentManager.findFragmentByTag("form");

        if (form != null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.remove(form);
            transaction.commit();
        }
    }

    private void showDialog() {
        TestDialogFragment dialog = new TestDialogFragment();
        dialog.show(mFragmentManager, "dialog");
    }

    private void showFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, new TestFragment(), "form");
        transaction.commit();
    }

    @Override
    public void addText(String text) {
        TextView textView = (TextView) findViewById(R.id.dialog_response);
        textView.setText(text);
    }

    @Override
    public void pushOK(String text) {
        Toast toast = Toast.makeText(this, "The user has written: " + text, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void pushCancel(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void pushTest(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }
}
