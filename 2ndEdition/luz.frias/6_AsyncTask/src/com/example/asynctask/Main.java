package com.example.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;

public class Main extends Activity {
	
	private ProgressDialog mProg;
	private MyAsyncTask mTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mTask = new MyAsyncTask(this);
		mTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
		
		private Activity context;
		private int TIME_TO_TAKE = 10; //seg
		
		public MyAsyncTask(Activity context) {
			this.context = context;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProg = new ProgressDialog(context);
		    mProg.setTitle(context.getResources().getString(R.string.progress_text_title));
		    mProg.setMessage(context.getResources().getString(R.string.progress_text_desc));       
		    mProg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		    mProg.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			for (int i = 1; i <= TIME_TO_TAKE; i++) {
				try {
					Thread.sleep(TIME_TO_TAKE*1000/10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				publishProgress(i);
			}
			return null;
		}
		
	     protected void onProgressUpdate(Integer... progress) {
	    	 mProg.setProgress(progress[0]*10);
	     }
		
		@Override
		protected void onPostExecute(Void arg) {
			super.onPostExecute(arg);
			if (mProg.isShowing())
				mProg.dismiss();
		}
		
	}

}
