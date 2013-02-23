package com.example.TimerTaskDia5;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 22/02/13
 * Time: 18:01
 * To change this template use File | Settings | File Templates.
 */
public class MyThreadHandler extends Thread {

    public Boolean shouldContinue = true;
    private final Integer TIMER_TASK_DELAY = 3000;
    private  Handler mHandler;
    private  Handler mHandlerLoop = new Handler();
    int c;

    public MyThreadHandler(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void run() {
        super.run();    //To change body of overridden methods use File | Settings | File Templates.
        if (!shouldContinue)
            return;

        Message msg = new Message();
        msg.what = c * TIMER_TASK_DELAY/1000 ;
        mHandler.sendMessage(msg);
        c = c + 1;

        mHandlerLoop.postDelayed(this, TIMER_TASK_DELAY);
    }
}
