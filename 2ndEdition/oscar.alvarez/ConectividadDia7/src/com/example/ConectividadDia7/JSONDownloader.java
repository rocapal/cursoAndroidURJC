package com.example.ConectividadDia7;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 08/03/13
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
public class JSONDownloader extends AsyncTask <Void, Void, Void> {
    ProgressDialog progressDialog;
    Context mContext;

    public JSONDownloader(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String dataString = getDataString(mContext.getString(R.string.urlConnection));
        Log.d("", dataString);

        JSONParser jsonParser = new JSONParser(dataString);
        //ArrayList<PlaceObject> = jsonParser.parser();
        jsonParser.parser();

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Descarga");
        progressDialog.setMessage("Descarga de fichero JSON...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
    }

    public String getDataString (String urlString) {
        try
        {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpGet httpGet = null;
            httpGet = new HttpGet(urlString);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String jsonDataString = EntityUtils.toString(entity);
            return jsonDataString;
        }
        catch (IOException e) {
            Log.e("doGet",e.getMessage());
            return null; }
    }
}
