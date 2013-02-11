package es.curso.activitiesandrelatives;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class actividad1 extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.framelayout);
		
		//Debemos obtener el intent que se envia
		Intent i = getIntent();
		//EL intent puede ser nulo. Hay que comprobarlo
		TextView tv = (TextView) this.findViewById(R.id.tvTitle);
		
		if (i!=null){
			String name = i.getStringExtra(MainActivity.VALUE_1);
			Integer num = i.getIntExtra(MainActivity.VALUE_2, -1);
			tv.setText(name + " " + num.toString());
		}
		
	
	}
}
