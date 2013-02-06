package com.example.lanzamientos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		Button boton1 = (Button) this.findViewById(R.id.boton1);
		boton1.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent iUrl = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.es"));
				startActivity(iUrl);
			}
		});

		Button boton2 = (Button) this.findViewById(R.id.boton2);
		boton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent iViewPhone = new Intent(Intent.ACTION_VIEW);
				iViewPhone.setData(Uri.parse("tel:677-371-074"));
				startActivity(iViewPhone);
			}
		});
		
		Button boton3 = (Button) this.findViewById(R.id.boton3);
		boton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent iCallPhone = new Intent(Intent.ACTION_CALL);
				iCallPhone.setData(Uri.parse("tel:677-371-074"));
				startActivity(iCallPhone);
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
