package com.example.appfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MobileActivityImage extends FragmentActivity{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
		int resImagen = i.getIntExtra("IMAGE", 0);
		if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
			MyImageFragments img = new MyImageFragments();
			img.setImage(resImagen);
			getSupportFragmentManager().beginTransaction().add(android.R.id.content, img).commit();
		}
	}
}
