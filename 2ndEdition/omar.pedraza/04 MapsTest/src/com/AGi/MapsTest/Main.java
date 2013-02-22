package com.AGi.MapsTest;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.maps.*;

import java.util.List;

public class Main extends MapActivity {
    private LocationListener mLocationListener = null;
    private LocationManager mLocationManager = null;
    private MapController mMapController = null;
    private MapView mMapView = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        MapAsyncTask asyncTask = new MapAsyncTask(this);
        asyncTask.execute(null, null, null);

        mMapView = (MapView) this.findViewById(R.id.myMapView);
        mMapView.setClickable(true);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setSatellite(true);

        mMapController = mMapView.getController();

        getLocationByCriteria();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLocationManager.removeUpdates(mLocationListener);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;    //To change body of overridden methods use File | Settings | File Templates.
    }

    private void getLocationByCriteria() {
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location:", String.valueOf(location.getLatitude()) + ", " + String.valueOf(location.getLongitude()));

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
        };

        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        mLocationManager.requestLocationUpdates(mLocationManager.getBestProvider(criteria, true), 5000, 15, mLocationListener);
    }

    private void refreshMap(Location location) {
        if (location != null) {
            GeoPoint point = new GeoPoint(this.locationToPoint(location.getLatitude()), this.locationToPoint(location.getLongitude()));

            mMapController.setZoom(18);
            mMapController.animateTo(point);

            MapOverlay overlay = new MapOverlay();
            overlay.setDrawable(this.getResources().getDrawable(R.drawable.red_pin));
            overlay.setGeoPoint(point);

            final List<Overlay> overlays = mMapView.getOverlays();
            overlays.clear();

            overlays.add(overlay);
        }
        else {
            Toast toast = Toast.makeText(this, "Location not available!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private Integer locationToPoint(Double location) {
        Integer point = (int) (location * 1000000);

        return point;
    }
}
