package com.example.mislistas;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import com.example.mislistas.listeners.ButtonComplexListeners;
import com.example.mislistas.listeners.ButtonEasyListeners;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button bEasy = (Button)this.findViewById(R.id.button_easy_list);
		bEasy.setOnClickListener(new ButtonEasyListeners (this));
		Button bComplex = (Button)this.findViewById(R.id.button_complex_list);
		bComplex.setOnClickListener(new ButtonComplexListeners (this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
