package com.example.servicegps;

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final TextView tv = (TextView) this.findViewById(R.id.info);
        
        ServiceGps.regListener(new Ilocation() {
        	
        	@Override
        	public void setLocation(Location location){
        		tv.setText (String.valueOf(location.getLatitude())+ "" 
        	    + String.valueOf(location.getLongitude()));
        	}
        	
        });
        
        startService(new Intent(this, ServiceGps.class));
        
        context = this;
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
