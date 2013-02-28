package com.example.hilos;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class MyService extends Service {
	private int DELAY = 3000; //ms
	
	private int count = 0;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.d("Service", "onCreate");
		
		updateCount();
	}
	
	@Override
	public void onDestroy() {
		Log.d("Service", "onDestroy");
		
		super.onDestroy();
	}
	
	public void updateCount() {
		Increment increment = new Increment();
		increment.sendMessageDelayed(new Message(), DELAY);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	class Increment extends Handler {
		
		@Override
		public void handleMessage(Message msg) {
			Log.d("Increment", "handleMessage");

				Main.updateText("" + count);
				count++;
				updateCount();

		}
	}

}
