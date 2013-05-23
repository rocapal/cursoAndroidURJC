package com.example.appfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MobileGlobalActivity extends Fragment{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		FragmentManager ff = getFragmentManager();
		Fragment editor = ff.findFragmentByTag("editor");
		if (null == editor){
			FragmentTransaction ft = ff.beginTransaction();
			ft.add(R.id.container1, new MyListFragments(), "editor");
			ft.commit();
		}
		FragmentManager ff2 = getFragmentManager();
		Fragment editor2 = ff2.findFragmentByTag("editor2");
		if (null == editor2){
			FragmentTransaction ft2 = ff2.beginTransaction();
			ft2.add(R.id.container2, new MyImageFragments(), "editor2");
			ft2.commit();
		}
	}
}
