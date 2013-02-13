package es.curso.android.apps;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
		TextView tvTexto = (TextView) this.findViewById( R.id.tvTexto1);
		
		Intent i = getIntent();
		if (i != null) {
			String name = i.getStringExtra(Main.VALOR_1);
			Integer num = i.getIntExtra(Main.VALOR_2, -1);
			tvTexto.setText( name + " " + num.toString());		
		}
	}
	
}
