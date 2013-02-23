package com.example.mapas;

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
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity {
	private MapView mapview = null;
	private MapController mapControl = null; 
	private Location loc;
	ProgressDialog dialog ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//dialog= ProgressDialog.show(this, getString(R.string.titulo_progress_dialog),getString(R.string.mensaje_progress_dialog));
		MyAsyncTask task=new MyAsyncTask();
		task.execute(null,null,null);
		mapview = (MapView) findViewById(R.id.myMapView);
		mapview.setBuiltInZoomControls(true);
		mapview.setClickable(true);
		
		mapControl = mapview.getController();
		configGps();
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public class MyAsyncTask extends AsyncTask<Void ,Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(5000);
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			dialog= ProgressDialog.show(MainActivity.this, getString(R.string.titulo_progress_dialog),getString(R.string.mensaje_progress_dialog));
			super.onPreExecute();
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			
			super.onPostExecute(result);
			if (dialog.isShowing())
				dialog.dismiss();
		}
	}
	
	private void refreshMap()
    {
		//if(dialog.isShowing())
			//dialog.dismiss(); 
		
		if (loc==null){
			
			Toast.makeText(getBaseContext(),
                    "Location not available!",
                    Toast.LENGTH_LONG).show();
    
     return;
		}
		
		GeoPoint geoPoint = new GeoPoint( (int) (loc.getLatitude()*1000000),(int) (loc.getLongitude()*1000000));
		mapControl.setZoom(18);
		mapControl.animateTo(geoPoint);
		
		
		MapOverlay mMapOverlay= new MapOverlay();
		
		mMapOverlay.setDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		mMapOverlay.setGeoPoint(geoPoint);
		
		final List<Overlay> overlays = mapview.getOverlays();
		
		overlays.clear();
		overlays.add(mMapOverlay);
		
		mapview.setClickable(true);
    }
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private class MyLocationListener implements LocationListener
    {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
		
			loc = location;
			Log.d("traza gps:", String.valueOf(location.getLatitude()) +
					" " +
					String.valueOf(location.getLongitude()));
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
	
	public void configGps()
	{
		LocationManager mLocationManager;
		LocationListener mLocationListener;
		
		mLocationManager = (LocationManager)
				getSystemService(Context.LOCATION_SERVICE);

		
		mLocationListener = new MyLocationListener();
		
		mLocationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER,
				5000, 15,
				mLocationListener);

		
	}

}
