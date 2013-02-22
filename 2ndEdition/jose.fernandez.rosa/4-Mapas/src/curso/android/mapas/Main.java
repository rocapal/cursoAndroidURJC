package curso.android.mapas;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class Main extends MapActivity {

	static public MapView mView;
	static public MapController mControl;
	static public LocationManager mLocManager;
	static public MyLocationListener mLocListener;
	static public ProgressDialog mPd = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// mPd = ProgressDialog.show(this,
		// getString(R.string.mPdTitle),getString(R.string.mPdText));
		MyAsyncTask task = new MyAsyncTask();
		task.execute(null,null,null);
		mView = (MapView) findViewById(R.id.myMapView);
		mView.setBuiltInZoomControls(true);
		mView.setClickable(true);

		setLocationListener();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mLocManager.removeUpdates(mLocListener);

	}

	private void setLocationListener() {
		mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		mLocListener = new MyLocationListener(this);
		mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
				15, mLocListener);
		mControl = mView.getController();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
		ProgressDialog mPd=null;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mPd = ProgressDialog.show(Main.this,
					Main.this.getString(R.string.mPdTitle),
					Main.this.getString(R.string.mPdText));

		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mPd.dismiss();
		}
	}

}
