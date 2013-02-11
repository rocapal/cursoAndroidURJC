package es.curso.activitiesandrelatives;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class actividad5 extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.complex);
		
		Button boton1 = (Button)this.findViewById(R.id.boton1);
		Button boton2 = (Button)this.findViewById(R.id.boton2);
		Button boton3 = (Button)this.findViewById(R.id.boton3);
		
		boton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.es"));
				startActivity(browserIntent);
			}
		});
		//Para el boton 2
		boton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("tel:123-456-789"));
				startActivity(intent);
				
				
			}
		});
		//Para el boton3
		boton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(Intent.ACTION_CALL);
				intent2.setData(Uri.parse("tel:123-456-789"));
				startActivity(intent2);
				
			}
		});
	}
}
