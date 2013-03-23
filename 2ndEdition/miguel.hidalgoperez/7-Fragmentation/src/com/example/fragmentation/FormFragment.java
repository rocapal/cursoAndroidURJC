package com.example.fragmentation;

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
	private formListener listener;
	
	public interface formListener {
		 public void pushOk (String text);
		 public void pushCancel (String text);
		 public void pushTest ();
	}
	
	public void onAttach (Activity activity) {
		super.onAttach(activity);
		if (activity instanceof formListener){
			listener = (formListener) activity;
		}
	}
	
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View vista = inflater.inflate(R.layout.frag, container, false);
		
		final EditText et = (EditText) vista.findViewById(R.id.edit);
		
		Button btn1 = (Button) vista.findViewById(R.id.buttonok);
		btn1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				listener.pushOk(et.getText().toString());
			}
		});
		
		Button btn2 = (Button) vista.findViewById(R.id.buttoncancel);
		btn2.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				listener.pushOk(et.getText().toString());
			}
		});
		
		Button btn3 = (Button) vista.findViewById(R.id.buttontest);
		btn3.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				listener.pushOk(et.getText().toString());
			}
		});
		
		return vista;
	}
	
}