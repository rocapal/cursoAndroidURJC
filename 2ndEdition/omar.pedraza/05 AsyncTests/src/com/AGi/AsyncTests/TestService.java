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
import android.util.Log;

public class TestService extends Service {
    private static Context mContext = null;
    private static Integer mCounter = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("service", "service destroyed");
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
        Handler handler = new Handler (new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                mCounter += AsyncTestsConstants.SERVICE_DELAY / 1000;
                ((Main) mContext).changeText(mCounter);

                initService();

                return true;
            }
        });
        handler.sendMessageDelayed(new Message(), AsyncTestsConstants.SERVICE_DELAY);
    }

    public static void setContext(Context context) {
        mContext = context;
    }
}
