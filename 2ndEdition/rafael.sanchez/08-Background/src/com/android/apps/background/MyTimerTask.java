package com.android.apps.background;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class MyTimerTask extends TimerTask {
	
	private Context miContexto;
	public final static Integer TIMER_PERIOD = 5000;

	public MyTimerTask(Context c) {
		miContexto = c;
	}
	
	@Override
	public void run() {
		((Activity) miContexto).runOnUiThread(new Runnable() {
			
		public void run() {		
		Toast.makeText(miContexto, miContexto.getString(R.string.title_thread, TIMER_PERIOD/1000),
				Toast.LENGTH_SHORT).show();
		}
		
	});
	}
		

	
	
	
}
