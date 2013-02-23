package com.example.aplicacion;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.widget.TextView;

public class Activity1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.l1);
		
		TextView tv = (TextView) this.findViewById(R.id.text);
		
		Intent i = getIntent();
		
		if ( i != null)
		{
			String name = i.getStringExtra(MainActivity.VALUE_1);
			Integer num = i.getIntExtra(MainActivity.VALUE_2, -1);
			
			tv.setText( name + " " + num.toString());
		}
	}
}
