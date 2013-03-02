package com.example.multitareaandroid;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class MyTimerTask extends TimerTask {

	private Context context;
	
	public MyTimerTask(Context myContext) {
		context = myContext;
	}

	@Override
	public void run() {
		// para pasar codigo al thread del contexto.
		((Activity)context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				String string = context.getString(R.string.message_timer, MainActivity.TIMER_TASK_PERIOD / 1000);
				Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
