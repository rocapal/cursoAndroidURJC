package es.android.barraporcentaje;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private ProgressBar pbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pbar = (ProgressBar) findViewById(R.id.pb1);
		MyAsyncTask task = new MyAsyncTask();
		task.execute(null,null,null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


	public class MyAsyncTask extends AsyncTask<Void, Void, Void>
	{

		ProgressDialog pd = null;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(Void... arg0) {
			int cuenta = 0;
			while (cuenta < 100) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cuenta = cuenta + 10;
				pbar.setProgress(cuenta);
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

	}	
}
