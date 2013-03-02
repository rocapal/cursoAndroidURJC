package com.example.multitareaandroid;

import java.util.Timer;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class TimerListener implements OnClickListener {

	Context context;
	Timer timer;
	MyTimerTask myTimerTask;	
	
	
	public TimerListener(Context myContext, Timer myTimer, MyTimerTask timerTask) {
		context = myContext;
		timer = myTimer;
		myTimerTask = timerTask;
	}



	@Override
	public void onClick(View v) {		
		timer.schedule(myTimerTask, MainActivity.TIMER_TASK_DELAY, MainActivity.TIMER_TASK_PERIOD);
	}

}
