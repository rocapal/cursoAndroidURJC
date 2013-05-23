package es.android.aplicacionprueba;

import java.util.Timer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static Timer mTimer;
	protected static final long TIMER_TASK_PERIOD = 5000;
	protected static final long TIMER_TASK_DELAY = 1000;
	protected static MyTimerTask mMyTimerTask;	
	protected static boolean mInicio;
	protected static boolean mInicioThread;
	public static Handler mHandler;
	protected Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTimer = new Timer();
		mInicio = false;
		mInicioThread = false;
		mContext = this;
		mMyTimerTask = new MyTimerTask(MainActivity.this);
		mTimer.schedule(mMyTimerTask, TIMER_TASK_DELAY, TIMER_TASK_PERIOD);		
		mHandler = new Handler(new Callback(){
			
			public boolean handleMessage(Message msg) {
				Toast.makeText(mContext, mContext.getString(R.string.toast_msg_thread_message, msg.what),
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		startService(new Intent(getApplicationContext(), MyService.class));	
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("TAG", "Stop Service");
	}

}
