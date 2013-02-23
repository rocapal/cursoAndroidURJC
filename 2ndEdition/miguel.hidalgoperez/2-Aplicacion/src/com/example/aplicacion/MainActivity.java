package com.example.aplicacion;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static final String VALUE_1 = "TEXT";
	public static final String VALUE_2 = "INTEGER";
	public static final String PARAM = "PARAM";
	public static final int FROM_ACTIVITY_2 = 2;
	public static final int FROM_ACTIVITY_3 = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn1 = (Button) this.findViewById(R.id.btn1);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.google.es"));
				startActivity(intent);
			}	
		});
		
		Button btn2 = (Button) this.findViewById(R.id.btn2);
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Activity1.class);
				intent.putExtra(VALUE_1, getString(R.string.app_name));
				intent.putExtra(VALUE_2, 3);
				startActivity(intent);
			}
		});
		
		Button btn3 = (Button) this.findViewById(R.id.btn3);
		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Activity2.class);
				startActivityForResult(intent, FROM_ACTIVITY_2);
			}
		});
		
		Button btn4 = (Button) this.findViewById(R.id.btn4);
		btn4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Activity3.class);
				startActivityForResult(intent, FROM_ACTIVITY_3);
			}
		});
	}
	
	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK){
			if (requestCode == FROM_ACTIVITY_2)
				Toast.makeText(MainActivity.this, "vengo de la actividad 2", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(MainActivity.this, "vengo de la actividad 3", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu2, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
		case R.id.menu_activity1:
			Intent intent = new Intent(MainActivity.this, Activity2.class);
			startActivityForResult(intent, FROM_ACTIVITY_2);
			return true;
		case R.id.menu_activity2:	
			intent = new Intent(MainActivity.this, Activity3.class);
			startActivityForResult(intent, FROM_ACTIVITY_3);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}	
	}

}
