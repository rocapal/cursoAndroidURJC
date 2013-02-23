package com.example.aplicacion;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.widget.TextView;

public class Activity3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.l1);
		
		TextView tv = (TextView) this.findViewById(R.id.text);
        tv.setText(getClass().getSimpleName());
        
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.PARAM, 20);
        setResult(RESULT_OK, returnIntent);
	}
}