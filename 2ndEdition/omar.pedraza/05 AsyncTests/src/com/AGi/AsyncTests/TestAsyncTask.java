//
//  TestAsyncTask.java
//  05 AsyncTests
//
//  Created by Omar Pedraza on 2/22/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.AsyncTests;

import android.content.Context;
import android.os.AsyncTask;

public class TestAsyncTask extends AsyncTask {
    public static Context mContext = null;

    public TestAsyncTask(Context context) {
        super();

        mContext = context;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
