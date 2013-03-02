package com.example.multitareaandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class RegisterService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(MainActivity.TAG, MainActivity.TAG + " onCreate");
		this.initService();
	}

	private void initService() {
		//myHandler.sendMessageDelayed(new Message(), DELAY);
		MainActivity.updateText("service working....");
		LocationListActivity.addLocation("zas en toda la boca...");
	}

	@Override
	public void onDestroy() {
		MainActivity.updateText("");
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
