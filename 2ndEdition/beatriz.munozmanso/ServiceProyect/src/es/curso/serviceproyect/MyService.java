package es.curso.serviceproyect;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	private final String TAG = getClass().getSimpleName();
	int cont = 0;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void initService(){
		DoSomething myHandler = new DoSomething();
		myHandler.sendMessageDelayed(new Message(), 3000);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "Arrancado el service");
		Toast.makeText(getApplicationContext(), "Start Service", Toast.LENGTH_SHORT).show();
		initService();
		
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG,"Stopping the service");
		Toast.makeText(getApplicationContext(), "Stop Service", Toast.LENGTH_SHORT).show();
		
	}


	public class DoSomething extends Handler{
		
		@Override
		public void handleMessage(Message msg) {
			
			cont = cont +3 ; 
		
		}
	}
}