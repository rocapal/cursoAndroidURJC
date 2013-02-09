package curso.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Obtiene el botón para abrir la uri de google
		Button btn1 = (Button) this.findViewById(R.id.btn_uri);
		
		//Añade el callback para lanzar la página de google
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent browserIntent= new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://www.google.es"));
				startActivity(browserIntent);
			}
		});
		
		//Obtiene el botón para llamar por teléfono
		Button btn2 = (Button) this.findViewById(R.id.btn_tlf);
		//Añade el callback para llamar
	    btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent tlfIntent = new Intent(Intent.ACTION_CALL);
				tlfIntent.setData(Uri.parse("tel:677-371-051"));
				startActivity(tlfIntent);
			}
		});
	    //Obtiene el botón para mostrar el teléfono
	    Button btn3 = (Button) this.findViewById(R.id.btn_viewtlf);
	    //Añade el callback para mostrar el número
	    btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent tlfIntent = new Intent(Intent.ACTION_VIEW);
				tlfIntent.setData(Uri.parse("tel:677-371-051"));
				startActivity(tlfIntent);
				
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
