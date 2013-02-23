package com.example.TimerTaskDia5;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 22/02/13
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */
public class MyAsyncTask extends AsyncTask {
    Context mContext;
    ProgressDialog pd;

    public MyAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();    //To change body of overridden methods use File | Settings | File Templates.
        pd = new ProgressDialog(mContext);
        pd.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);    //To change body of overridden methods use File | Settings | File Templates.
        pd.dismiss();
    }
}
