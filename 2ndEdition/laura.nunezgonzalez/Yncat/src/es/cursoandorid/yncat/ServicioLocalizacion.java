package es.cursoandorid.yncat;

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

public class ServicioLocalizacion extends Service{
	
	private static Handler mHandler;
	private static Location mLoc;
	private static Float mDist;
	private Bundle data;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		mHandler = MapasActivity.mHandler;
		mDist = (float) 0;
		data = new Bundle(mDist.intValue());
		configGPS();
	}
	
	public static void setHandler(Handler h){
		mHandler = h;
	}
	public static void setLocation(Location loc)
	{
		mLoc.set(loc);
	}
	
	public static void setDist(float dist){
		mDist = dist;
	}
	public static Location getLocation()
	{
		return mLoc;
	}
	
	public static float getDist(){
		return mDist;
	}
	private void configGPS ()
	{
		LocationManager mLocationManager;
		LocationListener mLocationListener;
		mLocationManager = (LocationManager)
		getSystemService(Context.LOCATION_SERVICE);
		mLocationListener = new MyLocationListener();
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
													5000, 5,mLocationListener);
	}
	
	private class MyLocationListener implements LocationListener
	{
		@Override
		public void onLocationChanged(Location location) {
			
			Log.d("Location:", String.valueOf(location.getLatitude()) +
					" " + String.valueOf(location.getLongitude()));
			float dist=0;
			Message msg = new Message();
			if(mLoc !=null)
				dist=mLoc.distanceTo(location);
			mDist = mDist + dist;
			mLoc = location;
			Log.d("Location", "Distancia: " + String.valueOf(mDist.intValue()));

			msg.arg1 =mDist.intValue();
			data.putDouble("Latitud", location.getLatitude());
			data.putDouble("Longitud", location.getLongitude());
			msg.setData(data);
			mHandler.sendMessage(msg);
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

}
