package com.AGi.AsyncTests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
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
    private static TestAsyncTask mAsyncTask = null;
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

        Button serviceButton = (Button) this.findViewById(R.id.service_button);
        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TestService.isRunning()) {
                    TestService.setContext(mContext);
                    TestService.registerListener(new iListener() {
                        @Override
                        public void setText(String text) {
                            TextView textView = (TextView) ((Activity) mContext).findViewById(R.id.title);
                            textView.setText(text);
                        }
                    });

                    startService(new Intent(mContext, TestService.class));

                    ((Button) view).setText(R.string.stop_service);
                }
                else {
                    TestService.stopHandler();
                    stopService(new Intent(mContext, TestService.class));

                    ((Button) view).setText(R.string.start_service);
                }
            }
        });

        Button asyncTaskButton = (Button) this.findViewById(R.id.async_task_button);
        asyncTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAsyncTask == null || mAsyncTask.mProgress == 0) {
                    mAsyncTask = new TestAsyncTask(mContext);
                    mAsyncTask.execute(null, null, null);
                }
            }
        });

        Button locationServiceButton = (Button) this.findViewById(R.id.location_service_button);
        locationServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TestLocationService.isRunning()) {
                    TestLocationService.setContext(mContext);
                    TestLocationService.registerListener(new iListener() {
                        @Override
                        public void setText(String text) {
                            TextView textView = (TextView) ((Activity) mContext).findViewById(R.id.title);
                            textView.setText(text);
                        }
                    });

                    startService(new Intent(mContext, TestLocationService.class));

                    ((Button) view).setText(R.string.stop_location_service);
                }
                else {
                    stopService(new Intent(mContext, TestLocationService.class));

                    ((Button) view).setText(R.string.start_location_service);
                }
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
}
