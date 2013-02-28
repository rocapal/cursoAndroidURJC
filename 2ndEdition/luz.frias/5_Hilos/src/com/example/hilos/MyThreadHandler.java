package com.example.hilos;

import android.os.Handler;
import android.os.Message;

public class MyThreadHandler extends Thread {
	private final int DELAY = 4000; //ms
	private Handler mHandler = null;
	private Handler mHandlerLoop = new Handler();
	private boolean shouldContinue = true;
	private int count = 0;
	
	public void setHandler(Handler handler) {
		mHandler = handler;
	}
	
	public void shouldContinue(boolean newValue) {
		shouldContinue = newValue;
	}
	
	@Override
	public void run() {
		if (!shouldContinue) {
			return;
		}
		
		Message message = new Message();
		message.what = count * DELAY/1000;
		mHandler.sendMessage(message);
		count++;
		
		mHandlerLoop.postDelayed(this, DELAY);
	}
}
