//
//  LocationService.java
//  07 Connectivity
//
//  Created by Omar Pedraza on 3/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Connectivity;

import android.app.*;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class LocationService extends Service implements LocationListener {
    private static Boolean mRunning = false;
    private static Context mContext = null;
    private static PlacesAsyncTask mAsyncTask = null;
    private static ProgressDialog mDialog = null;
    private static LocationManager mLocationManager = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mRunning = true;

        mDialog = new ProgressDialog(mContext);
        mDialog.setTitle(mContext.getResources().getString(R.string.app_name));
        mDialog.setMessage(mContext.getResources().getString(R.string.location_dialog));
        mDialog.show();

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
        if (location != null) {
            if (mAsyncTask == null) {
                mAsyncTask = new PlacesAsyncTask(mContext);
            }

            mAsyncTask.setURLForLocation(location);
            mAsyncTask.execute(null, null, null);
        }

        //showNotification();

        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
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

    private void showNotification()
    {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String text = String.valueOf(20);

        Notification notification = new Notification(R.drawable.taylor_swift, text, System.currentTimeMillis());

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra(Constants.kNotificationNumber, 20);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        notification.setLatestEventInfo(this, "Notification test", text, contentIntent);

        manager.notify(0, notification);
    }
}
