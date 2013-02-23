package com.example.maps;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends MapActivity {
	
	LocationManager mLocationManager;
	LocationListener mLocationListener;
	MapController mapControl;
	Location mloc;
	MapView mapview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Task task = new Task();
		task.execute(null,null,null);
		
		mapview = (MapView) findViewById(R.id.myMapView);
		
		mapview.setBuiltInZoomControls(true);
		mapview.setClickable(true);
		
		mapControl = mapview.getController();
		
		configGPS();
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

	private void configGPS(){
		mLocationManager = (LocationManager)
		getSystemService(Context.LOCATION_SERVICE);
		mLocationListener = new MyLocationListener();
		mLocationManager.requestLocationUpdates(
	    LocationManager.GPS_PROVIDER,5000, 15,mLocationListener);
	}
	
	private class MyLocationListener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			mloc=location;
			Log.d("Location:", String.valueOf(location.getLatitude()) +
					" " + String.valueOf(location.getLongitude()));
			refreshMap();
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
	
	private void refreshMap(){
		
		if (mloc == null){			
			Toast.makeText(getBaseContext(), "location not avalaible", 
					       Toast.LENGTH_SHORT).show();
			return;
		}
		GeoPoint geoPoint = new GeoPoint ((int)(mloc.getLatitude()*1000000),
				                          (int)(mloc.getLongitude()*1000000));
		mapControl.setZoom(8);
		mapControl.animateTo(geoPoint);
		
		MapOverlay myMapOver = new MapOverlay();
		myMapOver.setDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		myMapOver.setGeoPoint(geoPoint);
		myMapOver.setText("usted esta aqui!");
		
		final List<Overlay> overlays = mapview.getOverlays();
		overlays.clear();
		
		overlays.add(myMapOver);
	}

	private class Task extends AsyncTask<Void,Void,Void>{
		
		ProgressDialog mPd = null;

		protected void onPreExecute() {
			super .onPreExecute();
			
			mPd = ProgressDialog.show(MainActivity.this,
			                          getString(R.string.title_progress_dialog),
                                      getString(R.string.text_progress_dialog));
			
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			return null;
		}
		
		protected void onPostExecute(Void result) {
			super .onPostExecute(result);
			mPd.dismiss();
		}
		
	}
	
}
