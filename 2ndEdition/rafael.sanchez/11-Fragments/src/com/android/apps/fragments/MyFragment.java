package com.android.apps.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MyFragment extends Fragment {
	
	private myListener listener;
	
	public interface myListener {
		public void pushOk (String text);
		public void pushCancel(String text);
		public void pushTest();
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (activity instanceof myListener) {
			listener = (myListener) activity;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View vista = inflater.inflate(R.layout.fragment, container, false);
		
		// Press Ok
		((Button) vista.findViewById(R.id.btOk)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.pushOk (((EditText) getActivity().
						findViewById(R.id.etUserInput)).getText().toString());
			}
		});
	
		// Press Cancel
		((Button) vista.findViewById(R.id.btCancel)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.pushCancel (((EditText) getActivity().
						findViewById(R.id.etUserInput)).getText().toString());
			}
		});

		// Press test
		((Button) vista.findViewById(R.id.btTest)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.pushTest();
			}
		});
		
		return vista;
	}	

	
}
