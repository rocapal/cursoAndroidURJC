package com.android.myfragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FormFragments extends Fragment {
	
	public final static int OK = 0;
	public final static int CANCEL = 1;
	public formListener listener;
	
	public interface formListener {
		public void pushOk(String text);
		public void pushCancel(String text);
		
		public void pushTest();
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (activity instanceof formListener)
				listener = (formListener) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View vista = inflater.inflate(R.layout.fragment_a, container, false);
		
		Button bt1 = (Button) vista.findViewById(R.id.bta);		
		Button bt2 = (Button) vista.findViewById(R.id.btb);		
		Button bt3 = (Button) vista.findViewById(R.id.btc);
		final EditText et = (EditText) vista.findViewById(R.id.et);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.pushOk(et.getText().toString());
				
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.pushCancel(et.getText().toString());
				
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.pushTest();
				
			}
		});
		return vista;
		
	}
}
