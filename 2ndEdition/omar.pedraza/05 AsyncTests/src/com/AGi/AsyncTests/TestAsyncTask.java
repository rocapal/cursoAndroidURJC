//
//  TestAsyncTask.java
//  05 AsyncTests
//
//  Created by Omar Pedraza on 2/22/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.AsyncTests;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

public class TestAsyncTask extends AsyncTask {
    private static Context mContext = null;
    public static Integer mProgress = 0;
    private static ProgressBar mProgressBar = null;

    public TestAsyncTask(Context context) {
        super();

        mContext = context;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        try {
            while (mProgress < 100) {
                mProgress++;
                publishProgress(mProgress);
                Thread.currentThread().sleep(100);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        };

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if (mProgressBar.getVisibility() != View.INVISIBLE) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        mProgress = 0;
        publishProgress(mProgress);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressBar = (ProgressBar) ((Activity) mContext).findViewById(R.id.progress_bar);
        if (mProgressBar.getVisibility() != View.VISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);

        mProgressBar.setProgress((Integer) values[0]);
    }
}
