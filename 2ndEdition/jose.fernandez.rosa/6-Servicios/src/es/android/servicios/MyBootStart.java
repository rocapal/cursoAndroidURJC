package es.android.servicios;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBootStart extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent intent1 = new Intent(context, MyService.class);
		context.startService(intent1);		
	}

}
