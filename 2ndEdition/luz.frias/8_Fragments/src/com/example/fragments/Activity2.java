package com.example.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Activity2 extends FragmentActivity implements IListFragment {
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
			MyListFragment list = new MyListFragment();
			getSupportFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
		}
	}

	@Override
	public void onClickItem(int imageId) {
		Intent i = new Intent(this, MobileActivityFragment.class);
		i.putExtra("IMAGE", imageId);
		startActivity(i);
	}

}
