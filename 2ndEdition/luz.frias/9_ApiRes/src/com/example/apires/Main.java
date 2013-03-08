package com.example.apires;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class Main extends ListActivity {
	
	private final static String URL ="http://rest.libregeosocial.org/social/layer/560/search/?search=&latitude=40.2855&longitude=-3.8222&radius=1.0&category=0&elems=20&page=1&format=JSON";
	public final static int NOTIFICATION_ID = 0;
	private Activity mContext;
	private JSONDownloadTask mJsonTask;
	private ArrayList<PhotoItem> photos;
	private NotificationManager mNM;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Permisos de red en el UI Thread
		StrictMode.ThreadPolicy policy = new
				StrictMode.ThreadPolicy.Builder().permitNetwork().build();
		StrictMode.setThreadPolicy(policy);
		
		mContext = this;
		mJsonTask = new JSONDownloadTask();
		mJsonTask.execute();
	}
	
	private void showNotification() {
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		CharSequence text = "New places available!";
		
		Notification notification = new Notification(R.drawable.ic_launcher, text, System.currentTimeMillis());
		
		Intent iNotification = new Intent(this, new DummyActivity().getClass());
		
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, iNotification, PendingIntent.FLAG_CANCEL_CURRENT);
		
		notification.setLatestEventInfo(mContext, "Panoramio", "You have new places", contentIntent);
		
		mNM.notify(NOTIFICATION_ID, notification);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class JSONDownloadTask extends AsyncTask<Void,Void,Void> {
		private ProgressDialog pd = null;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			pd = ProgressDialog.show(mContext, "Descargando", "Descargando JSON");
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			String str = doGetPetition(URL);
			Log.d("JSONDonwloadTask", "Response: " + str);
			
			JSONParser parser = new JSONParser(str);
			photos = parser.parse();
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			if (pd != null) {
				pd.dismiss();
			}
//			Toast.makeText(mContext, "# elementos: " + photos.size(), Toast.LENGTH_LONG).show();
			ListAdapter la = new PhotoAdapter();
			
			setListAdapter(la);
			
			showNotification();
		}
		
		private String doGetPetition (String url) {
			try
			{
				DefaultHttpClient httpclient = new DefaultHttpClient();
				HttpGet httpGet = null;
				httpGet = new HttpGet(url);
				HttpResponse response = httpclient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity);
				return str;
			} catch (IOException e) {
				Log.e("doGetPetition",e.getMessage());
				return null;
			}
		}
	}
	
	public class PhotoAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return photos.size();
		}

		@Override
		public Object getItem(int index) {
			return photos.get(index);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int index, View vista, ViewGroup grupoVista) {
			PhotoItem photo = photos.get(index);
			
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			View view = inflater.inflate(R.layout.photo, null);
			
			TextView txName = (TextView) view.findViewById(R.id.name);
			txName.setText(photo.getName());
			
			ImageView thumb = (ImageView) view.findViewById(R.id.thumb);
			thumb.setImageBitmap(getBitmapFromUrl(photo.getThumb()));

			return view;
		}
		
		private Bitmap getBitmapFromUrl(String urlString) {
			try {
				URL url = new URL(urlString);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream input = connection.getInputStream();
				Bitmap bitmap = BitmapFactory.decodeStream(input);
				return bitmap;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
	}

}
