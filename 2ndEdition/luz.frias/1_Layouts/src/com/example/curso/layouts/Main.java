package com.example.curso.layouts;

import com.example.curso.layouts.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Extracción botones
        Button btHorizontal = (Button) this.findViewById(R.id.btHorizontal);
        Button btVertical = (Button) this.findViewById(R.id.btVertical);
        Button btTable = (Button) this.findViewById(R.id.btTable);
        Button btFicha = (Button) this.findViewById(R.id.btFicha);
        
        // Asignación layout a botones
        btHorizontal.setOnClickListener(createListenerForLayout(R.layout.horizontal));
        btVertical.setOnClickListener(createListenerForLayout(R.layout.vertical));
        btTable.setOnClickListener(createListenerForLayout(R.layout.table));
        btFicha.setOnClickListener(createListenerForLayout(R.layout.profile));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private OnClickListener createListenerForLayout(final int layoutId) {
    	
    	return new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), Layout.class);
				intent.putExtra("LAYOUT", layoutId);
				startActivity(intent);
			}
		};
    }
    
}
