package es.practicas.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ControlFragment extends Fragment{

		public final static int OK=0,CANCEL=1,TEST=2;
		public IControlListener listener;
		
		public interface IControlListener {
			public void clickOk(String text);
			public void clickCancel(String text);
			public void clickTest();
			
		}
		
		@Override
		public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
			super.onAttach(activity);
			if (activity instanceof IControlListener)
				listener = (IControlListener) activity;
		}
	
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
			
			
			View ctrl = inflater.inflate(R.layout.fragment_layout,  container,false);
		
			final EditText txt = (EditText) ctrl.findViewById(R.id.editText1);
			
			((Button) ctrl.findViewById(R.id.buttonOk)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listener.clickOk(txt.getText().toString());
					
				}
			});
			
			((Button) ctrl.findViewById(R.id.buttonCancel)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listener.clickCancel(txt.getText().toString());					
				}
			});
			
			((Button) ctrl.findViewById(R.id.buttonTest)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listener.clickTest();					
				}
			});
			
			return ctrl;
		}
}
