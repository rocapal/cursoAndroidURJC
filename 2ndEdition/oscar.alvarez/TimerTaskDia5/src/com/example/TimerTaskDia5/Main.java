package com.example.TimerTaskDia5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.Timer;

public class Main extends Activity {
    private final Integer TIMER_TASK_PERIOD = 5000;
    private final Integer TIMER_TASK_DELAY = 3000;
    private Timer mTimer = null;
    private MyTimerTask mTask = null;
    private MyThreadHandler mThread;
    private MyAsyncTask mAsyncTask;
    static boolean taskIsRunning = false;
    static boolean threadIsRunning = false;
    Context mContext;
    public static TextView counter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mContext = this;
        Button go_timerTask = (Button)this.findViewById(R.id.TimerTaskButton);
        Button go_thread = (Button)this.findViewById(R.id.ThreadButton);
        Button go_asyncTask = (Button)this.findViewById(R.id.AsyncTaskButton);
        Button go_serviceON = (Button)this.findViewById(R.id.ServiceONButton);
        Button go_serviceOFF = (Button)this.findViewById(R.id.ServiceOFFButton);

        counter = (TextView)this.findViewById(R.id.CounterTextView);
        counter.setText("00");

        go_timerTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskIsRunning == false)
                {
                    taskIsRunning = true;
                    mTimer = new Timer();
                    mTask = new MyTimerTask(mContext);//se puede poner un setContext y pasarle el context despues
                    mTimer.schedule(mTask, TIMER_TASK_DELAY, TIMER_TASK_PERIOD);
                }

            }
        });

        go_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                if (threadIsRunning == false)
                {
                    mThread.start();
                    threadIsRunning = true;
                }
            }
        });

        go_asyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                mAsyncTask = new MyAsyncTask(mContext);
                mAsyncTask.execute();
            }
        });

        go_serviceON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                startService(new Intent(mContext, MyService.class));
            }
        });

        go_serviceOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                stopService(new Intent(mContext, MyService.class));
            }
        });
    }

    public static void changeTextView (String string){
        counter.setText(string);
    }

    Handler myHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Toast.makeText(mContext, mContext.getString(R.string.mensajeThread, message.what, TIMER_TASK_PERIOD / 1000), Toast.LENGTH_SHORT).show();
            return true;  //To change body of implemented methods use File | Settings | File Templates.
        }
    });

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        Log.d("mensaje", "onPause");
        if (mTask != null)
        {
            mTask.cancel();
        }

        if (mTimer != null)
        {
            mTimer.purge();
        }

        if (mThread != null)
        {
            mThread.shouldContinue = false;
            try {
                mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        Log.d("mensaje", "onResume");
        if (taskIsRunning)
        {
            Log.d("mensaje", "onResume if taskShould continue");
            mTimer = new Timer();
            mTask = new MyTimerTask(mContext);//se puede poner un setContext y pasarle el context despues
            mTimer.schedule(mTask, TIMER_TASK_DELAY, TIMER_TASK_PERIOD);
        }

        if (mThread == null)
        {
            mThread = new MyThreadHandler(myHandler);
        }

        if (threadIsRunning)
        {
            mThread.shouldContinue = true;
            mThread.run();
        }
    }
}
