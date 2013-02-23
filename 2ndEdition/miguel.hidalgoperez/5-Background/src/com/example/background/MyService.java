package com.example.background;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	int c;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void initService (){
		
	}
	
	private class DoSomething extends Handler {
		@Override
		public void handleMessage(Message msg) {
			
			c = c + 3;
			initService();
			
			/*if (Iservice != null){
				time = time + 1;
				String mydate = java.text.DateFormat.getDateTimeInstance().format(date);
				Iservice.updateTime(mydate);
				Iservice.updateTemp(String.valueOf(time));
				if (run)
					initService();
			} else {
				Toast.makeText(getApplicationContext(), "Message", Toast.LENGTH_SHORT).show();
				if (run)
					initService();
			}*/
		}
	}	
}
