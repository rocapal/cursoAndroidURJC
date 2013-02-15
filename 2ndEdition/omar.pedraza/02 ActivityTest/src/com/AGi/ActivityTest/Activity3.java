//
//  activity3.java
//  ActivityTest
//
//  Created by Omar Pedraza on 2/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//
package com.AGi.ActivityTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Activity3 extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity3);

        Intent intent = new Intent();
        intent.putExtra(Constants.kValue, 30);
        setResult(RESULT_OK, intent);
    }

    @Override
    public void onPause() {
        super.onPause();

        finish();
    }
}
