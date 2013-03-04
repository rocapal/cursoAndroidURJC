package es.practicas.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class controlImageContent extends FragmentActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    if (getSupportFragmentManager().findFragmentById(android.R.id.content)==null) {
	    	ControlImageFragment image = new ControlImageFragment();
	    	image.setmResourceID(getIntent().getIntExtra("IMAGE", -1));
	    	
	    	
	    	getSupportFragmentManager().beginTransaction().add(android.R.id.content,image).commit();
	    }
	}

}
