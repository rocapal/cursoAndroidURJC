//
//  TestLocationService.java
//  05 AsyncTests
//
//  Created by Omar Pedraza on 2/26/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.AsyncTests;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class TestLocationService extends Service implements LocationListener {
    private static Boolean mRunning = false;
    private static Context mContext = null;
    private static iListener mListener = null;
    private static LocationManager mLocationManager = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mRunning = true;

        initGPS();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mRunning = false;
    }

    @Override
    public ComponentName startService(Intent service) {
        return super.startService(service);
    }

    @Override
    public boolean stopService(Intent name) {
        return super.stopService(name);
    }

    @Override
    public void onLocationChanged(Location location) {
        mListener.setText(mContext.getResources().getString(R.string.location_service_text, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    private void initGPS() {
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        mLocationManager.requestLocationUpdates(mLocationManager.getBestProvider(criteria, true), 5000, 15, this);
    }

    public static boolean isRunning() {
        return mRunning;
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public static void registerListener(iListener listener) {
        mListener = listener;
    }
}
