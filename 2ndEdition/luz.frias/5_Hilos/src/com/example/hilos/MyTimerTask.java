package com.example.hilos;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class MyTimerTask extends TimerTask {
	
	private Context mContext;
	private final int TASK_PERIOD = 5000; //ms
	
	public MyTimerTask(Context context) {
		this.mContext = context;
	}

	@Override
	public void run() {
		((Activity) mContext).runOnUiThread( new Runnable() {

			@Override
			public void run() {
				Toast.makeText(mContext, mContext.getString(R.string.txTimerTask, TASK_PERIOD/1000), Toast.LENGTH_SHORT).show();
			}
			
		});
	}

}
