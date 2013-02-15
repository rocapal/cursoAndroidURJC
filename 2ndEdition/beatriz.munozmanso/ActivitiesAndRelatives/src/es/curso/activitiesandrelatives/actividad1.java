package es.curso.activitiesandrelatives;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class actividad1 extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.framelayout);
		
		Button boton1 = (Button) this.findViewById(R.id.Goog);
		boton1.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.es"));
				startActivity(browserIntent);	
			}	
		});	
	}
}
