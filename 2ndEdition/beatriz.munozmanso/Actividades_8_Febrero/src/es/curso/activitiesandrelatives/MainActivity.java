package es.curso.activitiesandrelatives;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	//Definimos los identificadores como contantes para todo el programa
	final static public String VALUE_1 = "TEXT";
	final static public String VALUE_2 = "INT";
	final static public String PARAM = "STRING";
	final static public int FROM_ACTIVITY_2 = 2;
	final static public int FROM_ACTIVITY_3 = 3;
	
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
		//Queremos pasar información extra	
		@Override
		public void onClick(View v) {
			Intent browserIntent = new Intent(MainActivity.this, actividad1.class);
			//El primer parámetro será un string identificador. Se deben definir como
			//una constante.
			browserIntent.putExtra(VALUE_1, getString(R.string.app_name));
			browserIntent.putExtra(VALUE_2, 3);
			startActivity(browserIntent);
		}
		});
		
		boton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getBaseContext(), actividad2.class);
				startActivityForResult(browserIntent, FROM_ACTIVITY_2);
			}
		});
		boton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getBaseContext(), actividad3.class);
				startActivityForResult(browserIntent, FROM_ACTIVITY_3);
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
		//Se encarga de que se visualize el menú
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()){
		case R.id.Menu1:
			Intent intent = new Intent(MainActivity.this, actividad2.class);
			startActivityForResult(intent, FROM_ACTIVITY_2);
			//Si se consume el menú, se devuelve true, sino se manda al padre
			return true;
			
		case R.id.Menu2:
			Intent intent2 = new Intent(MainActivity.this, actividad3.class);
			startActivityForResult(intent2, FROM_ACTIVITY_3);
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == Activity.RESULT_OK){
			Integer value = data.getIntExtra(PARAM, -1);
			if(requestCode == FROM_ACTIVITY_2)
				//Mensaje durante un número de segundos, que no necesita interaccion
				Toast.makeText(this, "Come back from activity 2: " + value.toString() , Toast.LENGTH_SHORT).show();
			if(requestCode == FROM_ACTIVITY_3)
				Toast.makeText(this, "Come back from activity 3: " + value.toString() , Toast.LENGTH_SHORT).show();				
		}
	}
		
}
