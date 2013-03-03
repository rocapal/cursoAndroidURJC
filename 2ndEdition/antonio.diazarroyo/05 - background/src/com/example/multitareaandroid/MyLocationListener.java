package com.example.multitareaandroid;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationListener implements LocationListener {

	private ILocation myILocation;
	
	public MyLocationListener(ILocation iLocation) {
		myILocation = iLocation;
	}

	@Override
	public void onLocationChanged(Location location) {
		String msg =MainActivity.TAG + "[" + location.getLongitude() +"]" + "[" + location.getLatitude() + "]";
		Log.d(MainActivity.TAG, msg);
		myILocation.updateLocation(location);
	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

}
