
package com.example.mapas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class TareaAsincrona extends AsyncTask<Void, Void, Void> {
	
	private Activity activity;
	private ProgressDialog progressDialog;
	
	
	public TareaAsincrona (Activity myActivity) {
		activity = myActivity;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = ProgressDialog.show
				(activity, activity.getString(R.string.title_progress_dialog), activity.getString(R.string.message_progress_dialog));

	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		progressDialog.dismiss();
	}
	
	
	@Override
	protected Void doInBackground(Void... params) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}