//
//  PlacesAsyncTask.java
//  07 Connectivity
//
//  Created by Omar Pedraza on 3/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Connectivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class PlacesAsyncTask extends AsyncTask {
    private static Context mContext = null;
    private static PlacesListener mListener = null;
    private static ProgressDialog mDialog = null;
    private static Uri mURL = null;

    public PlacesAsyncTask(Context context) {
        super();

        mContext = context;

        if (mContext instanceof PlacesListener) {
            mListener = (PlacesListener) mContext;
        }
    }

    @Override
    protected Object doInBackground(Object... objects) {
        try
        {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(mURL.toString());
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();

            String response = EntityUtils.toString(entity);

            return response;
        }
        catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        PlacesParser parser = new PlacesParser((String) o);
        ArrayList<Place> places = parser.parseJSON();
        mListener.refreshData(places);

        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mDialog = new ProgressDialog(mContext);
        mDialog.setTitle(mContext.getResources().getString(R.string.app_name));
        mDialog.setMessage(mContext.getResources().getString(R.string.data_dialog));
        mDialog.show();
    }

    public void setURLForLocation(Location location) {
        String service = "http://rest.libregeosocial.org/social/layer/560/search/?search=&latitude=" + String.valueOf(location.getLatitude()) + "&longitude=" + String.valueOf(location.getLongitude()) + "&radius=1.0&category=0&elems=20&page=1&format=JSON";

        mURL = Uri.parse(service);
    }
}
