package es.android.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ActividadTres extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad_retorno);
		
		TextView tv = (TextView) this.findViewById(R.id.retorno);
		tv.setText(getClass().getSimpleName());
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra(ActividadUno.PARAM, 20);
		setResult(RESULT_OK, returnIntent);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("MAIN", "hola tres");
	}
}
