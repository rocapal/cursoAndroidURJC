package es.curso.serviceproyect;


import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity{
	
	private Handler myHandler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			Toast.makeText(getBaseContext(), getString(R.string.hello_world,
					msg.what),Toast.LENGTH_SHORT).show();
			return false;
		}
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startService(new Intent(this, ServiceGPS.class));
		
		final TextView tv = (TextView) this.findViewById(R.id.location);
		
		ServiceGPS.regListener(new IGpsLocation() {
			
			@Override
			public void setLocation(Location location) {
				
				tv.setText(String.valueOf(location.getLatitude()) + " "
						+ String.valueOf(location.getLongitude()));
				
			}
		});
		
		
		Button bTimerTask = (Button) this.findViewById(R.id.boton2);
		bTimerTask.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopService(new Intent(getApplicationContext(), MyService.class));
			}
		});
		
		Button buttomHandler = (Button) this.findViewById(R.id.boton);
		buttomHandler.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startService(new Intent(getApplicationContext(), MyService.class));
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
