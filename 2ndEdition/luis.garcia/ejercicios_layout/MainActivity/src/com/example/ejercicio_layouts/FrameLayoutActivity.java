package com.example.ejercicio_layouts;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class FrameLayoutActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(this.getClass().toString(),"onCreate");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_layout);
		
		setWidgets();
		
	}
	
	public void setWidgets(){
		Log.d(this.getLocalClassName(),"setWidgets");
		
		Button btnBig = (Button) this.findViewById(R.id.btnBig);
		Button btnLittle = (Button) this.findViewById(R.id.btnLittle);
		
		btnBig.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(this.getClass().toString(),"onClick");
				Button btnBig = (Button)v;
				Toast.makeText(v.getContext(), btnBig.getText().toString(), Toast.LENGTH_LONG).show();
			}
		});
		
		btnLittle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(this.getClass().toString(),"onClick");
				Button btnLittle = (Button)v;
				Toast.makeText(v.getContext(), btnLittle.getText().toString(), Toast.LENGTH_LONG).show();
			}
		});
		
	}
	
}