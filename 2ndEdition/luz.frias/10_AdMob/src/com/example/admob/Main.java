package com.example.admob;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;

public class Main extends Activity {
	
	private AdView adView;
	private LinearLayout lyBanner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button btRefresh = (Button) findViewById(R.id.btRefresh);
		Button btHide = (Button) findViewById(R.id.btHide);
		lyBanner = (LinearLayout) findViewById(R.id.llBanner);
		adView = (AdView) findViewById(R.id.adView);
		
		getAds();
	}
	
	private void getAds() {
		AdRequest request = new AdRequest();
//		request.addTestDevice(AdRequest.TEST_EMULATOR);
		adView.loadAd(request);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
