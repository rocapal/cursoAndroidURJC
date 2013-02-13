package es.curso.android.apps;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d("Main", "pepe");
		
		Button btLocalize = (Button) this.findViewById( R.id.btLocalize);
		final TextView tvHello = (TextView) this.findViewById( R.id.tvHello );
		btLocalize.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("Main", "Click button");
				tvHello.setText("Nuevo hello");
				tvHello.setText(getResources().getString(R.id.left_wrap));
				Button b = (Button) v;
				b.setText("Hola");
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
