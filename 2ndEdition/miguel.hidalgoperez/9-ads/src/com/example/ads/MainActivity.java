package com.example.ads;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class MainActivity extends Activity {
	
	AdView adView = (AdView) this.findViewById(R.id.ad);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAds();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void getAds(){
    	AdRequest request = new AdRequest();
    	request.addTestDevice(AdRequest.TEST_EMULATOR);
    	adView.loadAd(request);
    }
    
}
