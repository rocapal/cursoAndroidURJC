package com.example.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MobileActivityFragment extends FragmentActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		Intent i = getIntent();
		
		int element = i.getIntExtra("IMAGE", 0);
		
		if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
			ImageFragment cf = new ImageFragment();
			cf.setImage(element);
			getSupportFragmentManager().beginTransaction().add(android.R.id.content, cf).commit();
		}
	}
	
}
