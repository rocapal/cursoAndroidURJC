package es.bmuma.tacografo;

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
import android.widget.Toast;

public class Mapa extends MapActivity {
	
	private MapView mapa;
	private LocationManager mLocationManager;
	private MyLocationListener mLocationListener;
	private MapController mapContr;
	private Location mloc;
		
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maps_layout);
		//Progress dialog
		//mPd = ProgressDialog.show(this, getString(R.string.title_progress_dialog), getString(R.string.title_progress_msg));
		//Llamamos a la tarea asíncrona
		AsincronTask taskAsc = new AsincronTask();
		taskAsc.execute(null,null,null);
		
		mapa = (MapView) findViewById(R.id.myMapView);
		//Poner control de Zoom
		mapa.setBuiltInZoomControls(true);
		//What sea clickable
		mapa.setClickable(true);
		mapa.setSatellite(true);
		mapContr = mapa.getController();
		//Establish el litsener
		createLocationLitsener();
		

	}

	private void createLocationLitsener() {

		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mLocationListener = new MyLocationListener();
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
												5000, 15, mLocationListener);
		
	}

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mLocationManager.removeUpdates(mLocationListener);
	}

	@Override
	protected boolean isRouteDisplayed() {
	
		return false;
	}
	
	public class MyLocationListener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			mloc = location;
			Log.d("Location:", String.valueOf(location.getLatitude()) +
					" " + String.valueOf(location.getLongitude()));

			refreshMap();
			
			
		}

		private void refreshMap() {
			if (mloc == null){
				Toast.makeText(getBaseContext(), getString(R.string.posicion), 
						Toast.LENGTH_LONG).show();
				return;
				
			}
			GeoPoint point = new GeoPoint((int) (mloc.getLatitude()* 1000000),
					(int) (mloc.getLongitude()*1000000));
			mapContr.setZoom(18);
			mapContr.animateTo(point);
			MyOverlay myoverlay = new MyOverlay();
			myoverlay.setGeoPoint(point);
			myoverlay.setText(getString(R.string.app_name));
			myoverlay.setDrawable(getResources().getDrawable(R.drawable.circulito));
			List<Overlay> overlays = mapa.getOverlays();
			overlays.clear();
			overlays.add(myoverlay);
			
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			refreshMap();
			
		}
		
	}
	
	public class AsincronTask extends AsyncTask<Void, Void, Void>
	{
		ProgressDialog mPd;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mPd = ProgressDialog.show(Mapa.this, 
					getString(R.string.title_progress_dialog_maps), 
					getString(R.string.title_progress_msg_maps));
		}
		
		
		@Override
		protected Void doInBackground(Void... params) {
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				Log.e(null, null, e);
			}
			
			Mapa.this.runOnUiThread(new Runnable() {
				@Override
				public void run() {
		
					
				}
			});
			
			return null;
						
		}
		
		@Override
		protected void onPostExecute(Void result) {
			
			super.onPostExecute(result);
			if(mPd.isShowing())
				mPd.dismiss();
			
		}
		
	}
}
