package es.android.servicios;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity  {
	private final String TAG = getClass().getSimpleName();
	public static Context context;
	private  TextView mTexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context=this;		
		mTexto= (TextView) findViewById(R.id.texto);
		
		Button btnStart   = (Button) findViewById(R.id.btn_start);
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initService();
			}
		});
		
		Button btnStop = (Button) findViewById(R.id.btn_stop);
		btnStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cancelService();
			}
		});
		
		
	}

	private void  initService() {
		Log.d(TAG,"Iniciando el servicio");
		
		MyService.regIServiceListener(new IServiceListener() {			
			@Override
			public void updateMessage(String cadena) {
				Main.this.setTexto(cadena);
				
			}
		});
		
		startService(new Intent(getApplicationContext(),MyService.class));	
	}
	
	private void cancelService(){
		Log.d(TAG,"Parando el servicio");
		stopService(new Intent(getApplicationContext(),MyService.class));	
	}
	
	public void setTexto(String cadena){
		if (mTexto!=null) 
			mTexto.setText(cadena);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
	
	

}
