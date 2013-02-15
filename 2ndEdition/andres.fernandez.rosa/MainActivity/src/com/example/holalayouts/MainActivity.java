package com.example.holalayouts;

import android.app.Activity;
import android.os.Bundle;
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
		
		final Button btLocaliza = (Button) this.findViewById(R.id.boton1);
		final TextView btText = (TextView) this.findViewById(R.id.Texto);
		
		btLocaliza.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("MainActivity","Click button");
				
				btText.setText(getResources().getString(R.string.cambio));
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
