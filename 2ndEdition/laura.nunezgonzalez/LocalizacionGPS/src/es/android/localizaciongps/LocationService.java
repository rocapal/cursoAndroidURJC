     package es.android.localizaciongps;

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

public class LocationService extends Service {

	public Handler mHandler;
	private MyLocationListener mLocationListener;
	private Location mLoc;
	private LocationManager mLocationManager;

	public LocationService(){
		super();
		Log.d("Servicio", "Servicio iniciado parapapam");
		this.mHandler = MainActivity.mHandler;
		setLocationListener();
	}
	
	private class DoSomething extends Handler {
		
		public void handleMessage(Message msg) {
			setLocationListener();
		}
	}
	
	private void setLocationListener() {
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
	    mLocationListener = new MyLocationListener();
	    
	    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
			5000,15,mLocationListener);		
		Message msg = new Message();
		msg.arg1 = (int) mLoc.getLatitude();
		msg.arg2 = (int) mLoc.getLongitude();
		Log.d("GPS", String.valueOf(mLoc.getLatitude()) + "," + String.valueOf(mLoc.getLongitude()));
		this.mHandler.sendMessage(msg);
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class MyLocationListener implements LocationListener
	{

		@Override
		public void onLocationChanged(Location location) {
			mLoc = location;
			Log.d("Traza GPS", "Posición: " + location.getLatitude()+ "," + location.getLongitude());
			Location dest = new Location(location);
			dest.setLatitude(40.33483);
			dest.setLongitude(-3.873968);
			double distancia = location.distanceTo(dest);
			Log.d("Traza GPS", "Distancia: " + String.valueOf(distancia));
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
