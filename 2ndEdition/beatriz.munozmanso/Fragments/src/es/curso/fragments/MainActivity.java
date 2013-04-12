package es.curso.fragments;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements IFragment1{

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button bt = (Button) findViewById(R.id.frag1);
		Button bt2 = (Button) findViewById(R.id.frag2);
		Button bt3 = (Button) findViewById(R.id.frag3);
		
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				 
				showFragment();
			}

		});
		
		bt2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				hideFragment();
				
			}
		});
		
		bt3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent browseIntent = new Intent(getApplicationContext(), MobileActivity.class);
				
			}
		});
		
	}
	
	private void showFragment() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment editor = fm.findFragmentByTag("editor");
		if (null == editor){
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.container, new Fragment1(), "editor");
			ft.commit();
		}
	}
	
	private void hideFragment() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment editor = fm.findFragmentByTag("editor");
		if(null != editor){
			FragmentTransaction ft = fm.beginTransaction();
			ft.remove(editor);
			ft.commit();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void pushOK(String text) {
		Toast.makeText(this, text,Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void pushCancel(String text) {
		Toast.makeText(this, text,Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void pushTest() {
		Toast.makeText(this, "TEST",Toast.LENGTH_SHORT).show();
		
	}

}
