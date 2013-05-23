package com.android.myfragments;

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

import com.android.myfragments.FormFragments.formListener;

public class MainActivity extends FragmentActivity implements formListener{

	private boolean mMostrar;
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMostrar=false;
		final Button bt = (Button) findViewById(R.id.bt1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mMostrar)
					hideFragment();
				else
					showFragment();
			}
		});
	}
	
	public void showFragment(){
		FragmentManager ff = getSupportFragmentManager();
		Fragment editor = ff.findFragmentByTag("editor");
		if (null == editor){
			FragmentTransaction ft = ff.beginTransaction();
			ft.add(R.id.container, new FormFragments(), "editor");
			ft.commit();
		}
		mMostrar = true;
	}

	public void hideFragment(){
		FragmentManager ff = getSupportFragmentManager();
		Fragment editor = ff.findFragmentByTag("editor");
		if (!(null == editor)){
			FragmentTransaction ft = ff.beginTransaction();	
			ft.remove(editor);
			ft.commit();
		}
		mMostrar = false;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void pushOk(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	@Override
	public void pushCancel(String text) {
		Toast.makeText(this, "Cancelado " + text, Toast.LENGTH_LONG).show();		
	}

	@Override
	public void pushTest() {
		Toast.makeText(this, "testeado", Toast.LENGTH_LONG).show();
		
	}

}
