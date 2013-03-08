package es.curso.android.apps;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends ListActivity {
	
	public final String URL_JSON = "http://rest.libregeosocial.org/social/layer/560/search/?search=&latitude=40.2855&longitude=-3.8222&radius=1.0&category=0&elems=20&page=1&format=JSON";
	public Context mContext;
	ArrayList<PanoramioItem> mArray = new ArrayList<PanoramioItem>();
	private MiAdaptador miAdaptador = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mContext = this;//getApplicationContext();
	
		new DownloadJSON().execute();
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class DownloadJSON extends AsyncTask<Void, Void, Void> {

		ProgressDialog pd;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(mContext, "Espere ...", "Descargando JSON...");
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String str = doGetPetition(URL_JSON);
			
			JSONParser jsonP = new JSONParser(str);
			mArray = jsonP.parser();
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (pd.isShowing())
				pd.dismiss();
			Toast.makeText(mContext, "Numero elementos: " + String.valueOf(mArray.size()), 
					Toast.LENGTH_SHORT).show();
			
			miAdaptador = new MiAdaptador(mContext);
			setListAdapter(miAdaptador);
		}
		
		// Método genérico
		protected String doGetPetition(String mUrl) {
			try {
				DefaultHttpClient httpclient = new DefaultHttpClient();
				HttpGet httpGet = null;
				httpGet = new HttpGet(mUrl);
				HttpResponse response = httpclient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity);
				Log.d("doGet", str);
				return str;
			}
			catch (IOException e) {
				Log.e("doGet",e.getMessage());
				return null;
			}
		}
	}
	
	

		 
		
	private class MiAdaptador extends BaseAdapter {

		private Context miContexto;

		public MiAdaptador(Context c) {
			miContexto = c;
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
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;

			LayoutInflater inflater = (LayoutInflater) miContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view =inflater.inflate(R.layout.panoramio, null);

			TextView tvNombre = (TextView) view.findViewById(R.id.tvNombre);
			tvNombre.setText(mArray.get(position).getName());


			ImageView img = (ImageView) view.findViewById(R.id.imImagen);
			img.setImageBitmap(
					getBitmapFromUrl(mArray.get(position).getPhotoUrl()));
			return view;
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		//super.onListItemClick(l, v, position, id);

		Toast.makeText(this, "Posicion: " + String.valueOf(position), 
				Toast.LENGTH_SHORT).show();
	}

}
