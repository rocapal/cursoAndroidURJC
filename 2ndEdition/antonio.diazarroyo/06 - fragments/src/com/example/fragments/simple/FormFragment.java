package com.example.fragments.simple;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fragments.R;

public class FormFragment extends Fragment {

	private IFormListener formListener;
	private View vista;
	

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof IFormListener) {
			formListener = (IFormListener) activity;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		vista = inflater.inflate(R.layout.activity_fragment, container, false);
		Button button = (Button) vista.findViewById(R.id.button_ok);
		button.setOnClickListener(listenerOk());
		button = (Button) vista.findViewById(R.id.button_cancel);
		button.setOnClickListener(listenerCancel());
		button = (Button) vista.findViewById(R.id.button_test);
		button.setOnClickListener(listenerTest());
		return vista;
	}
	
	private View.OnClickListener listenerOk(){
		return new OnClickListener() {			
			@Override
			public void onClick(View v) {
				final EditText editText = (EditText) vista.findViewById(R.id.my_textview);
				formListener.pushOk(editText.getText().toString());				
			}
		};
	}
	private View.OnClickListener listenerCancel(){
		return new OnClickListener() {			
			@Override
			public void onClick(View v) {
				final EditText editText = (EditText) vista.findViewById(R.id.my_textview);
				formListener.pushCancel(editText.getText().toString());				
			}
		};
	}
	private View.OnClickListener listenerTest(){
		return new OnClickListener() {			
			@Override
			public void onClick(View v) {
				final EditText editText = (EditText) vista.findViewById(R.id.my_textview);;
				formListener.pushTest(editText.getText().toString());				
			}
		};
	}
	
}
