package com.example.ConectividadDia7;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends ListActivity {
    public static final String MESSAGE_KEY = "message";
    public Context mContext;
    private PlaceAdapter mPlacesAdapter = null;
    public ArrayList <PlaceObject> mArray;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeslistalayout);
        mContext = this;

        JSONDownloader downloadJSON = new JSONDownloader();
        downloadJSON.execute(null, null, null);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(mContext, "URL de la info: " + mArray.get(position).getmInformationUrl().toString(), Toast.LENGTH_SHORT).show();
        showNotification("Notificación numero " + position,"Esta es la informacion del objeto seleccionado, objeto numero " + position);
    }

    //AsyncTask

    public class JSONDownloader extends AsyncTask <Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(Void... voids) {
            String dataString = getDataString(mContext.getString(R.string.urlConnection));
            Log.d("", dataString);

            JSONParser jsonParser = new JSONParser(dataString);
            mArray = jsonParser.parser();
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
            Toast.makeText(mContext, "Numero de elementos en el JSON parseado " + mArray.size(), Toast.LENGTH_SHORT).show();
            mPlacesAdapter = new PlaceAdapter();
            setListAdapter(mPlacesAdapter);
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

    //adapter
    public class PlaceAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mArray.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.placeobjectlayout, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.placeImageView);

            DownloadImage downloadImage = new DownloadImage(imageView);
            downloadImage.execute(mArray.get(position).getmPhotoUrl(), null, null);

            //para hacerlo sin conexion a internet
            //imageView.setImageBitmap(getBitmapFromURL(mArray.get(position).getmPhotoUrl()));
            //imageView.setBackgroundColor(0);

            TextView placeName = (TextView) view.findViewById(R.id.placeNameTextView);
            placeName.setText(mArray.get(position).getmName());

            return view;
        }
    }

    //esto lo hace sin un thread MAL porque la conexion a internet debe ser en un hilo
    /*private Bitmap getBitmapFromURL(Uri uriBitmap) {
        try {
            URL url = new URL(uriBitmap.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }*/

    public class DownloadImage extends AsyncTask <Uri, Void, Bitmap> {
        ImageView mImageView;

        public DownloadImage(ImageView imageView) {
            mImageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Uri... uris) {
            return getBitmapFromURL(uris[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mImageView.setImageBitmap(bitmap);
            mImageView.setBackgroundColor(0);
        }

        private Bitmap getBitmapFromURL(Uri uriBitmap) {
            try {
                URL url = new URL(uriBitmap.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }
    }

    public void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new Notification(R.drawable.logo, title, System.currentTimeMillis());
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        Intent intent = new Intent(this, PlaceInfoActivity.class);
        intent.putExtra(MESSAGE_KEY, message);

        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(this, title, message, activity);

        notificationManager.notify(0, notification);
    }
}
