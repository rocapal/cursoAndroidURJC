package com.android.apps.mapas;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity {
	
	private MapView mapView = null;
	private MapController mapControl = null;
	private LocationManager mLocationManager = null;
	private LocationListener mLocationListener = null;
	private Location mLoc = null;
	
	private MapOverlay myMapOverlay = null;
	private ProgressDialog mPd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mapView = (MapView) findViewById (R.id.myMap);
		
		mapView.setBuiltInZoomControls(true);
		mapView.setClickable(true);
		
		mapControl = mapView.getController();
		
		//mPd = ProgressDialog.show(this, "Titulo", "Waiting for GPS signal");
		
		MyAsyncTask task =  new MyAsyncTask();
		task.execute(null, null, null);
		
		
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
		
		
		// Poner un if (mLoc == null)
		 
		GeoPoint geoPoint = new GeoPoint((int) (mLoc.getLatitude() * 1000000),
										 (int) (mLoc.getLongitude() * 1000000));
			
		mapControl.setZoom(18);
		mapControl.animateTo(geoPoint);
		
		myMapOverlay = new MapOverlay();
		myMapOverlay.setTexto("Hola, mundo");
		
		
		//mPd.dismiss();
		
		myMapOverlay.setDrawable(getResources().getDrawable(R.drawable.drawingpin));
		myMapOverlay.setGeoPoint(geoPoint);

		final List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();

		overlays.add(myMapOverlay);
		
		
		
	}

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		mPd = ProgressDialog.show(MainActivity.this, "Titulo", "Waiting for GPS signal");
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		mPd.dismiss();
	}
	
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
