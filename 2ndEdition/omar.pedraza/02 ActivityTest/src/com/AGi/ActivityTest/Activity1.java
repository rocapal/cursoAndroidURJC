//
//  Activity1.java
//  ActivityTest
//
//  Created by Omar Pedraza on 2/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//
package com.AGi.ActivityTest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Activity1 extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString(Constants.kName);
        String message = extras.getString(Constants.kMessage);
        Integer number = extras.getInt(Constants.kNumber, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast toast = Toast.makeText(this, null, Toast.LENGTH_LONG);

        String message = "You've selected the option ";
        switch (item.getItemId()) {
            case R.id.option_1:
                message += "1";
                break;
            case R.id.option_2:
                message += "2";
                break;
            case R.id.option_3:
                message += "3";
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        toast.setText(message);
        toast.show();

        return true;
    }

    @Override
    public void onPause() {
        super.onPause();

        finish();
    }
}
