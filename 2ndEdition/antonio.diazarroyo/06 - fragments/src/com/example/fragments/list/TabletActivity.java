package com.example.fragments.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.fragments.R;

public class TabletActivity extends FragmentActivity implements IListFragment {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FragmentManager fragmenteManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmenteManager.beginTransaction();
		transaction.add(R.id.container_list, new ImageListFragment());
		transaction.commit();
		setContentView(R.layout.tablet_layout);
	}

	@Override
	public void clickOnImage(Integer i) {
		/* remove previous image. */
		Fragment fragment = getSupportFragmentManager().findFragmentByTag("image");
		if (fragment != null) {
			getSupportFragmentManager().beginTransaction().remove(fragment).commit();
		}
		ContentFragment content = new ContentFragment();
		content.setImageId(i);
		getSupportFragmentManager().beginTransaction().add(R.id.container_image, content, "image").commit();
	}

}
