package es.curso.bmuma.advmon;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	AdView adView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		adView = (AdView) findViewById(R.id.adView);
		
		Button show = (Button) findViewById(R.id.show);
		show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		Button hide = (Button) findViewById(R.id.hide);
		show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		getAds();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private void getAds()
	{
		AdRequest request = new AdRequest();
		//request.addTestDevice(AdRequest.TEST_EMULATOR);
		adView.loadAd(request);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	

}
