package es.android.servicios;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service implements Runnable {
	private final String TAG = getClass().getSimpleName();
	private Handler mLoopHandler=new Handler();
	private static ArrayList<IServiceListener> mSLs = new ArrayList<IServiceListener>();
	private boolean mRunning=false;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();		
		mRunning = true;
		Log.d(TAG, "Init service");
		Toast.makeText(Main.context, "Init Service", Toast.LENGTH_SHORT).show();
		mLoopHandler.postDelayed(this, 3000);		
	}
	
	public static void regIServiceListener( IServiceListener sl){
		mSLs.add(sl);
	}

	@Override
	public void run() {
		if (!mRunning) return;
		
		String today = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		for (IServiceListener sl:mSLs) sl.updateMessage(today);
		if (mLoopHandler!=null) mLoopHandler.postDelayed(this, 3000);
		
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mRunning=false;
		Log.d(TAG, "Stop service");
		Toast.makeText(Main.context, "Stop Service", Toast.LENGTH_SHORT).show();
	}
	

	
}
