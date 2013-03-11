package com.example.hellojson;

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

import com.example.helojson.R;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	ArrayList<JSONItem> mArray;

	public Context mContext;

	public final String URL = "http://rest.libregeosocial.org/social/layer/560/search/?search=&latitude=40.2855&longitude=-3.8222&radius=1.0&category=0&elems=20&page=1&format=JSON";

	NotificationManager mNM;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		MiAssyncTask dl = new MiAssyncTask();
		dl.execute();
		
		

	}

	public class MiAssyncTask extends AsyncTask<Void, Void, Void> {

		ProgressDialog pd;

		private String doGetPetition(String mURL) {
			try {
				DefaultHttpClient httpclient = new DefaultHttpClient();
				HttpGet httpGet = null;
				httpGet = new HttpGet(mURL);
				HttpResponse response = httpclient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity);
				return str;
			} catch (IOException e) {
				Log.e("doGet", e.getMessage());
				return null;
			}
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			pd = ProgressDialog.show(mContext, "Descargando",
					"Descargando JSON...");

		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			String str = doGetPetition(URL);

			Log.d("Resultado", str);
			JSONConstructor jsonP = new JSONConstructor(str);
			mArray = jsonP.parse();

			Log.d("Cantidad", String.valueOf(mArray.size()));
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if (pd.isShowing())
				pd.dismiss();
			
			MiAdaptador adaptador = new MiAdaptador();
			setListAdapter(adaptador);

			showNotification();
		}
	}

	public class MiAdaptador extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mArray.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			view = inflater.inflate(R.layout.nodo_xml, null);
			if(view==null) Log.d("view", "vacio");
			ImageView img = (ImageView) view.findViewById(R.id.imagen1);
			if(img==null) Log.d("imagen", "vacio");
			Bitmap b =getBitmapFromURL(mArray.get(position).getmPhotoURL().toString());
			if (b==null) Log.d("b", "vacio");
			img.setImageBitmap(b);

			TextView tex = (TextView) view.findViewById(R.id.texto1);
			tex.setText(mArray.get(position).getmName());

			return view;
		}

	}
	
	private Bitmap getBitmapFromURL (String urlBitmap){
		try{
			URL url = new URL(urlBitmap);
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.setDoInput(true);
			conexion.connect();
			InputStream input = conexion.getInputStream();
			Bitmap miBitmap = BitmapFactory.decodeStream(input);
			return miBitmap;
		}catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}
	

	private void showNotification(){
		
		mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		
		CharSequence text = "New places available";
		
		Notification notificacion = new Notification(R.drawable.ic_launcher, text, System.currentTimeMillis());
		
		Intent iNotificacion = new Intent(this, new MainActivity().getClass());
		
		iNotificacion.putExtra("etiqueta", "valor");
		
		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, iNotificacion, PendingIntent.FLAG_CANCEL_CURRENT);
		
		
		
		
	}
}
