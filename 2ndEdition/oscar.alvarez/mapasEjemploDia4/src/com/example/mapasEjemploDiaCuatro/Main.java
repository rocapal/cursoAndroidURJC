package com.example.mapasEjemploDiaCuatro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.maps.*;

import java.util.List;

public class Main extends MapActivity {

    private Location mLoc;
    private MapView mapview = null;
    private MapController mapController = null;

    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

    ProgressDialog mProgress = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //MyAsyncTask task = new MyAsyncTask();
        mProgress = ProgressDialog.show(this, getString(R.string.titleProgressDialog),this.getString(R.string.detailsProgressDialog));

        mapview = (MapView) findViewById(R.id.myMapView);
        mapview.setBuiltInZoomControls(true);
        mapController = mapview.getController();
        mapview.setClickable(true);
        mapview.setTraffic(true);
        configGPS();
    }

    @Override
    protected boolean isLocationDisplayed() {
        return super.isLocationDisplayed();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;    //To change body of overridden methods use File | Settings | File Templates.
    }

    private void configGPS ()
    {
        mLocationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new MyLocationListener();
        mLocationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000, 15,
                mLocationListener);
    }

    /*public class MyAsyncTask extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object... objects) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        protected void doInBackground(Void... params) {
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();    //To change body of overridden methods use File | Settings | File Templates.
            mProgress = ProgressDialog.show(getBaseContext(), getString(R.string.titleProgressDialog), getString(R.string.detailsProgressDialog));

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);    //To change body of overridden methods use File | Settings | File Templates.
            if (mProgress.isShowing()){
                mProgress.dismiss();
            }
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();    //To change body of overridden methods use File | Settings | File Templates.
        }
    }*/

    private class MyLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location) {
            //SE puede guardar la localización en el atributo aqui y utilizarlo en lugar de pasarlo como argumento
            Log.d("Location:", String.valueOf(location.getLatitude()) +
                    " " + String.valueOf(location.getLongitude()));
            refreshMap(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onProviderEnabled(String s) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onProviderDisabled(String s) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    private void refreshMap( Location location)
    {
        if (mProgress.isShowing()){
            mProgress.dismiss();
        }

        if (location == null) {
            Toast.makeText(getBaseContext(), "Location not available!", Toast.LENGTH_LONG).show();
            return;
        }

        GeoPoint geoPoint = new GeoPoint ( (int) (location.getLatitude() * 1000000),
                (int) (location.getLongitude() * 1000000));

        mapController.setZoom(18);
        mapController.animateTo(geoPoint);

        MapOverlay myMapOverlay = new MapOverlay();
        myMapOverlay.setDrawable(getResources().getDrawable(R.drawable.chincheta));
        myMapOverlay.setGeoPoint(geoPoint);
        myMapOverlay.setText(getResources().getString(R.string.localizacion));

        final List<Overlay> overlays = mapview.getOverlays();
        overlays.clear();

        overlays.add(myMapOverlay);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
        mLocationManager.removeUpdates(mLocationListener);
    }
}