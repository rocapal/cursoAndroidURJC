package com.example.multitareaandroid;

import java.util.Timer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
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

	public static final Integer TIMER_TASK_PERIOD = 4000;
	public static final Integer TIMER_TASK_DELAY = 3000;
	public static final String TAG = "threadsAndroid";
	
	public static TextView MY_TEXT;
	
	
	private Timer timer;
	private MyTimerTask myTimerTask;
	private ThreadHandler threadHandler;
	private Context contexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contexto = this;
		MainActivity.MY_TEXT = (TextView)findViewById(R.id.texto_actualizable);
		ServiceGPS.setiLocation(new ILocationImp());
		startService(new Intent(contexto, ServiceGPS.class));
		Button button;
		/* TimerTask. */
		button = (Button)findViewById(R.id.button_timer_start);
		button.setOnClickListener(this.createTimerStart());		
		button = (Button)findViewById(R.id.button_timer_stop);
		button.setOnClickListener(this.createTimerStop());		
		/* Thread and Handler. */
		button = (Button)findViewById(R.id.button_thread_start);
		button.setOnClickListener(this.createThreadStart());
		button = (Button)findViewById(R.id.button_thread_stop);
		button.setOnClickListener(this.createThreadStop());
		/* AsyncTask. */
		button = (Button)findViewById(R.id.button_asynctask_start);
		button.setOnClickListener(this.createAsynctaskStart());
		button = (Button)findViewById(R.id.button_asynctask_stop);
		button.setOnClickListener(this.createAsynctaskStop());
		/* Service. */
		button = (Button)findViewById(R.id.button_service_start);
		button.setOnClickListener(this.createServiceStart());
		button = (Button)findViewById(R.id.button_service_stop);
		button.setOnClickListener(this.createServiceStop());
		/* GPS register. */
		button = (Button)findViewById(R.id.button_register_start);
		button.setOnClickListener(this.createRegisterStart());
		button = (Button)findViewById(R.id.button_register_stop);
		button.setOnClickListener(this.createRegisterStop());
		button = (Button)findViewById(R.id.button_register_view);
		button.setOnClickListener(this.createRegisterView());
		
		
	}
	
	private OnClickListener createRegisterStart() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startService(new Intent(contexto, RegisterService.class));
			}
		};
	}
	private OnClickListener createRegisterStop() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				stopService(new Intent(contexto, RegisterService.class));
			}
		};
	}
	
	private OnClickListener createRegisterView() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(contexto, LocationListActivity.class));
			}
		};
	}
	private OnClickListener createAsynctaskStop() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(contexto, R.string.not_implemented, Toast.LENGTH_SHORT).show();
			}
		};
	}

	private OnClickListener createAsynctaskStart() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new MyAsyncTask(contexto).execute(null, null, null);
			}
		};
	}

	private OnClickListener createServiceStop() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent (contexto, MyService.class));
			}
		};
	}

	private OnClickListener createServiceStart() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(contexto, MyService.class));
			}
		};
	}

	private OnClickListener createThreadStop() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (threadHandler!=null){
					threadHandler.setShouldContinue(false);
					try {
						threadHandler.join();
					} catch (InterruptedException e) {
						Log.d(TAG, e.getMessage());
					}
				}
			}
		};
	}

	private View.OnClickListener createThreadStart() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button button = (Button)findViewById(R.id.button_thread_stop);
				button.performClick();
				threadHandler = new ThreadHandler(handler);
				threadHandler.run();
			}
		};
	}

	/** Create the listener of start TimerTask button. */
	private View.OnClickListener createTimerStart(){
		View.OnClickListener myListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (contexto!=null){
					timer = new Timer();
					myTimerTask = new MyTimerTask(contexto);
					timer.schedule(myTimerTask, MainActivity.TIMER_TASK_DELAY, MainActivity.TIMER_TASK_PERIOD);
				}
			}
		};
		return myListener;
	}
	
	/** Create the listener of stop TimerTask button. */
	private View.OnClickListener createTimerStop(){
		/** Stop the TimerTask. */
		View.OnClickListener myListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (timer!=null) {
					timer.purge();
				}
				if (myTimerTask != null) {
					myTimerTask.cancel();
				}				
			}
		};
		return myListener;
	}
	

	Handler handler = new Handler(new Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			if (contexto!=null) {
				String myStr = contexto.getString(R.string.message_thread, msg.what);
				Toast.makeText(MainActivity.this, myStr, Toast.LENGTH_SHORT).show();
			}
			return true;
		}
	});
	
	Handler handlerService = new Handler(new Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			if (contexto!=null) {
				TextView myText = (TextView)MainActivity.this.findViewById(R.id.texto_actualizable);
				myText.setText("count: " + msg.what);
			}
			return true;
		}
	});
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (threadHandler!=null){
			threadHandler.setShouldContinue(false);
			try {
				threadHandler.join();
			} catch (InterruptedException e) {
				Log.d(TAG, e.getMessage());
			}
		}
		if (timer!=null) {
			timer.purge();
		}
		if (myTimerTask != null) {
			myTimerTask.cancel();
		}
		stopService(new Intent(contexto, MyService.class));
		stopService(new Intent(contexto, ServiceGPS.class));
	}
	
	/** Actualiza el texto del TextView, pensado para que sea actualizado desde un Service. */
	public static void updateText(String newTxt){
		if (MainActivity.MY_TEXT!=null) {
			MainActivity.MY_TEXT.setText(newTxt);
		}
	}	
}
