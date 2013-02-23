package com.example.background;

import java.util.TimerTask;

import android.content.Context;
import android.widget.Toast;

public class MyTimerTask extends TimerTask {
	
	private Context mycontext;
	
	public MyTimerTask (Context context){
		mycontext = context;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		((MainActivity) mycontext).runOnUiThread(new Runnable(){
			@Override
			public void run () {
				Toast.makeText(mycontext, mycontext.getString(R.string.msg, 2),Toast.LENGTH_SHORT).show();
			}
		});
	}

}
