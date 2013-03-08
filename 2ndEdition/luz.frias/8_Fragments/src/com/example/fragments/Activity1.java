package com.example.fragments;

import com.example.fragments.FormFragment.IFormListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Activity1 extends FragmentActivity implements IFormListener {
	
	private static final String FRAGMENT_TAG = "editor";
	private boolean fragmentVisible = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
		
		final Button btFragment = (Button) findViewById(R.id.btFragment);
		
		btFragment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (fragmentVisible) {
					fragmentVisible = false;
					hideFragment();
					btFragment.setText(getString(R.string.btShowFragment));
				}
				else {
					fragmentVisible = true;
					showFragment();
					btFragment.setText(getString(R.string.btHideFragment));
				}
			}
			
		});
	}
	
	private void showFragment () {
		FragmentManager fm = getSupportFragmentManager();
		Fragment editor = fm.findFragmentByTag(FRAGMENT_TAG);
		if (editor == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.container, new FormFragment(), FRAGMENT_TAG);
			ft.commit();
		}
	}
	
	private void hideFragment () {
		FragmentManager fm = getSupportFragmentManager();
		Fragment editor = fm.findFragmentByTag(FRAGMENT_TAG);
		if (editor != null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.remove(editor);
			ft.commit();
		}
	}

	@Override
	public void pushOK(String text) {
		Toast.makeText(this, "OK: " + text, Toast.LENGTH_LONG).show();
	}

	@Override
	public void pushCancel(String text) {
		Toast.makeText(this, "Cancel: " + text, Toast.LENGTH_LONG).show();
	}

	@Override
	public void pushTest() {
		Toast.makeText(this, "Test", Toast.LENGTH_LONG).show();
	}
}
