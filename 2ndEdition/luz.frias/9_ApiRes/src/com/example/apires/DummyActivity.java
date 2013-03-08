package com.example.apires;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

public class DummyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dummy);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
		mNotificationManager.cancel(Main.NOTIFICATION_ID);
	}
}
