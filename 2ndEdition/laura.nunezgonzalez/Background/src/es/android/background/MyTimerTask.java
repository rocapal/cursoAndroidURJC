package es.android.background;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class MyTimerTask extends TimerTask{

	protected static final int TIMER_TASK_PERIOD = 5000;
	private Context mContexto;
	
	public MyTimerTask(){
		super();
	}
	
	public MyTimerTask(Context c) {
		this();
		this.setContexto(c);
	}
	
	@Override
	public void run() {
		((Activity) mContexto).runOnUiThread(new Runnable() {

			public void run() {
				Toast.makeText(mContexto, mContexto.getString(R.string.mensaje, TIMER_TASK_PERIOD/1000),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	public Context getContexto() {
		return mContexto;
	}

	public void setContexto(Context contexto) {
		this.mContexto = contexto;
	}

}
