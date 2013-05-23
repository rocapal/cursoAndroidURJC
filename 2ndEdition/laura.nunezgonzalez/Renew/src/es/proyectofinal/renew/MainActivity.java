package es.proyectofinal.renew;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView v1 = (ImageView) findViewById(R.id.boton01);
		v1.setImageResource(R.drawable.android1);
/*		final ImageView btn1= (ImageView) findViewById(R.id.boton01);;
    	if (Math.random() > 0.5)
    		btn1.setImageResource(R.drawable.android1);
    	else
    		btn1.setImageResource(R.drawable.android2);
    	btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (btn1.get.equals(R.drawable.android2))
					btn1.setBackgroundResource(R.drawable.android1);
				else 		
					btn1.setBackgroundResource(R.drawable.android2);
			}
		});		*/	

	}

	public void cambiar(View v)
	{
		ImageView v1 = (ImageView) v.findViewById(R.id.boton01);
		if (v1.getImageMatrix().equals(R.drawable.android2))
			v1.setBackgroundResource(R.drawable.android1);
		else 		
			v1.setBackgroundResource(R.drawable.android2);
		Log.d("Cambio", "cambiado");
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
