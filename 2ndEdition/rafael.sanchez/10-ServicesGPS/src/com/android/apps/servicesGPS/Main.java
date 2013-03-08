package com.android.apps.servicesGPS;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	
	TextView tvPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		

		tvPosition = (TextView) findViewById (R.id.tvPosition);
		tvPosition.setText("Introduzca longitud/latitud:");
		
		MyService.regListener (new ILocation() {
			
			@Override
			public void updateLocation(Location location) {
				tvPosition.setText(String.valueOf(location.getLongitude()) + " " +
						String.valueOf(location.getLatitude()));
			
			}
		});
		
		
		Toast.makeText(this, "onCreate() launching seervice",
				Toast.LENGTH_SHORT).show();
		
		startService(new Intent(getApplicationContext(),
						MyService.class));
		
		
		
	}
	
	/*public static void actualiza(Context context, String texto) {
		Toast.makeText(context, texto,
				Toast.LENGTH_SHORT).show();
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
