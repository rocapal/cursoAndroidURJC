package es.android.background;

import android.os.Handler;
import android.os.Message;

public class MyThreadHandle extends Thread {

	private static final int DELAY = 4000;
	private boolean shouldContinue;
	private Handler mHandler;
	private Handler mHandlerLoop = new Handler();
	private static int c;
	
	public MyThreadHandle(Handler h){
		super();
		this.setHandler(h);
		this.setShouldContinue(true);
		this.c = 0;
	}
	

	@Override
	public void run() {
		if(!isShouldContinue())
			return;
		
		Message msg = new Message();
		msg.what = c * (DELAY/1000);
		mHandler.sendMessage(msg);
		
		c++;
		
		mHandlerLoop.postDelayed(this, DELAY);
	}
	
	public Handler getHandler() {
		return mHandler;
	}


	public void setHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}


	public boolean isShouldContinue() {
		return shouldContinue;
	}


	public void setShouldContinue(boolean shouldContinue) {
		this.shouldContinue = shouldContinue;
	}



}
