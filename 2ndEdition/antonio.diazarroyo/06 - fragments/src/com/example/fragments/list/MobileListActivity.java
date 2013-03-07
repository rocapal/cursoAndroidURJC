package com.example.fragments.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.fragments.R;

public class MobileListActivity extends FragmentActivity implements IListFragment {

	public static final String IMAGE_ID = "IMAGE_ID";

	protected void onCreateBis(Bundle savedInstance) {
	
	}
	
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		FragmentManager fragmenteManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmenteManager.beginTransaction();
		transaction.add(R.id.container, new ImageListFragment());
		transaction.commit();
		setContentView(R.layout.mobile_layout);
	}

	@Override
	public void clickOnImage(Integer i) {
		Intent intent = new Intent(this, MobileImageActivity.class);
		intent.putExtra(IMAGE_ID, i);
		startActivity(intent);
	}
}
