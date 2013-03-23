package com.example.fragmentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.fragmentation.MyListFragment.IListFragment;

public class Activity2 extends FragmentActivity implements IListFragment{
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		if (getSupportFragmentManager().findFragmentById(android.R.id.content)==null){
			MyListFragment list = new MyListFragment();
			getSupportFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
		}
	}

	@Override
	public void itemClick(Integer imageResource) {
		// TODO Auto-generated method stub
		Intent i = new Intent (this, Activity2.class);
		i.putExtra("IMAGE", imageResource);
	}

}
