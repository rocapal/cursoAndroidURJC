package com.example.testadmob;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class ActivityMain extends Activity implements AdListener {

	private AdView adView;
	private String MY_AD_UNIT_ID = "";
	public static final long TIME_SHOWING_AD = 5000;
	private ActivityMain context;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		adView = new AdView(this, AdSize.BANNER, MY_AD_UNIT_ID);		
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.ad_layout);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.BOTTOM;
		linearLayout.addView(adView, params);
		refreshAdd();		
		Button button;
		button = (Button) findViewById(R.id.show);
		button.setOnClickListener(showListener());
		button = (Button) findViewById(R.id.hide);
		button.setOnClickListener(hideListener());
		button = (Button) findViewById(R.id.refresh);
		button.setOnClickListener(refreshListener());
	}

	private void refreshAdd() {
		AdRequest adRequest = new AdRequest();
		adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
		adView.setAdListener(this);
		adView.loadAd(adRequest);
	}

	private void hideAdd() {
		if (adView!=null && adView.isShown()) {
			LinearLayout adLayout = (LinearLayout) findViewById(R.id.ad_layout);
			adLayout.removeView(adView);
		}
	}
	
	private void showAd() {
		if (adView!=null && !adView.isShown()) {
			LinearLayout adLayout = (LinearLayout) findViewById(R.id.ad_layout);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.gravity = Gravity.BOTTOM;
			adLayout.addView(adView, params);
		}
	}
	
	private OnClickListener hideListener() {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				hideAdd();
			}
		};
	}

	private OnClickListener showListener() {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				showAd();
			}
		};
	}
	
	private OnClickListener refreshListener() {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				refreshAdd();
			}
		};
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		if (adView!=null) {
			adView.destroy();
		}
		super.onDestroy();
	}

	@Override
	public void onDismissScreen(Ad arg0) { }

	@Override
	public void onFailedToReceiveAd(Ad arg0, ErrorCode errorCode) {
		Toast.makeText(context, "onFailedReceiveAd" + errorCode, Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onLeaveApplication(Ad arg0) { }

	@Override
	public void onPresentScreen(Ad arg0) { }

	@Override
	public void onReceiveAd(Ad ad) {
		context.showAd();
		//Start a thread to wait 10 seconds before to hide the ad.
		//Declaracion de la clase Handler.
		Handler myHandler = new Handler (new Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				context.hideAdd();
				return true;
			}
		});
		MyThreadHandler mThreadHandler = new MyThreadHandler();
		mThreadHandler.setHandler(myHandler);
		mThreadHandler.start();
	}
}
