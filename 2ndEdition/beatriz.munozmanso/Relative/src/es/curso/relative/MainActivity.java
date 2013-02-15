package es.curso.relative;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_main);
		
		//final = no se puede cambiar la referencia a memoria
		final TextView btText = (TextView)this.findViewById(R.id.prueba);
		
		Button locale = (Button)this.findViewById(R.id.mapaboton);
		//Para que el botón haga algo cuando lo pinchamos
		
		
		
		locale.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("MainActivity","Click Button Localize");
				//Cambia el texto cuando haces click
				btText.setText("Click");
			}
		});
		//Mandamos el contexto
		//a.text(this)
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
