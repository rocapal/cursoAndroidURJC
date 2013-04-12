package es.curso.serviceApp;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("Arrancado el service", null);
		//Button bTimerTask = (Button) this.findViewById(R.id.boton);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("Stopping the service", null);
		
	}

}
