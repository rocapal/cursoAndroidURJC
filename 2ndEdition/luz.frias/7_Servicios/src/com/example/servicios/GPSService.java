package com.example.servicios;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GPSService extends Service {
	
	private LocationManager mLocationManager;
	private GPSLocationListener mLocationListener;
	public Location mCurrentLoc;

	private void configGPS () {

		mLocationManager = (LocationManager)
		getSystemService(Context.LOCATION_SERVICE);
		mLocationListener = new GPSLocationListener();
		mLocationManager.requestLocationUpdates(
		LocationManager.GPS_PROVIDER, 5000, 15, mLocationListener);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		configGPS();
	}
	
	@Override
	public void onDestroy() {
		mLocationManager.removeUpdates(mLocationListener);
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class GPSLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			mCurrentLoc = location;
			Main.setText("Position: " + location.getLongitude() + ", " + location.getLatitude());
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

}
