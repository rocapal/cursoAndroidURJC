package com.example.fragments.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MobileImageActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		int myIdImage = i.getIntExtra(MobileListActivity.IMAGE_ID, 0);
		// Create the list fragment and add it as our sole content.
		if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
			ContentFragment content = new ContentFragment();
			content.setImageId(myIdImage);
			getSupportFragmentManager().beginTransaction().add(android.R.id.content, content).commit();
		}

	}

}
