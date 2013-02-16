package com.example.mislistas.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.mislistas.EasyList;

public class ButtonEasyListeners implements OnClickListener {

		private Context context;
	
		public ButtonEasyListeners(Context myContext) {
			context = myContext;
		}
		
		public void onClick(View v) {
			Intent newActivityIntent = new Intent (context, EasyList.class);
			context.startActivity(newActivityIntent);
		}
}
