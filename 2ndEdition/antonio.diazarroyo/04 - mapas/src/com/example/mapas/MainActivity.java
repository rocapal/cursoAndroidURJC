package com.example.mapas;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {

	private MapView mapView;
	private LocationManager mLocationManager;
	private LocationListener mLocationListener;
	private ProgressDialog progressDialog;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//		progressDialog = ProgressDialog.show(this, getString(R.string.title_progress_dialog), getString(R.string.message_progress_dialog));
		//TareaAsincrona tareaAsincrona = new TareaAsincrona(this);
		//tareaAsincrona.execute(null, null, null);
		mapView = (MapView)findViewById(R.id.myMapView);
		mapView.setBuiltInZoomControls(true);
		mapView.setClickable(true);
		mapView.getController();
		this.configGPS();
		
	}
	
	private void configGPS () {
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mLocationListener = new MyLocationListener(this, mapView, progressDialog);
		mLocationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 5000, 15, mLocationListener);
		
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	

	
	
}
