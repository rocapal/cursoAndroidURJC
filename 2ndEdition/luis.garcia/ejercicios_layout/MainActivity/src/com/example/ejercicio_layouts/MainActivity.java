package com.example.ejercicio_layouts;

import com.example.ejercicio_layouts.FrameLayoutActivity;
import com.example.ejercicio_layouts.MainActivity;
import com.example.ejercicio_layouts.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	public void setWidgets(){
		Log.d("MainActivity","setWidgets");
		
		Button btnFrameLayout = (Button) this.findViewById(R.id.btnFrameLayout);
		Button btnLinearLayout = (Button) this.findViewById(R.id.btnLinearLayout);
		Button btnRelativeLayout = (Button) this.findViewById(R.id.btnRelativeLayout);
		Button btnTableLayout = (Button) this.findViewById(R.id.btnTableLayout);
		Button btnEjercicio = (Button) this.findViewById(R.id.btnEjercicio);
		
		btnFrameLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, FrameLayoutActivity.class );
				startActivity(intent);
			}
		});
		
		btnLinearLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, LinearLayoutActivity.class );
				startActivity(intent);
			}
		});		
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setWidgets();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
