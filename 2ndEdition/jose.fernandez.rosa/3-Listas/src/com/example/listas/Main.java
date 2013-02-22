package com.example.listas;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnSimple=(Button) findViewById(R.id.btn_simple);
		btnSimple.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Main.this,SimpleList.class);
				startActivity(i);
			}
		});
		
		Button btnAdvanced=(Button) findViewById(R.id.btn_advanced);
		btnAdvanced.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Main.this,AdvancedList.class);
				startActivity(i);
			}
		});
		
	}


 
}
