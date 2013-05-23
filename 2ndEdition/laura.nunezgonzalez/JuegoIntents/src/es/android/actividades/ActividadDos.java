package es.android.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActividadDos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad_texto);
		
		TextView tv = (TextView) this.findViewById(R.id.tvTitle);
		Intent i = getIntent();
		
		if (i != null)
		{
			String name = i.getStringExtra(ActividadUno.VALUE_1);
			Integer num = i.getIntExtra(ActividadUno.VALUE_2, -1);	
			tv.setText(name + " " + num.toString());
		}
	}

}
