package com.android.apps.background;

import android.os.Handler;
import android.os.Message;

public class MyThread extends Thread {
	
	public boolean shouldContinue = true;
	
	public Handler mHandler, mHandlerLoop;
	int c;
	
	public void setHandler (Handler handler) {
		mHandler = handler;
		mHandlerLoop = new Handler();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		if (!shouldContinue)
			return;
		
		Message msg = new Message();
		msg.what = c * (4000/1000);
		mHandler.sendMessage(msg);
		c = c + 1;
		
		mHandlerLoop.postDelayed(this, 4000);
		
	}
}
