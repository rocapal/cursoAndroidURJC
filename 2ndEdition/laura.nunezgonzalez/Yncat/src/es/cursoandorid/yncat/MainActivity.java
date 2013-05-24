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
	public static Context mContexto;
	public static int mColor;
	public static int mZoom;
	public static String searchString;
	private EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContexto = this.getApplicationContext();
		MainActivity.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContexto);
		Button bmap = (Button) findViewById(R.id.btnmapa);
		bmap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(mContexto, MapasActivity.class);
				startActivity(i);
				
			}
		});
		
		Button bpref = (Button) findViewById(R.id.btnpreferences);
		bpref.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(mContexto, Preferences.class);
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
				}
				else {
					Toast.makeText(mContexto, getString(R.string.msg_error_busqueda), Toast.LENGTH_SHORT).show();
				}
				Intent i = new Intent(mContexto, Hashtag.class);
				startActivity(i);				
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
