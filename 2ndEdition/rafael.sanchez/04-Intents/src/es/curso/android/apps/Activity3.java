package es.curso.android.apps;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity3);
		TextView tvTexto = (TextView) this.findViewById( R.id.tvTexto3);
		
		tvTexto.setText( "This is Activity 3: " + getClass().getSimpleName());
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra(Main.PARAM, 30);
		setResult(RESULT_OK, returnIntent);
		
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("MAIN", "hola onPause() 3");
		
		// Info retorno: Se puede meter en el onCreate(), pero mejor aqui
		// Si llamo a finish() desde el onCreate, tambiñen se ejecuta esto
		

		
		
	}
	
}
