package es.android.wifiandogepeto;

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
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private final String url = "http://rest.libregeosocial.org/social/layer/560/search/?search=&latitude=40.2855&longitude=-3.8222&radius=1.0&category=0&elems=20&page=1&format=JSON";
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
				DefaultHttpClient httpclient = new DefaultHttpClient();
				HttpGet httpGet = null;
				httpGet = new HttpGet(mUrl);
				HttpResponse response = httpclient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity);
				return str;
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
			Toast.makeText(mContext,"Numero de elementos " + 
					String.valueOf(this.getLista().size()), Toast.LENGTH_SHORT).show();
			
			mArray = this.getLista();
			if (mArray != null)
			{
				Log.d("Adapter", "La lista no es nula");
				pasarAdaptador();
			} else {
				Log.d("Adapter", "La lista si es nula");
			}
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
			ImageView im = (ImageView) view.findViewById(R.id.imv);
			Log.d("IMG", mArray.get(position).getFotoUrl());
			im.setImageBitmap(getBitmapFromURL(mArray.get(position).getFotoUrl()));
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
