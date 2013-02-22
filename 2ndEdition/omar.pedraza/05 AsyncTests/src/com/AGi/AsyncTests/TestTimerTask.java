//
//  TestTimerTask.java
//  05 AsyncTests
//
//  Created by Omar Pedraza on 2/22/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.AsyncTests;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import java.util.TimerTask;

public class TestTimerTask extends TimerTask {
    private Context mContext = null;

    public TestTimerTask(Context context) {
        mContext = context;
    }

    public void run() {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, mContext.getString(R.string.timer_toast_message), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
