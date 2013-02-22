package com.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity02 extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_layout);
		TextView tv = (TextView) findViewById(R.id.myCommonText);
		tv.setText(getClass().getSimpleName());
		Intent intent = new Intent();
		intent.putExtra(MainActivity.PARAM, 20);
		setResult(RESULT_OK, intent);
	}
	
	@Override
	protected void onStop() {
		Log.d("etiqueta", "onpause.............");
		super.onStop();
	}
}
