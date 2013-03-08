package com.android.apps.fragmentlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.android.apps.fragmentlist.MyListFragment.IListFragment;

public class Main extends FragmentActivity implements IListFragment {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Create the list fragment and add it as our sole content.
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
         MyListFragment list = new MyListFragment();
         getSupportFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
        }	
	}

	@Override
	public void itemClick(Integer imageResource)
	{
	
		Intent i = new Intent (this, ContentActivity.class);
		i.putExtra("IMAGE", imageResource);		
		
		startActivity(i);
		
	
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
