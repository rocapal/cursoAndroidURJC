package com.example.mislistas.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.mislistas.ComplexList;

public class ButtonComplexListeners implements OnClickListener {

	private Context context;
	
	public ButtonComplexListeners(Context myContext) {
		context = myContext;
	}
	
	@Override
	public void onClick(View v) {
		Intent newActivityIntent = new Intent (context, ComplexList.class);
		context.startActivity(newActivityIntent);
	}

}
