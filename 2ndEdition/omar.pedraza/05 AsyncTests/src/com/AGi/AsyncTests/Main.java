package com.AGi.AsyncTests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class Main extends Activity {
    private static Boolean mThreadActivated = false;
    private static Boolean mTimerActivated = false;
    private static Context mContext = null;
    private static TestTimerTask mTimerTask = null;
    private static TestThreadHandler mThread = null;
    private static Timer mTimer = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mContext = this;

        Button timerButton = (Button) this.findViewById(R.id.timer_button);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimer != null && !mTimerActivated) {
                    mTimer.schedule(mTimerTask, AsyncTestsConstants.TIMER_TASK_DELAY, AsyncTestsConstants.TIMER_TASK_PERIOD);

                    mTimerActivated = true;
                }
            }
        });

        Button threadButton = (Button) this.findViewById(R.id.thread_button);
        threadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mThread != null && !mThreadActivated) {
                    mThread.start();

                    mThreadActivated = true;
                }
            }
        });

        Button startService = (Button) this.findViewById(R.id.start_service_button);
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestService.setContext(mContext);
                startService(new Intent(getApplicationContext(), TestService.class));
            }
        });

        Button stopService = (Button) this.findViewById(R.id.stop_service_button);
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestService.setContext(mContext);
                stopService(new Intent(getApplicationContext(), TestService.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mTimerActivated) {
            if (mTimerTask != null) {
                mTimerTask.cancel();
            }

            if (mTimer != null) {
                mTimer.purge();
            }
        }

        if (mThread != null) {
            mThread.mShouldContinue = false;

            try {
                mThread.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mTimerTask == null) {
            mTimerTask = new TestTimerTask(mContext);
        }

        if (mTimer == null) {
            mTimer = new Timer();
        }

        if (mTimerActivated) {
            mTimer.schedule(mTimerTask, AsyncTestsConstants.TIMER_TASK_DELAY, AsyncTestsConstants.TIMER_TASK_PERIOD);
        }

        if (mThread == null) {
            Handler handler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message message) {
                    Toast.makeText(mContext, mContext.getString(R.string.thread_toast_message, message.what), Toast.LENGTH_SHORT).show();

                    return true;
                }
            });

            mThread = new TestThreadHandler(handler);
        }

        if (mThreadActivated) {
            mThread.mShouldContinue = true;
            mThread.run();
        }
    }

    public void changeText(Integer counter) {
        TextView textView = (TextView) this.findViewById(R.id.title);
        textView.setText(this.getString(R.string.service_text, counter));
    }
}
