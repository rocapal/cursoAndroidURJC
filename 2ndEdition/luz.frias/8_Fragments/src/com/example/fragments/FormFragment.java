package com.example.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FormFragment extends Fragment {
	
	public final static int OK = 0;
	public final static int CANCEL = 1;
	private IFormListener listener;
	
	public interface IFormListener {
		public void pushOK (String text);
		public void pushCancel (String text);
		public void pushTest ();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (activity instanceof IFormListener) {
			listener = (IFormListener) activity;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View vista = inflater.inflate(R.layout.fragment, container, false);
		
		final EditText editText = (EditText) vista.findViewById(R.id.editFragment);
		final Button btOK = (Button) vista.findViewById(R.id.btFragmentOK);
		final Button btCancel = (Button) vista.findViewById(R.id.btFragmentCancel);
		final Button btTest = (Button) vista.findViewById(R.id.btFragmentTest);
		
		//Boton OK
		btOK.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (listener != null) {
					listener.pushOK(editText.getText().toString());
				}
			}
		});
		
		//Boton Cancel
		btCancel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (listener != null) {
					listener.pushCancel(editText.getText().toString());
				}
			}
		});

		//Boton Test
		btTest.setOnClickListener(new OnClickListener() {
		
			public void onClick(View v) {
				if (listener != null) {
					listener.pushTest();
				}
			}
		});

		return vista;
	}
}
