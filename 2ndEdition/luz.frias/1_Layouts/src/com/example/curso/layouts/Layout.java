package com.example.curso.layouts;

import android.app.Activity;
import android.os.Bundle;

public class Layout extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(this.getIntent().getIntExtra("LAYOUT", -1));
		
	}
}
