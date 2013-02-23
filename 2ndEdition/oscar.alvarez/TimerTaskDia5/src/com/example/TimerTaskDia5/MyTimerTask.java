package com.example.TimerTaskDia5;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 22/02/13
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
public class MyTimerTask extends TimerTask {
    Context mContext = null;
    private final Integer TIMER_TASK_PERIOD = 5000;
    public boolean shouldContinue;

    public MyTimerTask(Context context) {
        mContext = context;
    }
//Comprobar con booleano que si has pulsado y entras vuelva a salir
    @Override
    public void run() {
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, mContext.getString(R.string.mensaje, TIMER_TASK_PERIOD / 1000), Toast.LENGTH_SHORT).show();
            }
        });
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
