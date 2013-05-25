package es.cursoandorid.yncat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static SharedPreferences  mSharedPreferences;
	public static Context mContext;
	public static String searchString;
	private EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this.getApplicationContext();
		MainActivity.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
		Button bmap = (Button) findViewById(R.id.btnmapa);
		bmap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(mContext, YncatMapsActivity.class);
				startActivity(i);
				
			}
		});
		
		Button bpref = (Button) findViewById(R.id.btnpreferences);
		bpref.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(mContext, Preferences.class);
				startActivity(i);				
			}
		});
		
		et = (EditText) findViewById(R.id.edhashtag);
		Button bhas = (Button) findViewById(R.id.btnhashtag);
		bhas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et.getText().toString().length() > 0) {
					searchString = et.getText().toString();
					Intent i = new Intent(mContext, Hashtag.class);
					startActivity(i);
				}
				else {
					Toast.makeText(mContext, getString(R.string.msg_error_busqueda), Toast.LENGTH_SHORT).show();
				}				
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
