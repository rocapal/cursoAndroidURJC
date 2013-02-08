package com.example.ex02_actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity1 extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_actividad1);

		// Recuperamos elemento TextView
		TextView text = (TextView) this.findViewById(R.id.txContenido1);
		
		// Recuperamos parámetros extra
		Intent intent = this.getIntent();
		
		if (intent != null) {

			String mensaje1 = intent.getStringExtra(Main.MSG1);
			int mensaje2 = intent.getIntExtra(Main.MSG2, -1);
		
			text.setText(mensaje1 + " " + mensaje2);

		}
	}
}
