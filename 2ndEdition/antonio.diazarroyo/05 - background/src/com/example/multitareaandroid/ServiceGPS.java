package com.example.multitareaandroid;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

public class ServiceGPS extends Service {

	private static ILocation iLocation;
	
	@Override
	public void onCreate() {
		Log.d(MainActivity.TAG, MainActivity.TAG + " starting ServiceGPS");
		super.onCreate();
		initService();
	}
	
	
	private void initService() {		
		MainActivity.MY_TEXT.setText("starting service...");
		LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		MyLocationListener mLocationListener = new MyLocationListener(iLocation);
		mLocationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 5000, 15, mLocationListener);
	}

	@Override
	public void onDestroy() {
		Log.d(MainActivity.TAG, MainActivity.TAG + " stoping service");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}


	public static ILocation getiLocation() {
		return iLocation;
	}


	public static void setiLocation(ILocation iLocation) {
		ServiceGPS.iLocation = iLocation;
	}
}
