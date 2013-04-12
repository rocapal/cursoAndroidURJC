package es.curso.timertask;

import java.util.Timer;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = "cannot stop the Thread";
	private Timer mTimer; 
	private TimerTaskClass mTimerClass;
	private static boolean state = false;
	private static boolean state2 = false;
	private ThreadhandlerClass threadHand;
	
	private Handler myHandler = new Handler(new Handler.Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			Toast.makeText(getBaseContext(), getString(R.string.toastHandler,msg.what),Toast.LENGTH_SHORT).show();
			return true;
		}
	});
		

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Button bTimerTask = (Button) this.findViewById(R.id.boton);
		bTimerTask.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				state = true;
				mTimer = new Timer();
				mTimerClass = new TimerTaskClass();
				mTimerClass.setContext(MainActivity.this);
				mTimer.schedule(mTimerClass, 0, 5000);
				
			}
		});
		
		Button buttomHandler = (Button) this.findViewById(R.id.boton2);
		buttomHandler.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				state2 = true;
				threadHand = new ThreadhandlerClass();
				threadHand.setHandler(myHandler);
				threadHand.start();
				
			}
		});
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		//Stop TimerTask
		if (mTimerClass != null)
			mTimerClass.cancel();
		
		if (mTimer != null)
			mTimer.purge();
		
		if(myHandler != null){

			threadHand.setShouldContinue(false);
			try{
				threadHand.join();
			}catch (InterruptedException e){
				Log.d(TAG, e.getMessage());
			}
		}
	}
	
	@Override
	protected void onResume() {
		if (state){
			mTimer = new Timer();
			mTimerClass = new TimerTaskClass();
			mTimerClass.setContext(MainActivity.this);
			mTimer.schedule(mTimerClass, 0, 5000);
		}
		
		if(state2){
			threadHand.setShouldContinue(true);
			threadHand = new ThreadhandlerClass();
			threadHand.setHandler(myHandler);
			threadHand.start();
		}

		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	

}
