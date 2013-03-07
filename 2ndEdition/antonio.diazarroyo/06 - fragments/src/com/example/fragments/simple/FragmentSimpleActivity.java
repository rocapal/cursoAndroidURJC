package com.example.fragments.simple;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragments.R;

public class FragmentSimpleActivity extends FragmentActivity implements IFormListener {

	private Context context;

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_simple);
		context = this;
		Button button = (Button) findViewById(R.id.button_show);
		button.setOnClickListener(createListenerShow());
		button = (Button) findViewById(R.id.button_hide);
		button.setOnClickListener(createListenerHide());
	}

	private OnClickListener createListenerShow() {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
				Fragment fragment = fragmentManager.findFragmentByTag("editor");
				if (fragment == null) {
					FragmentTransaction tx;
					tx = fragmentManager.beginTransaction();
					tx.add(R.id.container, new FormFragment(), "editor");
					tx.commit();
				}
			}
		};
	}

	private OnClickListener createListenerHide() {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getSupportFragmentManager();
				Fragment fragment = fragmentManager.findFragmentByTag("editor");
				if (fragment != null) {
					FragmentTransaction tx = fragmentManager.beginTransaction();
					tx.remove(fragment);
					tx.commit();
				}
			}
		};
	}

	@Override
	public void pushOk(String text) {
		Toast.makeText(context, "OK " + text, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void pushCancel(String text) {
		Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void pushTest(String text) {
		Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
	}
}
