package es.android.mymaps;

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
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity {

	private MapView mapview;
	private LocationManager mLocationManager;
	private LocationListener mLocationListener;	
	private Location mLoc;
	private MyAsyncTask mat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		mPd = ProgressDialog.show(this, getString(R.string.title_progress), getString(R.string.title_message));
		mat = new MyAsyncTask();
		mat.execute(null,null, null);
		mapview = (MapView) findViewById(R.id.myMapView);
		
		mapview.setBuiltInZoomControls(true);
		mapview.setClickable(true);

		
		mapview.getController();
		setLocationListener();

	}

	private void setLocationListener() {
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
	    mLocationListener = new MyLocationListener();
	    
	    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
			5000,15,mLocationListener);		
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
	
	private void refreshMap()
	{		mat.onPostExecute(null);
//		if (mPd.isShowing())
	//		mPd.dismiss();
		if (mLoc == null){
			Toast.makeText(getBaseContext(), "Location not available", Toast.LENGTH_SHORT).show();
			return;
		}
		GeoPoint geoPoint = new GeoPoint((int) mLoc.getLatitude()*1000000,
				(int) mLoc.getLongitude()*1000000);

		MapOverlay myMapOver = new MapOverlay();
		myMapOver.setDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		myMapOver.setGeoPoint(geoPoint);
		
		final List<Overlay> overlays = mapview.getOverlays();
		overlays.clear();

		overlays.add(myMapOver);
				
		mapview.setClickable(true);
    	
		
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
	
	protected void onDestroy(){
		super.onDestroy();
		mLocationManager.removeUpdates(mLocationListener);
	}

	public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
		
		private ProgressDialog mPd = null;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mPd = ProgressDialog.show(MainActivity.this, getString(R.string.title_progress), getString(R.string.title_message));
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MainActivity.this.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			});
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (mPd.isShowing())
				mPd.dismiss();
		}
	}
}
