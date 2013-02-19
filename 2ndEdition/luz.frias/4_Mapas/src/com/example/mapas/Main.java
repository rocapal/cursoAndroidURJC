package com.example.mapas;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Menu;

public class Main extends MapActivity {

	private MapView mapView;
	private LocationManager mLocationManager;
	private LocationListener mLocationListener;
	ProgressDialog mProg;
	private MapController mapController = null;
	private Location currentLoc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mapView = (MapView) findViewById(R.id.myMapView);
		
		mapView.setBuiltInZoomControls(true);
		mapView.setClickable(true);
		
		mapController = mapView.getController();
		
		MyAsyncTask task = new MyAsyncTask(this);
		task.execute();

		configGPS();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mLocationManager.removeUpdates(mLocationListener);
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
	
	private void configGPS () {

		mLocationManager = (LocationManager)
		getSystemService(Context.LOCATION_SERVICE);
		mLocationListener = new MyLocationListener();
		mLocationManager.requestLocationUpdates(
		LocationManager.GPS_PROVIDER, 5000, 15, mLocationListener);
	}
	
	private void updateMap () {
		
		if (currentLoc == null) {
			//ToDo mensaje error
			return;
		}

		GeoPoint geoPoint = new GeoPoint((int) (currentLoc.getLatitude() * 1E6), (int) (currentLoc.getLongitude() * 1E6));
		mapController.setZoom(18);
		mapController.setCenter(geoPoint);
		
		MapOverlay mapOverlay = new MapOverlay();
		
		mapOverlay.setDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		mapOverlay.setText(getResources().getString(R.string.marker_text));
		mapOverlay.setGeoPoint(geoPoint);
		
		final List<Overlay> overlays = mapView.getOverlays();
		overlays.clear();
		
		overlays.add(mapOverlay);
		
		mapView.setSatellite(true);
		mapView.setBuiltInZoomControls(true);
		
//		mapOverlay.draw(canvas, mapView, false);
	}
	
	public class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			currentLoc = location;
			Log.d("Main", "Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude());
			updateMap();
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
	
	public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
		
		private Activity context;
		
		public MyAsyncTask(Activity context) {
			this.context = context;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProg = ProgressDialog.show(context,
					context.getResources().getString(R.string.progress_text_title),
					context.getResources().getString(R.string.progress_text_desc));
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void arg) {
			super.onPostExecute(arg);
			if (mProg.isShowing())
				mProg.dismiss();
		}
		
	}

}
