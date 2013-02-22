package com.example.ejercicio_layouts;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class LinearLayoutActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(this.getClass().toString(),"onCreate");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linear_layout);
		
		setWidgets();
		
	}
	
	public void setWidgets(){
		Log.d(this.getLocalClassName(),"setWidgets");
		
		Button btnOrientation = (Button) this.findViewById(R.id.btnOrientation);
		Button btnGravity = (Button) this.findViewById(R.id.btnGravity);
		
		btnOrientation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(this.getClass().toString(),"onClick");
				
				LinearLayout layout = (LinearLayout) v.getParent();
				
				if (layout.getOrientation()==LinearLayout.VERTICAL){
					layout.setOrientation(LinearLayout.HORIZONTAL);
				}
				else{
					layout.setOrientation(LinearLayout.VERTICAL);
				}
				
				
				/* ESTO CAMBIA LA ORIENTACION DE LA PANTALLA, PERO NO DEL LAYOUT
				int orientation = getRequestedOrientation();
				Activity.this.getLayoutInflater().getContext().set
				if ( orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ){
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}
				else{
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				*/
			}
		});
		
		btnGravity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(this.getClass().toString(),"onClick");

				LinearLayout layout = (LinearLayout) v.getParent();
				
				ViewGroup.LayoutParams parent_layout_params = (ViewGroup.LayoutParams) layout.getLayoutParams();
				LinearLayout.LayoutParams layout_params = (LinearLayout.LayoutParams) parent_layout_params;
				
				Log.d(this.getClass().toString(),"Yeah");
				if (layout.getOrientation()==LinearLayout.VERTICAL){
					
					if(layout_params.gravity==Gravity.CENTER_HORIZONTAL){
						layout.setGravity(Gravity.CENTER_VERTICAL);
					}
					else{
						layout.setGravity(Gravity.CENTER_HORIZONTAL);
					}
				}
				else{
					if(layout_params.gravity==Gravity.CENTER_HORIZONTAL){
						layout.setGravity(Gravity.CENTER_VERTICAL);
					}
					else{
						layout.setGravity(Gravity.CENTER_HORIZONTAL);
					}
				}
			}
		});
		
	}
	
}