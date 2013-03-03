package com.example.multitareaandroid;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ThreadHandler extends Thread {

	private static final int DELAY = 4000;
	private Handler handler;
	private Handler handlerLoop;
	private int c;
	public boolean shouldContinue = true;
	
	public ThreadHandler(Handler myHandler) {
		handler = myHandler;
		handlerLoop = new Handler();
	}
	
	@Override
	public void run() {
		Message msg = new Message();
		Log.d (MainActivity.TAG, "shouldContinue" + shouldContinue);
		if (!this.shouldContinue ) {
			return;
		}
		// para pasar string habria que
		msg.what = c * (DELAY / 1000);
		handler.sendMessage(msg);
		c++;
		handlerLoop.postDelayed(this, DELAY);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public boolean isShouldContinue() {
		return shouldContinue;
	}


	public void setShouldContinue(boolean shouldContinue) {
		Log.d (MainActivity.TAG, "shouldContinue " + shouldContinue);
		this.shouldContinue = shouldContinue;
	}
	
	
}
