package es.curso.android.apps;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);
		TextView tvTexto = (TextView) this.findViewById( R.id.tvTexto2);
		
		tvTexto.setText( "This is Activity 2: " + getClass().getSimpleName());
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra(Main.PARAM, 20);
		setResult(RESULT_OK, returnIntent);
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("MAIN", "hola onDestroy() 2");
		
		// Info retorno: Se puede meter en el onCreate(), pero mejor aqui
		// Si llamo a finish() desde el onCreate, tambiñen se ejecuta esto
		
		
	}
	
}
