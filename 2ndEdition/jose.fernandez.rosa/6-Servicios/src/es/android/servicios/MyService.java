package es.android.servicios;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class MyService extends Service implements Runnable {
	private Handler mLoopHandler=new Handler();
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		mLoopHandler.postDelayed(this, 3000);
		
	}

	@Override
	public void run() {
		
		
	}
	
	
}
