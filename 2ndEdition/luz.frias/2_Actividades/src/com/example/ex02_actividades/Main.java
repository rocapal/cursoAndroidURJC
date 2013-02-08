package com.example.ex02_actividades;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
	
	final static String MSG1 = "MSG1";
	final static String MSG2 = "MSG2";
	final static String MSG3 = "MSG3";
	final static String MSG4 = "MSG4";

	final static int ACTIVITY_REQ_2 = 2;
	final static int ACTIVITY_REQ_3 = 3;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Recuperamos los botones
        Button button1 = (Button) this.findViewById(R.id.button1);
        Button button2 = (Button) this.findViewById(R.id.button2);
        Button button3 = (Button) this.findViewById(R.id.button3);
        Button button4 = (Button) this.findViewById(R.id.button4);
        Button button5 = (Button) this.findViewById(R.id.button5);
        
        // Acción botón 1: Impresión de parámetros
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this, Activity1.class);
				intent.putExtra(MSG1, Main.this.getString(R.string.txMensaje1));
				intent.putExtra(MSG2, 200);
//				Log.d("Boton", "String: " + Main.this.getString(R.string.txMensaje1));
				startActivity(intent);
			}
        });
        
        // Acción botón 2: Muestra Actividad 2, esperamos parámetro de retorno
        button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this, Activity2.class);
				startActivityForResult(intent, Main.ACTIVITY_REQ_2);
			}
        });
        
     // Acción botón 3: Muestra Actividad 3, esperamos parámetro de retorno
        button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this, Activity3.class);
				startActivityForResult(intent, Main.ACTIVITY_REQ_3);
			}
        });
        
        // Acción botón 4: Abre el explorador
        button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.es"));
				startActivity(browserIntent);
			}
        });

        // Acción botón 5: Llama
        button5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent telIntent = new Intent(Intent.ACTION_CALL);
				telIntent.setData(Uri.parse("tel:666-666-666"));
				startActivity(telIntent);
			}
        });

    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.menu1:
			Intent i1 = new Intent(Main.this, Activity2.class);
			startActivityForResult(i1, Main.ACTIVITY_REQ_2);
    		return true;
    	case R.id.menu2:
			Intent i2 = new Intent(Main.this, Activity3.class);
			startActivityForResult(i2, Main.ACTIVITY_REQ_3);
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    	
    }
    
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if (resultCode == Activity.RESULT_OK) {
    		
	    	// Retorno de Activity2
		    if (requestCode == ACTIVITY_REQ_2) {
		    	int paramRetorno = data.getIntExtra(MSG3, -1);
			    Toast.makeText(Main.this, "Volviendo de Actividad 2, i=" + paramRetorno, Toast.LENGTH_SHORT).show();
		    }
		    
		    //Retorno de Activity3
		    else if (requestCode == ACTIVITY_REQ_3) {
		    	int paramRetorno = data.getIntExtra(MSG4, -1);
		    	Toast.makeText(Main.this, "Volviendo de Actividad 3, i=" + paramRetorno, Toast.LENGTH_SHORT).show();
		    }
    	}
    }
    
}
