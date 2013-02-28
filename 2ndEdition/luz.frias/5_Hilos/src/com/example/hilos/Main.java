package com.example.hilos;

import java.util.Timer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	
	private final int TASK_DELAY = 5000;
	private final int TASK_PERIOD = 5000; //ms
	
	private Context mContext = this;
	private Timer mTimer = null;
	private MyTimerTask mTimerTask = null;
	
	private Handler mHandler = null;
	private MyThreadHandler mThreadHandler = null;
	
	private MyService mService = null;
	
	private static TextView mText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//TimerTask
        Button btTimerTask = (Button) this.findViewById(R.id.btTimerTask);
        btTimerTask.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				mTimer = new Timer();
				mTimerTask = new MyTimerTask(mContext);
				mTimer.schedule(mTimerTask, TASK_DELAY, TASK_PERIOD);
			}
        	
        });

        //Thread
        Button btThread = (Button) this.findViewById(R.id.btThread);
        btThread.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				mHandler = new Handler(new Callback() {

					@Override
					public boolean handleMessage(Message msg) {
						int count = msg.what;
						
						Toast.makeText(mContext, mContext.getString(R.string.txThread, count), Toast.LENGTH_SHORT).show();
						return false;
					}
					
				});
				
				mThreadHandler = new MyThreadHandler();
				mThreadHandler.setHandler(mHandler);
				mThreadHandler.start();
				
			}
        	
        }); 
        
        //Start Service
        Button btStartService = (Button) this.findViewById(R.id.btStartService);
        btStartService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				mService = new MyService();
				
				startService(new Intent(getApplicationContext(), MyService.class));
			}
        	
        }); 
        
        //Stop Service
        Button btStopService = (Button) this.findViewById(R.id.btStopService);
        btStopService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				stopService(new Intent(getApplicationContext(), MyService.class));
			}
        	
        }); 
        
        //Texto
        mText = (TextView) this.findViewById(R.id.tvService);
        
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		//TimerTask
		if (mTimer != null) {
			mTimer = new Timer();
			mTimerTask = new MyTimerTask(mContext);
			mTimer.schedule(mTimerTask, TASK_DELAY, TASK_PERIOD);
		}
		
	}
	
	@Override
	protected void onPause() {
		//TimerTask
		if (mTimerTask != null) {
			mTimerTask.cancel();
		}
		if (mTimer != null) {
			mTimer.purge();
		}
		
		//Thread
		if (mThreadHandler != null) {
			mThreadHandler.shouldContinue(false);
			try {
				mThreadHandler.join();
			} catch (InterruptedException e) {
				Log.d("Main.onPause", e.getMessage());
			}
		}
		
		super.onPause();
	}
	
	public static void updateText(String newText) {
		mText.setText(newText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
