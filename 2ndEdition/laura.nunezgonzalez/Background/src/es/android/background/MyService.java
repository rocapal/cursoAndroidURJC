package es.android.background;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MyService extends Service{

	private int mTemp;
	public Handler mHandler;
	
	public MyService(){
		super();
		Log.d("Servicio", "Servicio iniciado parapapam");
		this.mTemp = 0;
		this.mHandler = MainActivity.mHandler;

	}
	
	public void HacerEsto(){
		DoSomething myHandler = new DoSomething();
		myHandler.sendMessageDelayed(new Message(), 3000);
		Message msg = new Message();
		this.mHandler.sendMessage(msg);
		((Activity) getApplicationContext()).setContentView(R.layout.activity_main);
		TextView tv1 = (TextView) ((Activity) getApplicationContext()).findViewById(R.id.tv1);
		Log.d("SErvice", "llega hasta aqui");
		tv1.setText("otro");
	}
	
	private class DoSomething extends Handler {
		
		public void handleMessage(Message msg) {
			mTemp = mTemp + 3;
			HacerEsto();
		}
	}
	
	public String getTexto(){
		return String.valueOf(mTemp);
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
