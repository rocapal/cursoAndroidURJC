package com.activities;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String VALUE_1 = "URL";
	public static final String VALUE_2 = "ID_LAYOUT";
	public static final Integer FROM_ACTIVITY_2 = 2;
	public static final Integer FROM_ACTIVITY_3 = 3;
	public static final String PARAM = "param";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		Button bActivity = (Button)this.findViewById(R.id.activityButton);
		bActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent newActivityIntent = new Intent (MainActivity.this, NewActivity.class);
				newActivityIntent.putExtra(VALUE_1, getString(R.string.app_name));
				newActivityIntent.putExtra(VALUE_2, 69);
				startActivity(newActivityIntent);
			}
		});
		
		Button bActivity02 = (Button)this.findViewById(R.id.activity02Button);
		bActivity02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (MainActivity.this, Activity02.class);
				startActivityForResult(intent, FROM_ACTIVITY_2);
			}
		});
		
		Button bActivity03 = (Button)this.findViewById(R.id.activity03Button);
		bActivity03.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (MainActivity.this, Activity03.class);
				startActivityForResult(intent, FROM_ACTIVITY_3);
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu1:
			Intent intent02 = new Intent (MainActivity.this, Activity02.class);
			startActivityForResult(intent02, FROM_ACTIVITY_2);
			return true;
		case R.id.menu2:
			Intent intent03 = new Intent (MainActivity.this, Activity03.class);
			startActivityForResult(intent03, FROM_ACTIVITY_3);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("etiqueta", "pasa ..." + resultCode);
		if (resultCode == Activity.RESULT_OK) {
			Integer value = data.getIntExtra(PARAM, -1);
			if (requestCode == FROM_ACTIVITY_2) {
				Toast.makeText(MainActivity.this, "Hola Actividad 02 - "+ value, Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
