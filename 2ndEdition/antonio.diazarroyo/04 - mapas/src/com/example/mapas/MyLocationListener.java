package com.example.mapas;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MyLocationListener implements LocationListener {

	
	private MapView mapView;
	private Location location;
	private Context context;
	private ProgressDialog progressDialog;
	
	
	public MyLocationListener (Context myContext, MapView myMapView, ProgressDialog myProgressDialog) {
		context = myContext;
		mapView = myMapView;
		progressDialog = myProgressDialog;
	}
	
	
	@Override
	public void onLocationChanged(Location myLocation) {
		Log.d("Location:", "Cambio de localizacion:" + myLocation.getLatitude() + " - "+ myLocation.getLongitude());
		Log.d("Location:", "speed:" + myLocation.getSpeed());
		Log.d("Location:", "location:" + myLocation);
		location = myLocation;
		refreshMap();
	}

	private void refreshMap() {
		if (progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
		if (location==null) {
			Toast.makeText(context, "error location not defined", Toast.LENGTH_SHORT).show();
		} else {
			GeoPoint geoPoint = new GeoPoint((int)(location.getLatitude()* 1E6), (int)(location.getLongitude() * 1E6));
			mapView.getController().setZoom(18);
			mapView.getController().animateTo(geoPoint);
			
			MapOverlay mapOverlay = new MapOverlay();
			mapOverlay.setDrawable(context.getResources().getDrawable(R.drawable.android));
			mapOverlay.setGeoPoint(geoPoint);
			
			final List<Overlay> overLay = mapView.getOverlays();
			overLay.clear();
			overLay.add(mapOverlay);
			
			mapView.setSatellite(true);
			mapView.setBuiltInZoomControls(true);
		}
	}
	
	
	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {


	}

}
