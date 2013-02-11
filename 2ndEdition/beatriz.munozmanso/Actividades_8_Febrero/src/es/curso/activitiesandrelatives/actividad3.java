package es.curso.activitiesandrelatives;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class actividad3 extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.framelayout);
		
		TextView tv = (TextView) this.findViewById(R.id.tvTitle);
		tv.setText(R.string.app_name3);
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra(MainActivity.PARAM, 30);
		setResult(RESULT_OK, returnIntent);
	}
	
}
