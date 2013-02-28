package com.example.hilos;

import java.net.URI;


import android.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main_Hilo extends Activity {

	Context contexto = null;
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_hilo);
		
		Button boton = (Button) findViewById(R.id.boton);
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new HiloSec().execute();
				
			}
		});
		
		
		
	}

	

	public class HiloSec extends AsyncTask <URI, Integer, Integer> {

		ProgressDialog pd = null;
		
		@Override
		protected void onPreExecute() {
			
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd = ProgressDialog.show(contexto, "Prueba_asyncTask", "Mostrando progreso");
			
					
		}
		
		
		@Override
		protected Integer doInBackground(URI... params) {
			// TODO Auto-generated method stub
			
			for (int i=0; i<0; i++){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return null;
		}

		
		protected void onPostExecute(){
			pd.dismiss();
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main__hilo, menu);
		return true;
	}

}
