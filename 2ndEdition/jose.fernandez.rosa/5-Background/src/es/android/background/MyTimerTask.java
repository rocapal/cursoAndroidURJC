package es.android.background;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class MyTimerTask extends TimerTask {
	private Context mContext;
	public final static int TIMER_TASK_PERIOD = 5000;

	public MyTimerTask(Context contexto) {
		this.setmContext(contexto);
	}

	@Override
	public void run() {
		((Activity) mContext).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(
						mContext,
						mContext.getString(R.string.toast_msg, TIMER_TASK_PERIOD / 1000), 
						Toast.LENGTH_SHORT)
						.show();

			}
		});

	}

	public Context getmContext() {
		return mContext;
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

}
