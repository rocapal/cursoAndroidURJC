package es.cursoandorid.yncat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	public static Context mContexto;
	SharedPreferences  mSharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContexto = this.getApplicationContext();
		Button bmap = (Button) findViewById(R.id.btnmapa);
		bmap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(mContexto, MapasActivity.class);
				startActivity(i);
				
			}
		});
		Button bfoto = (Button) findViewById(R.id.btnfoto);
		bfoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(mContexto, PhotoIntentActivity.class);
				startActivity(i);
				
			}
		});
		
		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
				
	}
	
	private void savePreference (String key, String value)
	{
		Editor editor = mSharedPreferences.edit();
		editor.putString(key,value);
		editor.commit();
	}
	
	private String loadPreference(String key)
	{
		return mSharedPreferences.getString(key, null);		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
