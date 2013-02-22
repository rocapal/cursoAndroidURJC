package com.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity03 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_activity);
		TextView tv = (TextView) findViewById(R.id.myText);
		tv.setText(getClass().getSimpleName());
	}
}
