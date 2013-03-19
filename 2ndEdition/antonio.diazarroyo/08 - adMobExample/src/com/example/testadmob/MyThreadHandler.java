package com.example.testadmob;

import android.os.Handler;
import android.os.Message;

public class MyThreadHandler extends Thread {

	private Handler handler;
	
	@Override
	public void run() {
		try {
			Thread.sleep(ActivityMain.TIME_SHOWING_AD);
			handler.sendMessage(new Message());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
