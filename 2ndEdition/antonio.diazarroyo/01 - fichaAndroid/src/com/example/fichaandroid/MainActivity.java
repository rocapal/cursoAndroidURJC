package com.example.fichaandroid;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btLocaliza = (Button) this.findViewById(R.id.btLocalizame);
		final TextView textoCabecera = (TextView) this.findViewById(R.id.text01);
		btLocaliza.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("main", "on click button");
				textoCabecera.setText(R.string.hello_world);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
