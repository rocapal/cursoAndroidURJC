package com.example.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.fragments.list.MobileListActivity;
import com.example.fragments.list.TabletActivity;
import com.example.fragments.simple.FragmentSimpleActivity;
import com.example.fragments.withoutfragments.ListNoFragmentsActivity;

public class MainActivity extends Activity {

	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		Button button;
		button = (Button)findViewById(R.id.button_fragment_simple);
		button.setOnClickListener(createListenerSimple());
		button = (Button)findViewById(R.id.button_no_fragments);
		button.setOnClickListener(createListenerNoFragments());		
		button = (Button)findViewById(R.id.button_fragment_mobile);
		button.setOnClickListener(createListenerMobil());
		button = (Button)findViewById(R.id.button_fragment_tablet);
		button.setOnClickListener(createListenerTablet());
	}
	
	
	private View.OnClickListener createListenerNoFragments() {
		return new OnClickListener() {			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(context, ListNoFragmentsActivity.class));
			}
		};
	}
	
	private View.OnClickListener createListenerSimple() {
		return new OnClickListener() {			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(context, FragmentSimpleActivity.class));
			}
		};
	}
	
	private View.OnClickListener createListenerMobil() {
		return new OnClickListener() {			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(context, MobileListActivity.class));
			}
		};
	}
	
	private View.OnClickListener createListenerTablet() {
		return new OnClickListener() {			
			@Override
			public void onClick(View v) {
				startActivity(new Intent (context, TabletActivity.class));
			}
		};
	}
	
}
