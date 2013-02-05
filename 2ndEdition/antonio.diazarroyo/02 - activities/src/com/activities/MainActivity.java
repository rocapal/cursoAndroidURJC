package com.activities;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		Button b1 = (Button)this.findViewById(R.id.firstButton);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
				startActivity(browserIntent);
			}
		});
		Button b2 = (Button)this.findViewById(R.id.secondButton);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openDialer = new Intent (Intent.ACTION_VIEW);
				openDialer.setData(Uri.parse("tel:666-666-666"));
				startActivity(openDialer);
			}
		});
		Button b3 = (Button)this.findViewById(R.id.thirdButton);
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openDialer = new Intent (Intent.ACTION_CALL);
				openDialer.setData(Uri.parse("tel:666-666-666"));
				startActivity(openDialer);
			}
		});
		
		
		return true;
	}

}
