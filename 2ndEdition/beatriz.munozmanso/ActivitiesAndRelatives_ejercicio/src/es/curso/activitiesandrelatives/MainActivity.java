package es.curso.activitiesandrelatives;

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
		
		Button boton1 = (Button)this.findViewById(R.id.frame);
		Button boton2 = (Button)this.findViewById(R.id.horiz);
		Button boton3 = (Button)this.findViewById(R.id.vert);
		Button boton4 = (Button)this.findViewById(R.id.rel);
		Button boton5 = (Button)this.findViewById(R.id.comp);
		
		boton1.setOnClickListener(new OnClickListener() {
			
		@Override
		public void onClick(View v) {
			Intent browserIntent = new Intent(getBaseContext(), actividad1.class);
			startActivity(browserIntent);
		}
		});
		
		boton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getBaseContext(), actividad2.class);
				startActivity(browserIntent);
			}
		});
		
		boton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getBaseContext(), actividad3.class);
				startActivity(browserIntent);
			}
		});
		
		boton4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getBaseContext(), actividad4.class);
				startActivity(browserIntent);
			}
		});
		
		boton5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getBaseContext(), actividad5.class);
				startActivity(browserIntent);
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
