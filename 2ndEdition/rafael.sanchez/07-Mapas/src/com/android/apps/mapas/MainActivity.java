package com.android.apps.mapas;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {
	
	private MapView mapView = null;
	private MapController mapControl = null;
	private LocationManager mLocationManager = null;
	private LocationListener mLocationListener = null;
	private Location mLoc = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mapView = (MapView) findViewById (R.id.myMap);
		
		mapView.setBuiltInZoomControls(true);
		mapView.setClickable(true);
		
		mapControl = mapView.getController();
		
		pongamosGPS_usandoListeners(); // OJO a poner esto antes del mapView.getController()
		
	
	}
	
	private void pongamosGPS_usandoListeners ()
	{
		
		mLocationManager = (LocationManager)
		getSystemService(Context.LOCATION_SERVICE);
		
		mLocationListener = new MyLocationListener();
		
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
										5000, 15, mLocationListener);

	}
	
	private void refrescarMapa() {
		
		GeoPoint geoPoint = new GeoPoint((int) (mLoc.getLatitude() * 1000000),
										 (int) (mLoc.getLongitude() * 1000000));
		mapControl.setZoom(18);
		mapControl.animateTo(geoPoint);
	}

		
	private class MyLocationListener implements LocationListener
	{
	
		@Override
		public void onLocationChanged(Location location) {
			
			mLoc = location;
			Log.d("Location:", String.valueOf(mLoc.getLatitude()) +
			" " + String.valueOf(mLoc.getLongitude()));
			refrescarMapa();
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
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
