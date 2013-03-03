package com.example.multitareaandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<Void, Integer, Integer> {

	public static final long ONE_SECOND = 1000;
	private ProgressDialog progressDialog;
	private Context contexto;
	
	
	public MyAsyncTask(Context myContext) {
		contexto = myContext;
	}

	@Override
	protected void onPreExecute() {
		progressDialog = new ProgressDialog(contexto);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setTitle("ProgressDialog");
		progressDialog.setMessage("progressDialog AsyncTask");
		progressDialog.show();		
		Log.d(MainActivity.TAG, "doPreExecute");
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		Log.d(MainActivity.TAG, "doOnProgressUpdate " + values[0]);
		progressDialog.setProgress(values[0]);
	}

	@Override
	protected Integer doInBackground(Void... arg0) {
		Log.d(MainActivity.TAG, "doInBackground");
		this.download(0);
		/*
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(ONE_SECOND);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			publishProgress(i);
		}*/
		return -1;
	}

	
	
	private void download(int i) {
		if (i<=100) {
			Message msg = new Message();
			msg.what = i;
			myHandler.sendMessageDelayed(msg, 100);
			publishProgress(i);
		} else {
			onPostExecute(1);
		}
		
	}

	@Override
	protected void onPostExecute(Integer result) {
		if (result == 1) {
			progressDialog.dismiss();
		}
		Log.d(MainActivity.TAG, "doPostExecute");
	}

	Handler myHandler = new Handler(new Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			download(msg.what +1);
			return true;
		}
	});	
}
