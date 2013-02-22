package es.android.background;

import android.os.Handler;
import android.os.Message;

public class MyThreadHandler extends Thread {
	static final int DELAY=4000;
	private Integer counter=0;
	private Handler mHandlerParent;
	private Handler mHandlerLoop=new Handler();
	private boolean mCancelThread = false;
	
	
	public MyThreadHandler(Handler messageHandler) {
		// TODO Auto-generated constructor stub
		this.mHandlerParent=messageHandler;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (mCancelThread) return;
		
		Message msg=new Message();
		msg.what= counter++ * DELAY/1000;
		mHandlerParent.sendMessage(msg);
		
		mHandlerLoop.postDelayed(this, DELAY);
		
	}
	
	public void stopMe(){
		mCancelThread=true;
	}
}
