package com.example.gjson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	String URL_JSON = "http://rest.libregeosocial.org/social/layer/560/search/?search=&latitude=40.2855&longitude=-3.8222&radius=1.0&category=0&elems=20&page=1&format=JSON";
	Context mContext = this;
	String TAG = this.getClass().getName();
	static ArrayList<JSONitem> mArray;
	private MyAdapter ma = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Download down = new Download();
		down.execute(null,null,null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public class Download extends AsyncTask<Void,Void,Void>{
		ProgressDialog pd;

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			pd = ProgressDialog.show(mContext, "Downloading", "Downloading JSON...");
		}

		@Override
		protected Void doInBackground (Void... param) {

			String str = doGetPetition(URL_JSON);
			Log.d(TAG, str);

		    JSONParse jsonP = new JSONParse(str);
    		mArray = jsonP.parser();
    		
			return null;
		}

		@Override
		protected void onPostExecute(Void result){
			super.onPostExecute(result);

			if (pd.isShowing())
				pd.dismiss();
			
			Toast.makeText(mContext, "nelements: " +
			               String.valueOf(mArray.size()),
			               Toast.LENGTH_SHORT).show();

			ma = new MyAdapter(mContext);
			setListAdapter(ma);
			ShowNotification();
		}
		
		

		private String doGetPetition (String mUrl) {
			try{
				DefaultHttpClient httpclient = new DefaultHttpClient();
				HttpGet httpGet = null;
				httpGet = new HttpGet(mUrl);
				HttpResponse response = httpclient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity);
				return str;
			}catch (IOException e) {
				Log.e("doGet",e.getMessage());
				return null;
			}	
		}
	}
	
	private void ShowNotification (){
		NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		
		CharSequence text = mArray.size() + "nuevos";
		
		Notification notification = new Notification(R.drawable.ic_launcher,text,
			      System.currentTimeMillis());
		      
		Intent iNotification = new Intent(this, new MainActivity().getClass());
		
		/*iNotification.putExtra(HelperPanoramio.NEW_PLACES_NAME,
				               HelperPanoramio.NEW_PLACES_VALUE);*/
		
		PendingIntent contentIntent = PendingIntent.getActivity
				(this, 0, iNotification, PendingIntent.FLAG_CANCEL_CURRENT);
		
		notification.setLatestEventInfo(mContext, "titulo", text, contentIntent);
		mNM.notify(1,notification);
	}
	
	public class MyAdapter extends BaseAdapter {
		private Context mContext;

		public MyAdapter(Context c) {
			mContext = c;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mArray.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mArray.get(position);
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View view = null;
			
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			view  = inflater.inflate(R.layout.list, null);
			
			TextView tvTitle = (TextView) view.findViewById(R.id.title);
			tvTitle.setText(mArray.get(position).getmName());
			
			ImageView image = (ImageView) view.findViewById(R.id.image);
			image.setImageBitmap(getBitmapFromURL(mArray.get(position).getmPhotoUri()));
			
			return view;
		}
		
		private Bitmap getBitmapFromURL(String src) {
		     try {
		         URL url = new URL(src);
		         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		         connection.setDoInput(true);
		         connection.connect();
		         InputStream input = connection.getInputStream();
		         Bitmap myBitmap = BitmapFactory.decodeStream(input);
		         return myBitmap;
		     } catch (IOException e) {
		         e.printStackTrace();
		         return null;
		     }
		 }
	}
	
}