package es.curso.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MobileActivityContent extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		int image = getIntent().getExtras().getInt("IMG",0);
		
		if(getSupportFragmentManager().findFragmentById(android.R.id.content)==null){
			ContentFragment content = new ContentFragment();
			content.setImage (image);
			getSupportFragmentManager().beginTransaction().add(android.R.id.content, content).commit();
		}
	}

}
