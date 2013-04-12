package es.curso.timertask;

import android.os.Handler;
import android.os.Message;

public class ThreadhandlerClass extends Thread{
	
	private static boolean mShouldContinue = true; 
	private static Handler mHandler;
	private static Integer DELAY = 4000;
	private Integer c = 10;
	private Handler mHandlerLoop = new Handler();
	
	public void setHandler (Handler handler){
		ThreadhandlerClass.mHandler = handler;
		
	}
	public void setShouldContinue (boolean shouldContinue){
		ThreadhandlerClass.mShouldContinue = shouldContinue;
		
	}
	@Override
	public void run() {
		
		if(!mShouldContinue)
			return;
		
		
		Message msg = new Message();
		msg.what= c * (DELAY/1000);
		mHandler.sendMessage(msg);
		c++;

		mHandlerLoop.postDelayed(this, DELAY);
	}
}
