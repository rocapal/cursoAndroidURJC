//
//  TestService.java
//  05 AsyncTests
//
//  Created by Omar Pedraza on 2/22/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.AsyncTests;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class TestService extends Service {
    private static Boolean mRunning = false;
    private static Context mContext = null;
    private static Handler mHandler = null;
    private static iListener mListener = null;
    private static Integer mCounter = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mRunning = true;
        initService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mRunning = false;
    }

    @Override
    public ComponentName startService(Intent service) {
        return super.startService(service);
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    private void initService() {
        mHandler = new Handler (new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                mCounter += AsyncTestsConstants.SERVICE_DELAY / 1000;
                mListener.setText(mContext.getString(R.string.service_text, mCounter));

                initService();

                return true;
            }
        });
        mHandler.sendMessageDelayed(new Message(), AsyncTestsConstants.SERVICE_DELAY);
    }

    public static boolean isRunning() {
        return mRunning;
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public static void registerListener(iListener listener) {
        mListener = listener;
    }

    public static void stopHandler() {
        mHandler.removeCallbacksAndMessages(null);
    }
}
