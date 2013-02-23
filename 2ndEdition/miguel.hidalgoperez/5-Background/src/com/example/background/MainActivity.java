package com.example.background;

import java.util.Timer;

import android.os.Handler.Callback;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Timer myTimer;
	private MyTimerTask myTimerTask;
	private Context mycontext;
	private MyThreadHandler myThreadHandler;
	boolean resume = false;
	
	Handler myHandler = new Handler(new Callback() {
		public boolean handleMessage (Message msg) {
			Toast.makeText(mycontext, getString(R.string.msgthread, msg.what), Toast.LENGTH_SHORT).show();
			return true;
		}
	});
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mycontext=MainActivity.this;
        
        Button btn1 = (Button) this.findViewById(R.id.buttontimertask);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				myTimer = new Timer();
				myTimerTask = new MyTimerTask(mycontext);
				myTimer.schedule(myTimerTask, 2000, 2000);
			}
		});
		
		Button btn2 = (Button) this.findViewById(R.id.buttonthread);
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				myThreadHandler = new MyThreadHandler(myHandler);
				myThreadHandler.start();
			}
		});
		
		Button btn3 = (Button) this.findViewById(R.id.buttonstartservice);
		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(getApplicationContext(), MyService.class));
			}
		});
		
		Button btn4 = (Button) this.findViewById(R.id.buttonstopservice);
		btn4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(getApplicationContext(), MyService.class));
			}
		});
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	
    	resume=true;
    	
    	if (myTimerTask != null)
    		myTimerTask.cancel();
    	
    	if (myTimer != null)
    		myTimer.purge();
    	
    	if (myThreadHandler != null){
    		myThreadHandler.shouldContinue = false;
    		try {
    			myThreadHandler.join();
    		} catch (InterruptedException e){
    			Log.d("MainActivity", e.getMessage());
    		}
    	}
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	if (resume) {
    		myTimer = new Timer();
    		myTimerTask = new MyTimerTask(mycontext);
    		myTimer.schedule(myTimerTask, 3000, 3000);
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
