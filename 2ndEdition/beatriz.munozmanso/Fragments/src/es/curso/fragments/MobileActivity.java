package es.curso.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MobileActivity extends FragmentActivity implements IListFragment {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getSupportFragmentManager().findFragmentById(android.R.id.content)==null){
			MyFragmentList list = new MyFragmentList();
			getSupportFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
		}
	}

	@Override
	public void itemClick(Integer imageResource) {
		Intent i = new Intent(getApplicationContext(), MobileActivityContent.class);
		i.putExtra("IMG", imageResource);
		
	}

}
