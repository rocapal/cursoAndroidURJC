package com.android.apps.fragments;

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

import com.android.apps.fragments.MyFragment.myListener;

public class Main extends FragmentActivity implements myListener {
	
	Button btShowFragment;
	boolean btStatus = true;  //true = "do show"; false = "do hide"

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btShowFragment = (Button) findViewById(R.id.btShowFragment);
		
		btShowFragment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (btStatus) {
					btStatus = false;
					showFragment();
					btShowFragment.setText("Hide fragment");	
		         }
				else {
					btStatus = true;
					hideFragment();
					btShowFragment.setText("Show fragment");
				}
			}
		});
	}
	
	private void showFragment() {
		 FragmentManager fm = getSupportFragmentManager();
         Fragment editor = fm.findFragmentByTag("rafa");
         if (editor == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add (R.id.container, new MyFragment(), "rafa");
            ft.commit();
         } 
		
	}
	
	private void hideFragment() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment editor = fm.findFragmentByTag("rafa");
		FragmentTransaction ft = fm.beginTransaction();
		ft.remove(editor);
		ft.commit();
	}

	@Override
	public void pushOk(String text) {
		Toast.makeText(this, text,
				Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void pushCancel(String text) {
		Toast.makeText(this, text,
				Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void pushTest() {
		Toast.makeText(this, "Test button is pressed",
				Toast.LENGTH_SHORT).show();
		
	}

}
