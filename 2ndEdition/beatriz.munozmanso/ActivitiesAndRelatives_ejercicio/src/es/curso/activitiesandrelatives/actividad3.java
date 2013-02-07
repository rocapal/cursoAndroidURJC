package es.curso.activitiesandrelatives;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class actividad3 extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_ver);
		
		Button boton1 = (Button) this.findViewById(R.id.plan);
		boton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				vibrator.vibrate(50);
				//Intent browserIntent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
				//startActivity(browserIntent);	
			}	
		});	
	}
}
