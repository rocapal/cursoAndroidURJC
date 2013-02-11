package es.curso.activitiesandrelatives;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class actividad2 extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.framelayout);
		
		TextView tv = (TextView) this.findViewById(R.id.tvTitle);
		//Nombre de la clase
		tv.setText(getClass().getSimpleName());
		Intent returnIntent = new Intent();
		returnIntent.putExtra(MainActivity.PARAM, 20);
		setResult(RESULT_OK, returnIntent);
		
	}
	
}
