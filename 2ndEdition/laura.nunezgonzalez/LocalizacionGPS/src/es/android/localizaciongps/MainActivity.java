package es.android.localizaciongps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static Handler mHandler;
	private Context mContexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("TAG","Iniciando");
		setContentView(R.layout.activity_main);
		mContexto = this.getApplicationContext();
		mHandler = new Handler(new Callback(){
			
		public boolean handleMessage(Message msg) {
				Log.d("TAG", "Llega mensaje");
				Toast.makeText(mContexto, mContexto.getString(R.string.toast_msg_location) + String.valueOf(msg.arg1) +
						"," + String.valueOf(msg.arg2),
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		startService(new Intent(getApplicationContext(), LocationService.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
