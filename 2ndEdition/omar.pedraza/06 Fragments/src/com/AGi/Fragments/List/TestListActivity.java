//
//  TestListActivity.java
//  06 Fragments
//
//  Created by Omar Pedraza on 3/5/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Fragments.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import com.AGi.Fragments.Constants;
import com.AGi.Fragments.R;

public class TestListActivity extends FragmentActivity implements ListListener {
    private static boolean mIsTablet = false;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_activity);

        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {
            mIsTablet = true;
        }
        else {
            Fragment image = getSupportFragmentManager().findFragmentById(R.id.image_container);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(image);
            transaction.commit();
        }
    }

    @Override
    public void chooseItem(Node node) {
        if (mIsTablet) {
            ImageFragment fragment = (ImageFragment) getSupportFragmentManager().findFragmentById(R.id.image_container);
            fragment.setImage(node.image);
        }
        else {
            Intent intent = new Intent(this, ImageActivity.class);
            intent.putExtra(Constants.kImage, node.image);
            startActivity(intent);
        }
    }
}
