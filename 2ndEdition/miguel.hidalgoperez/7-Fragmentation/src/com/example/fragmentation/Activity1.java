package com.example.fragmentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragmentation.FormFragment.formListener;

public class Activity1 extends FragmentActivity implements formListener{

	private boolean hide = true; 
	
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act1);
		
		Button btn = (Button) this.findViewById(R.id.buttonfrag);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (hide) {
					showFragment();
					hide=false;
				}else{
					hideFragment();
				}
			}
		});
	}
	
	private void showFragment(){
		FragmentManager fm = getSupportFragmentManager();
		Fragment editor = fm.findFragmentByTag("editor");
		if (null == editor) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.add (R.id.container, new FormFragment(), "editor");
			ft.commit();
		}
	}
	
	private void hideFragment(){
		FragmentManager fm = getSupportFragmentManager();
		Fragment editor = fm.findFragmentByTag("editor");
		FragmentTransaction ft = fm.beginTransaction();
		ft.remove(editor);
		ft.commit();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return false;
	}

	@Override
	public void pushOk(String text) {
		// TODO Auto-generated method stub
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void pushCancel(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushTest() {
		// TODO Auto-generated method stub
		
	}

	
}
