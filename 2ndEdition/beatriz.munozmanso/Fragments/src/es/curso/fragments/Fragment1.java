package es.curso.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Fragment1 extends Fragment{

	
	private IFragment1 listener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof IFragment1)
			listener = (IFragment1) activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View vista = inflater.inflate(R.layout.fragment1, container, false);
		
		Button btOK = (Button) vista.findViewById(R.id.ok);
		Button btCancel = (Button) vista.findViewById(R.id.cancel);
		Button btTest = (Button) vista.findViewById(R.id.text);
		final EditText edT = (EditText) vista.findViewById(R.id.mytext);
		
		btOK.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				listener.pushOK(edT.getText().toString());
				
			}

		});
		
		btCancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				listener.pushCancel(edT.getText().toString());
				
			}
		});
		
		btTest.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				listener.pushTest();
				
			}
		});
		
		return vista;
	}
	
}
