package es.curso.serviceproyect;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class ServiceGPS extends Service{

	private static IGpsLocation mListener = null;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		//generamos un locationLitsener
		//No es necesario ningún bucle, registramos el litsener
		super.onCreate();
		
		initService();
		
		
	}

	private void initService() {
		
		LocationManager mLocationManager = (LocationManager) 
				getSystemService(Context.LOCATION_SERVICE);
		
		MyLocationListener mLocationListener = new MyLocationListener();
		
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
				15, mLocationListener);
		
		
	}
	
	public static void regListener(IGpsLocation listener){
		
		mListener = listener;
		
	}

	
	public class MyLocationListener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			if(mListener != null)
				mListener.setLocation(location);
			
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
