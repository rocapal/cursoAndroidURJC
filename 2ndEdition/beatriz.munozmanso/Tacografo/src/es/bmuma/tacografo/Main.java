package es.bmuma.tacografo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;



public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ImageButton btGestionTime = (ImageButton) this.findViewById(R.id.Button1);
		btGestionTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getBaseContext(), TimesActivity.class);
				startActivity(browserIntent);
			}
		});
		
		ImageButton btListTime = (ImageButton) this.findViewById(R.id.Button2);
		btListTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getBaseContext(), ListTime.class);
				browserIntent.putExtra("type", 0);
				startActivity(browserIntent);
			}
		});
		
		
		ImageButton btMap = (ImageButton) this.findViewById(R.id.Button3);
		btMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getBaseContext(), Mapa.class);
				startActivity(browserIntent);
			}
		});
		
		ImageButton btLang = (ImageButton) this.findViewById(R.id.Button4);
		btLang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getBaseContext(), Prefer.class);
				startActivity(browserIntent);
			}
		});
		
		ImageButton btEmergeny = (ImageButton) this.findViewById(R.id.Button5);
		btEmergeny.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					   startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:112")));
					}catch(Exception e){
						Toast.makeText(getApplicationContext(), getString(R.string.noEmergencyCall), Toast.LENGTH_SHORT).show();
					}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
