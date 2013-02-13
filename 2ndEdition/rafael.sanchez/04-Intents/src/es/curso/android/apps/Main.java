package es.curso.android.apps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
	
	public final static String VALOR_1 = "1";
	public final static String VALOR_2 = "2";
	
	public final static int FROM_ACTIVITY_2 = 1;
	public final static int FROM_ACTIVITY_3 = 2;
	
	public final static String PARAM = "1";
	
	Button btGoToActivity1, btGoToActivity2, btGoToActivity3; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		btGoToActivity1 = (Button) this.findViewById( R.id.btGoToActivity1);
		btGoToActivity2 = (Button) this.findViewById( R.id.btGoToActivity2);
		btGoToActivity3 = (Button) this.findViewById( R.id.btGoToActivity3);
		Button btButton1 = (Button) this.findViewById( R.id.btButton1);
		Button btButton2 = (Button) this.findViewById( R.id.btButton2);
		Button btButton3 = (Button) this.findViewById( R.id.btButton3);
		
		
		btGoToActivity1.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("Main", "GoToActivity1 button clicked");
				Intent intent = new Intent(Main.this, Activity1.class);
				intent.putExtra(Main.VALOR_1, "Cadena transferidad entre Activities");
				intent.putExtra(Main.VALOR_2, 23);
				startActivity( intent );
			}
		});
		
		btGoToActivity2.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("Main", "GoToActivity2 button clicked");
				Intent intent = new Intent(Main.this, Activity2.class);
				startActivityForResult(intent, Main.FROM_ACTIVITY_2);
			}
		});	
		
		btGoToActivity3.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("Main", "GoToActivity2 button clicked");
				Intent intent = new Intent(Main.this, Activity3.class);
				startActivityForResult(intent, Main.FROM_ACTIVITY_3);
			}
		});	
		
		
		
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
	protected void onActivityResult(int requestCode,int resultCode,Intent data) {
		
		Integer value = data.getIntExtra(PARAM, -1);
		
		if (resultCode == Activity.RESULT_OK ) {
			if (requestCode == FROM_ACTIVITY_2) {		
					Toast.makeText(this, "Hola A2: " + String.valueOf(value), 
							Toast.LENGTH_SHORT).show();
			}
			if (requestCode == FROM_ACTIVITY_3) {
				Toast.makeText(this, "Hola A3: " + String.valueOf(value), 
						Toast.LENGTH_SHORT).show();
			}
		}
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
			case R.id.menu_activity2:
				Log.d("Main", "Activity2 menu clicked");
				intent = new Intent(Main.this, Activity2.class);
				startActivityForResult(intent, Main.FROM_ACTIVITY_2);
				return true;
			case R.id.menu_activity3:
				Log.d("Main", "Activity3 menu clicked");
				intent = new Intent(Main.this, Activity3.class);
				startActivityForResult(intent, Main.FROM_ACTIVITY_3);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

}
