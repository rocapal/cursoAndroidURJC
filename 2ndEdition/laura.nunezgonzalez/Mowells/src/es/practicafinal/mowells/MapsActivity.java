package es.practicafinal.mowells;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapsActivity extends MapActivity {

	public static Handler mHandler;
	public Location mLoc;
    private MapView mapview = null;
    private MapController mapControl = null;
    private Context mContexto;
	private LocationManager mLocationManager;
	private es.practicafinal.mowells.MapsActivity.MyLocationListener mLocationListener;
	private OverlayMapa myMapOver = new OverlayMapa();
    private List<Overlay> overlays;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
        mapview = (MapView) findViewById(R.id.myMapView);
        mContexto = this;
        mapview.setBuiltInZoomControls(true);
        mapview.setSatellite(false);
        mapControl = mapview.getController(); 
        overlays = mapview.getOverlays();
	/*	mHandler = new Handler(new Callback(){
			
		public boolean handleMessage(Message msg) {
				Log.d("TAG", "Llega mensaje");
				Toast.makeText(mContexto, mContexto.getString(R.string.toast_msg_location) + String.valueOf(msg.arg1) +
						"," + String.valueOf(msg.arg2),
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		startService(new Intent(mContexto, LocationService.class));   */
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    mLocationListener = new MyLocationListener();
		Log.d("LISTENER", "mLocationListener");	    
	    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
			1000,1,mLocationListener);	
	    mapControl.setZoom(10);
		mapview.setClickable(true);
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
		return true;
	}

	private class MyLocationListener implements LocationListener
	{

		@Override
		public void onLocationChanged(Location location) {
			if(mLoc == null)
				mLoc = location;
			Log.d("Traza GPS", "Posición: " + location.getLatitude()+ "," + location.getLongitude());
			double distancia = location.distanceTo(mLoc);
			Log.d("Traza GPS", "Distancia: " + String.valueOf(distancia));
			Toast.makeText(mContexto, "Estas en: " + location.getLatitude() + 
					"," + location.getLongitude() , Toast.LENGTH_SHORT).show();
			GeoPoint geo = new GeoPoint((int) location.getLatitude(),(int)location.getLongitude());
	//		mapControl.setCenter(geo);
		//	mapControl.zoomToSpan((int) location.getLatitude(),(int)location.getLongitude());

			myMapOver.setLocalicacion(location);
			overlays.clear();
			overlays.add(myMapOver);	
			Log.d("CHANGE", ((OverlayMapa) overlays.get(0)).latitud + ", " + ((OverlayMapa) overlays.get(0)).longitud );
			mapControl.animateTo(myMapOver.getMyLocation());
//			mapview.postInvalidate();
			mLoc = location;
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
		
	public class OverlayMapa extends Overlay {
	    public Double latitud;
	    public Double longitud;
	 
	    public void setLocalicacion(Location l)
	    {
	    	this.latitud = l.getLatitude();
	    	this.longitud = l.getLongitude();
	    }
	    public GeoPoint getMyLocation() {
			// TODOGeoPoint geoPoint =
            return new GeoPoint(latitud.intValue(), longitud.intValue());
		}
		@Override
	    public void draw(Canvas canvas, MapView mapView, boolean shadow)
	    {
	        Projection projection = mapView.getProjection();
	        GeoPoint geoPoint =
	            new GeoPoint(latitud.intValue(), longitud.intValue());
	        Log.d("OVERLAY", String.valueOf(latitud.intValue()) + ", " + String.valueOf(longitud.intValue()));
	        if (shadow == false)
	        {
	            Point centro = new Point();
	            projection.toPixels(geoPoint, centro);
	            Log.d("DRAW", String.valueOf(centro.x) + ", " + String.valueOf(centro.x));
	            //Definimos el pincel de dibujo
	            Paint p = new Paint();
	            p.setColor(Color.BLUE);
	            
	            Bitmap bm = BitmapFactory.decodeResource(
	                    mapView.getResources(),
	                    R.drawable.ic_launcher);
	             
	            canvas.drawBitmap(bm, centro.x - bm.getWidth(),
	                    centro.y - bm.getHeight(), p);
	        }
	    }
	    
	    public boolean onTap(GeoPoint point, MapView mapView)
	    {
	        Context contexto = mapView.getContext();
	        String msg = "Lat: " + point.getLatitudeE6()/1E6 + " - " +
	            "Lon: " + point.getLongitudeE6()/1E6;
	     
	      //  Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
	       // toast.show();
	     
	        return true;
	    }
	}	
}
