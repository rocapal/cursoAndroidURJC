package com.example.servicegps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class ServiceGps {
	
	public static ILocation mListener;
	
	public void onCreate() {
		super.onCreate();
		
		initService();
	}
	
	public static void regListener (ILocation listener){
		mListener = listener;
	}
	
	public void initService() {
		 // declaramos LocationManager
        LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        // declaramos un listener
        MyLocationListener mLocationListener = new MyLocationListener();

        // configuramos el locationmanager y le asignamos el listener
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 15, mLocationListener);
	}
	
	public class MyLocationListener implements LocationListener{
		 @Override
	        public void onLocationChanged(Location location) {
	            // TODO Auto-generated method stub
	            Log.d("GPS", String.valueOf(location.getLatitude()) + " " + String.valueOf(location.getLongitude()));
	            if(mListener!=null){
	                mListener.setLocation(location);
	            }
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
