//
//  TestThreadHandler.java
//  05 AsyncTests
//
//  Created by Omar Pedraza on 2/22/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.AsyncTests;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class TestThreadHandler extends Thread {
    public static Boolean mShouldContinue = true;
    private static Handler mHandler = null;
    private final Handler mHandlerLoop = new Handler();
    private static Integer count = 0;

    public TestThreadHandler(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void run() {
        if (!mShouldContinue) {
            return;
        }

        Message message = new Message();
        message.what = count * (AsyncTestsConstants.THREAD_DELAY / 1000);
        mHandler.sendMessage(message);

        count++;

        mHandlerLoop.postDelayed(this, AsyncTestsConstants.THREAD_DELAY);
    }
}
