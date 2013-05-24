package es.cursoandorid.yncat;

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
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Hashtag extends ListActivity {

	private final String url = "http://search.twitter.com/search.json?q="+ MainActivity.searchString + "&rpp=5&include_entities=true&result_type=mixed";
	public Context mContext;
	public ArrayList<ItemJs> mArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista);
		StrictMode.ThreadPolicy policy = new
				StrictMode.ThreadPolicy.Builder().permitNetwork().build();
				StrictMode.setThreadPolicy(policy);
		
		this.mContext = this;
		DownloadJSON djson = new DownloadJSON();
		djson.setUrl(url);
		djson.execute();
	}



	public void pasarAdaptador(){
		MyAdapter madap = new MyAdapter();
		setListAdapter(madap);
	}
	
	public class DownloadJSON extends AsyncTask<Void, Void, Void> {
		private ProgressDialog pd;
		private String mUrl;
		private ArrayList<ItemJs> mlista;
			
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			pd = ProgressDialog.show(mContext, "Descargando", "Descargando JSCON");
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String str = doGetPetition(mUrl);
			JsonParsing json = new JsonParsing(str);
			this.setLista(json.parser());
			Log.d("JSON", str);
			return null;
		}
		
		private String doGetPetition (String mUrl)
		{
			try
			{
				final ConnectivityManager conMan = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
				final State mobile = conMan.getNetworkInfo(0).getState();
				final State wifi = conMan.getNetworkInfo(1).getState();
				if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED)
				{
					DefaultHttpClient httpclient = new DefaultHttpClient();
					HttpGet httpGet = null;
					httpGet = new HttpGet(mUrl);
					HttpResponse response = httpclient.execute(httpGet);
					HttpEntity entity = response.getEntity();
					String str = EntityUtils.toString(entity);
					return str;
				} else
					return null;
			}
			catch (IOException e) {
				Log.e("doGet",e.getMessage());
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();			
			mArray = this.getLista();
			if (mArray != null)
				pasarAdaptador();
		}


		public String getUrl() {
			return mUrl;
		}

		public void setUrl(String mUrl) {
			this.mUrl = mUrl;
		}

		public ArrayList<ItemJs> getLista() {
			return mlista;
		}

		public void setLista(ArrayList<ItemJs> mlista) {
			this.mlista = mlista;
		}

	}
	public class MyAdapter extends BaseAdapter {
		
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
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			if (convertView == null) {
			
				LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
							Context.LAYOUT_INFLATER_SERVICE);
			
				view = inflater.inflate(R.layout.personal_layout, null);
			} else {
				view = convertView;
			}
			TextView tv = (TextView) view.findViewById(R.id.tvTexto1);
			tv.setText(mArray.get(position).getNombre() + ", " 
						+ mArray.get(position).getDate());
			TextView tv2 = (TextView) view.findViewById(R.id.tvTexto2);
			tv2.setText(mArray.get(position).getTexto());
			ImageView im = (ImageView) view.findViewById(R.id.imag1);
			Bitmap bm = getBitmapFromURL(mArray.get(position).getFotoUrl());
			im.setImageBitmap(bm);
			return view;
		}


		private Bitmap getBitmapFromURL(String urlBitmap)
		{
			try{
				URL url =  new URL(urlBitmap);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.connect();
				InputStream input = connection.getInputStream();
				Bitmap myBitmap = BitmapFactory.decodeStream(input);
				return myBitmap;
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
	}

}
