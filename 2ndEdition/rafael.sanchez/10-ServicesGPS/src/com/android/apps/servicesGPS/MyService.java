package com.android.apps.servicesGPS;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	
	private static ILocation mListener = null; 
	
	private LocationManager mLocationManager = null;
	private LocationListener mLocationListener = null;
	private Location mLoc = null;
	
	int mCounter = 0;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("SERVICE", "onCreate(). Iniciando servicio");
		initService();
	}
	
	public void initService() {
		
		mLocationManager = (LocationManager)
				getSystemService(Context.LOCATION_SERVICE);
				
		mLocationListener = new MyLocationListener();
				
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
												5000, 15, mLocationListener);
		
	}
	
	public static void regListener (ILocation listener) {
		mListener = listener;
	
		
	}
	
	private class MyLocationListener implements LocationListener
	{
	
		@Override
		public void onLocationChanged(Location location) {
			
			mLoc = location;
			Log.d("Location:", String.valueOf(mLoc.getLatitude()) +
			" " + String.valueOf(mLoc.getLongitude()));
			if (mListener != null)
				mListener.updateLocation(mLoc);
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
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
