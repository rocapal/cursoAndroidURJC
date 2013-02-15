package com.example.ex02_actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity2 extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_actividades2y3);

		// Imprimimos el nombre de la Actividad
		TextView text = (TextView) this.findViewById(R.id.txActividad2y3);
		text.setText(getClass().getSimpleName());
		
		// Insertamos parámetros de retorno
		Intent intentRetorno = new Intent();
		intentRetorno.putExtra(Main.MSG3, 20);
		setResult(Activity.RESULT_OK, intentRetorno);

	}
	
	protected void onDestroy() {
		super.onDestroy();
		
		Log.d("Actividad2", "onDestroy");
	}

}
