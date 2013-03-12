//
//  ImageActivity.java
//  06 Fragments
//
//  Created by Omar Pedraza on 3/6/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Fragments.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import com.AGi.Fragments.Constants;
import com.AGi.Fragments.R;

public class ImageActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.image_activity);

        Bundle extras = getIntent().getExtras();
        Integer image = extras.getInt(Constants.kImage, 0);

        ImageFragment fragment = (ImageFragment) getSupportFragmentManager().findFragmentById(R.id.image_container);
        fragment.setImage(image);
    }
}
