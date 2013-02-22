package com.android.apps.background;

import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
	
	Button btTimerTask, btThreadWithHandler;
	Timer mTimer;
	MyTimerTask myTimerTask;
	MyThread myThread;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		btTimerTask = (Button) findViewById (R.id.btTimerTask);
		btThreadWithHandler = (Button) findViewById (R.id.btThreadWithHandler);
		
		Toast.makeText(this, "onCreate()",
				Toast.LENGTH_SHORT).show();
		
		btTimerTask.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				
				mTimer = new Timer();
				myTimerTask = new MyTimerTask(Main.this);
				
				mTimer.schedule(myTimerTask, 2000, 5000);

				
			}
		});
		
		
		
		btThreadWithHandler.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				myThread = new MyThread();
				myThread.setHandler(mHandler);
				myThread.shouldContinue = true;
				myThread.run();
			}
		});
	}
	
	Handler mHandler = new Handler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Toast.makeText(Main.this, "Ejecutando Thread + Handler.\nHan pasado " + msg.what + " segundos.", 
					Toast.LENGTH_SHORT).show();
			
			return false;
		}
		
	});
	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Toast.makeText(this, "onResume()",
				Toast.LENGTH_SHORT).show();
		
		if ((myTimerTask != null) && (mTimer != null))
			mTimer.schedule(myTimerTask, 2000, 5000);
		
		if ((myThread != null) && (!myThread.shouldContinue)){
			myThread.shouldContinue = true;
			myThread.run();
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		Toast.makeText(this, "onPause()",
				Toast.LENGTH_SHORT).show();
		
		if (myTimerTask != null)
			myTimerTask.cancel();
		
		if (mTimer != null)
			mTimer.purge();
		
		if (myThread != null) {
			myThread.shouldContinue = false;
			try {
				myThread.join();
			} catch (InterruptedException e) {
				Log.d("MYTHREAD", e.getMessage());
			}
		}
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
