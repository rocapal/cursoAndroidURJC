package es.android.background;

import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
	private Timer mTimer;
	private MyTimerTask mTimerTask;
	private MyThreadHandler mThHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnTimer = (Button) findViewById(R.id.btn_timer);
		btnTimer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mTimerTask == null) {

					mTimer = new Timer();
					mTimerTask = new MyTimerTask(Main.this);
					mTimer.schedule(mTimerTask, 0,
							MyTimerTask.TIMER_TASK_PERIOD);
				}
			}
		});

		Button btnThread = (Button) findViewById(R.id.btn_thread);
		btnThread.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		Button btnThreadHandled = (Button) findViewById(R.id.btn_thread_handled);
		btnThreadHandled.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Handler myHandler = new Handler(new Callback() {

					@Override
					public boolean handleMessage(Message msg) {
						Toast.makeText(
								Main.this,
								Main.this
										.getString(
												R.string.toast_msg_th_handler,
												msg.what), Toast.LENGTH_SHORT)
								.show();
						return false;
					}
				});
				
				mThHandler= new MyThreadHandler(myHandler);
				mThHandler.start();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mTimerTask!=null)	mTimerTask.run();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		if (mTimerTask != null)
			mTimerTask.cancel();
		if (mTimer != null)
			mTimer.purge();
		
		if (mThHandler!=null) mThHandler.stopMe();
		super.onDestroy();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

	}

}
