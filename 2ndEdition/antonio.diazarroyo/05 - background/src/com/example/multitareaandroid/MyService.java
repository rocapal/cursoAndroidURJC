package com.example.multitareaandroid;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Handler.Callback;
import android.util.Log;

public class MyService extends Service {

	public int contador;
	private boolean stopNow;
	private static final int DELAY = 3000; 
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(MainActivity.TAG, "onCreate de myService");
		initService();
	}
	
	private void initService() {
		myHandler.sendMessageDelayed(new Message(), DELAY);
		MainActivity.updateText("contador " + contador);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		stopNow = true;
		MainActivity.updateText("");		
		Log.d(MainActivity.TAG, "onDelete de myService");
	}
	
	Handler myHandler = new Handler(new Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			contador += (DELAY / 1000);
			if (!stopNow) {
				initService();
			}
			return true;
		}
	});	
}

