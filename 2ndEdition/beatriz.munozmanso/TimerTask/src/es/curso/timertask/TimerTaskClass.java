package es.curso.timertask;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


public class TimerTaskClass extends TimerTask{
	
	private Context mContext;
	private Integer TIMER_TASK_DELAY = 0;
	private Integer TIMER_TASK_PERIOD = 5000;

	public void setContext(Context context){
		this.mContext = context;
	}

	@Override
	public void run() {
		((Activity) mContext).runOnUiThread(new Runnable() {
			
			
			@Override
			public void run() {
				Toast.makeText(mContext, mContext.getString(R.string.msg_period,
						TIMER_TASK_PERIOD/1000),Toast.LENGTH_SHORT).show();
				
			}
		});
		

		
	}

}
