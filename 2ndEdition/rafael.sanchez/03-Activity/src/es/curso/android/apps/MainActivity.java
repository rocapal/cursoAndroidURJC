package es.curso.android.apps;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btButton1 = (Button) this.findViewById( R.id.btButton1);
		Button btButton2 = (Button) this.findViewById( R.id.btButton2);
		Button btButton3 = (Button) this.findViewById( R.id.btButton3);
		
		
		
		btButton1.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("Main", "Click button 1");
				Intent browserInternet = new Intent(Intent.ACTION_VIEW, 
							Uri.parse("http://www.google.es")); 
				startActivity( browserInternet );
			}
		});	
		
		btButton2.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("Main", "Click button 2");
				Intent intent = new Intent(Intent.ACTION_VIEW); 
				intent.setData(Uri.parse("tel:777-777-777")); 
				startActivity( intent );
			}
		});	
		
		btButton3.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("Main", "Click button 3");
				Intent intent = new Intent(Intent.ACTION_CALL); 
				intent.setData(Uri.parse("tel:777-777-777")); 
				startActivity( intent );
			}
		});	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
