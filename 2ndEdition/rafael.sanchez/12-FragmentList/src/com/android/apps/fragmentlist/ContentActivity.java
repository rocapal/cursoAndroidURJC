package com.android.apps.fragmentlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ContentActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
        int resImage = i.getIntExtra("IMAGE", 0);
        
        
        // Create the list fragment and add it as our sole content.
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
         ContentFragment content = new ContentFragment();
         content.setImage (resImage);
         getSupportFragmentManager().beginTransaction().add(android.R.id.content, content).commit();
        }	
	}

}
