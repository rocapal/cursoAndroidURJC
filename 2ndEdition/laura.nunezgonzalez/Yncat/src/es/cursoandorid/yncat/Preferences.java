package es.cursoandorid.yncat;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Preferences extends Activity {
	
	private Context mContext;
	private EditText edzoom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferences);
		this.mContext = MainActivity.mContexto;
		savePreference(mContext.getResources().getString(R.string.pref_color), Color.BLUE);
		savePreference(mContext.getResources().getString(R.string.pref_zoom), 15);
		Button btnbe = (Button) findViewById(R.id.btnblue);
		Button btny = (Button) findViewById(R.id.btnyellow);
		Button btnr = (Button) findViewById(R.id.btnred);
		Button btnbc = (Button) findViewById(R.id.btnblack);
		btnbe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				savePreference(mContext.getResources().getString(R.string.pref_color), Color.BLUE);
				Toast.makeText(mContext, mContext.getResources().getString(R.string.color_change), 
						Toast.LENGTH_SHORT).show();
			}
		});
		btny.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				savePreference(mContext.getResources().getString(R.string.pref_color), Color.YELLOW);
				Toast.makeText(mContext, mContext.getResources().getString(R.string.color_change), 
						Toast.LENGTH_SHORT).show();				
			}
		});
		btnr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				savePreference(mContext.getResources().getString(R.string.pref_color), Color.RED);
				Toast.makeText(mContext, mContext.getResources().getString(R.string.color_change), 
						Toast.LENGTH_SHORT).show();				
			}
		});
		btnbc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				savePreference(mContext.getResources().getString(R.string.pref_color), Color.BLACK);
				Toast.makeText(mContext, mContext.getResources().getString(R.string.color_change), 
						Toast.LENGTH_SHORT).show();				
			}
		});
		Button btnsavezoom = (Button) findViewById(R.id.btnsavezoom);
		edzoom = (EditText) findViewById(R.id.edit_zoom);
		btnsavezoom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (edzoom.getText().toString().length() > 0) {
					try{
					int zoom = Integer.parseInt(edzoom.getText().toString());
						if(zoom < 22 && zoom > 1)
						{
							savePreference(edzoom.getText().toString(), zoom);
							Toast.makeText(mContext, getString(R.string.msg_zoom_ok), Toast.LENGTH_SHORT).show();
						}else
							Toast.makeText(mContext, getString(R.string.msg_zoom_invalid), Toast.LENGTH_SHORT).show();
					}catch (NumberFormatException e) {
						// TODO: handle exception
						Toast.makeText(mContext, getString(R.string.msg_zoom_invalid), Toast.LENGTH_SHORT).show();
					}
				}
				else {
					Toast.makeText(mContext, getString(R.string.msg_zoom_invalid), Toast.LENGTH_SHORT).show();
				}				
			}
		});
		
	}
	
	private void savePreference (String key, String value)
	{
		Editor editor = MainActivity.mSharedPreferences.edit();
		editor.putString(key,value);
		editor.commit();
	}
	
	private String loadPreference(String key)
	{
		return MainActivity.mSharedPreferences.getString(key, null);		
	}

	private void savePreference(String key, int value)
	{
		Editor editor = MainActivity.mSharedPreferences.edit();
		editor.putInt(key,value);
		editor.commit();
	}
	
	private int loadPreference(String key, int def)
	{
		return MainActivity.mSharedPreferences.getInt(key,def);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
