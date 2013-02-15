//
//  MapAsyncTask.java
//  04 MapsTest
//
//  Created by Omar Pedraza on 2/15/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//
package com.AGi.MapsTest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class MapAsyncTask extends AsyncTask {
    private Context mContext = null;
    private ProgressDialog mDialog = null;

    public MapAsyncTask(Context context) {
        super();

        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mDialog = new ProgressDialog(mContext);
        mDialog.setTitle(mContext.getResources().getString(R.string.app_name));
        mDialog.setMessage(mContext.getResources().getString(R.string.map_dialog));
        mDialog.show();
    }

    @Override
    protected Object doInBackground(Object... objects) {
        try {
            Thread.currentThread().sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } ;

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
