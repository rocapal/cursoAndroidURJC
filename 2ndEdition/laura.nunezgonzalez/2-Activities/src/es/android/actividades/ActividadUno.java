package es.android.actividades;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ActividadUno extends Activity {

	public static final String VALUE_1 = "TEXT";
	public static final String VALUE_2 = "INT";
	public static final String PARAM = "PARAM";
	private static final int ACTIVITY_REQUEST_1 = 1;
	private static final int ACTIVITY_REQUEST_2 = 2;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad_uno);
		
		Button bt1 = (Button) this.findViewById(R.id.boton1);
		Button bt2 = (Button) this.findViewById(R.id.boton2);
		Button bt3 = (Button) this.findViewById(R.id.boton3);
		Button bt4 = (Button) this.findViewById(R.id.boton4);
		Button bt5 = (Button) this.findViewById(R.id.boton5);		
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Para abrir navegador
				Intent browserIntent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://www.google.es"));
				startActivity(browserIntent);
				
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Para llamar
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:666-666-666"));
				startActivity(intent);
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Para pasar a otra actividad definida por mi y pasarle parámetro
				Intent intent = new Intent(ActividadUno.this, ActividadDos.class);
				intent.putExtra(VALUE_1, getString(R.string.app_name));
				intent.putExtra(VALUE_2, 3);
				startActivity(intent);
				
			}
		});		

		bt4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ActividadUno.this, ActividadTres.class);
				startActivityForResult(intent, ACTIVITY_REQUEST_1);
				
			}
		});		
		
		bt5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ActividadUno.this, ActividadCuatro.class);
				startActivityForResult(intent, ACTIVITY_REQUEST_2);				
			}
		});	

/*		bt4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Para llamar
				Intent intent = new Intent();
				intent.setAction(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivity(intent);
				
			}
		});		
		
		bt5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Para llamar
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("google.streetview:cbll=40.284043650362996,-3.8391530513763428&cbp=1,99.56,,1,-5.27&mz=21"));
				startActivity(intent);
				
			}
		});		*/
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == Activity.RESULT_OK)
		{
			Integer value = data.getIntExtra(PARAM, -1);
			
			if (requestCode == ACTIVITY_REQUEST_1) {
				Toast.makeText(this, "Vuelve del boton 4: " + value.toString(), 
						Toast.LENGTH_SHORT).show();
			} else if (requestCode == ACTIVITY_REQUEST_2) {
				Toast.makeText(this, "Vuelve del boton 5: " + value.toString(), 
						Toast.LENGTH_SHORT).show();
				
			}
		}
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actividad_uno, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		
			case R.id.menu1:
				Intent intent = new Intent(ActividadUno.this, ActividadDos.class);
				intent.putExtra(VALUE_1, getString(R.string.app_name));
				intent.putExtra(VALUE_2, 3);
				startActivity(intent);
				return true;
			case R.id.menu2:
				Intent intent2 = new Intent(ActividadUno.this, ActividadTres.class);
				startActivityForResult(intent2, ACTIVITY_REQUEST_1);
				return true;
			default:
				return super.onMenuItemSelected(featureId, item);

		}
	}

}
