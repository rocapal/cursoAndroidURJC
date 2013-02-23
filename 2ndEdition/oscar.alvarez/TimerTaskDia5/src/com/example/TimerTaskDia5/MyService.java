package com.example.TimerTaskDia5;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.Delayed;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 22/02/13
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */
public class MyService extends Service {
    int counter;
    boolean shouldContinue = true;

    @Override
    public void onCreate() {
        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.
        Log.d("Mensaje", "Se ha creado el servicio");
        initService();
    }

    private void initService(){
        CountActions myCounter = new CountActions();
        myCounter.sendMessageDelayed(new Message(), 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
        Log.d("Mensaje", "Se ha cerrado el servicio");
        shouldContinue = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private class CountActions extends Handler {

        @Override
        public void handleMessage(Message msg) {
            counter = counter + 3;
            super.handleMessage(msg);    //To change body of overridden methods use File | Settings | File Templates.
            Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.mensajeService, counter), Toast.LENGTH_SHORT).show();
            if (shouldContinue)
                initService();
            Main.changeTextView(String.valueOf(counter));
        }
    }
}
