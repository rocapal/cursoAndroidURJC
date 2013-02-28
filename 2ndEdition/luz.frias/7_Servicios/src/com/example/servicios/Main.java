package com.example.servicios;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class Main extends Activity {
	
	private static TextView mText;
	private GPSService mGPSService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mText = (TextView) findViewById(R.id.mainText);
		
		mGPSService = new GPSService();
		
		startService(new Intent(getApplicationContext(), GPSService.class));
	}
	
	public static void setText(String text) {
		if (mText == null) return;
		mText.setText(text);
	}
	
	@Override
	protected void onDestroy() {
		if (mGPSService != null) {
			stopService(new Intent(getApplicationContext(), GPSService.class));
		}
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
