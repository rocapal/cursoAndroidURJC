package es.practicas.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import es.practicas.fragments.ControlListFragment.IListFragment;

public class ListFragments extends FragmentActivity implements IListFragment {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    if (getSupportFragmentManager().findFragmentById(android.R.id.content)==null) {
	    	ControlListFragment list = new ControlListFragment();
	    	getSupportFragmentManager().beginTransaction().add(android.R.id.content,list).commit();
	    }
	}

	@Override
	public void itemClick(Integer imageResource) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(),ControlImageFragment.class);
		intent.putExtra("IMAGE", imageResource);
		startActivity(intent);
	}

}
