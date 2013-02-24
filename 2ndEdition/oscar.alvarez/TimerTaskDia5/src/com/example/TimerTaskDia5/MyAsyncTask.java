package com.example.TimerTaskDia5;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 22/02/13
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */
public class MyAsyncTask extends AsyncTask {
    Context mContext;
    ProgressDialog pd = null;
    int progress;

    public MyAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        for (int i = 0; i < 100; i++)
        {
            try {
                progress = i;
                publishProgress(i);
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();    //To change body of overridden methods use File | Settings | File Templates.
        pd = new ProgressDialog(mContext);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setTitle("AsyncTask Background");
        pd.setMessage("Executing...");
        pd.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);    //To change body of overridden methods use File | Settings | File Templates.
        pd.dismiss();
        Toast.makeText(mContext, "Progress bar successfully Presented", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);    //To change body of overridden methods use File | Settings | File Templates.
        pd.setProgress(progress);
    }

}
