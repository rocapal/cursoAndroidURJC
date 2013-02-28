package es.android.background;

import java.util.Timer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static Timer mTimer;
	protected static final long TIMER_TASK_PERIOD = 5000;
	protected static final long TIMER_TASK_DELAY = 1000;
	protected static MyTimerTask mMyTimerTask;
	protected static MyThreadHandle mThreadHandler;	
	protected static boolean mInicio;
	protected static boolean mInicioThread;
	public static Handler mHandler;
	protected Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTimer = new Timer();
		mMyTimerTask = new MyTimerTask(MainActivity.this);
		mInicio = false;
		mInicioThread = false;
		mContext = this;
		Button b = (Button) this.findViewById(R.id.boton1);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mInicio= true;
				mTimer.schedule(mMyTimerTask, TIMER_TASK_DELAY, TIMER_TASK_PERIOD);
				
			}
		});
	
		
		mHandler = new Handler(new Callback(){
			
			public boolean handleMessage(Message msg) {
				Toast.makeText(mContext, mContext.getString(R.string.toast_msg_thread_message, msg.what),
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		Button b2 = (Button) this.findViewById(R.id.boton2);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mThreadHandler = new MyThreadHandle(mHandler);
				mInicioThread = true;
				mThreadHandler.start();
			}
		});		
		
		Button b3 = (Button) this.findViewById(R.id.boton3);
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startService(new Intent(getApplicationContext(), MyService.class));
			}
		});
		
		Button b4 = (Button) this.findViewById(R.id.boton4);
		b4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopService(new Intent(getApplicationContext(), MyService.class));
			}
		});		
	}
	
	public void CambiarTexto(String texto){
		TextView tv1 = (TextView) findViewById(R.id.tv1);
		tv1.setText(texto);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mTimer == null)
			mTimer = new Timer();
		if (mMyTimerTask == null)
			mMyTimerTask = new MyTimerTask(MainActivity.this);
		if (mInicio)
			mTimer.schedule(mMyTimerTask, TIMER_TASK_DELAY, TIMER_TASK_PERIOD);
		if (mInicioThread){
			mHandler = new Handler(new Callback(){
				
				public boolean handleMessage(Message msg) {
					Toast.makeText(mContext, mContext.getString(R.string.toast_msg_thread_message, msg.what),
							Toast.LENGTH_SHORT).show();
					return false;
				}
			});
			if (mInicioThread)
				mThreadHandler = new MyThreadHandle(mHandler);
			mInicioThread = true;
			mThreadHandler.start();
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mMyTimerTask != null)
			mMyTimerTask.cancel();
		if (mTimer != null)
			mTimer.purge();

		if (mThreadHandler != null)
		{
			mThreadHandler.setShouldContinue(false);
			try {
				mThreadHandler.join();
			} catch (Exception e) {
				Log.d("TAG", e.getMessage());
			}
		}		
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("TAG", "Stop Service");
	}
}
