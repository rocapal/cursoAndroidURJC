package es.bmuma.tacografo;


import java.util.ArrayList;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class TimeService extends Service
{
	private static boolean savedState = false;
	private static boolean savedDriving = false;
	private static int savedType = 0;
	//Types in String
	private static String DISPOSE = "dispose";
	private static String RELAX = "relax";
	private static String DRIVING = "driving";
	private static String WORK = "work";
	//Maximal Times
	private static final Date maxFirstPeriod = new Date(16200000);
	private static final Date maxSecondPeriod= new Date(32400000);
	private static final Date minRelaxTime = new Date(2700000);
	//The type of conduction
	private static Integer type;
	//Conduction Times
	private static Date drivingDate;
	private static Date relaxDate;
	//Times for the list
	private static DesglossedTime timeDriving;
	private static DesglossedTime timeRelax;
	private static DesglossedTime timeDispose;
	private static DesglossedTime timeWork;
	
	private static String myDate;
	
	private static ArrayList<DesglossedTime> timeTotalDesgloss;
	
	private final String TAG = getClass().getSimpleName();
	private static ITimeService Iservice = null;
	private static Boolean run = false;
	private static Boolean primero = true;
	private static Boolean segundo = false;
	private static Boolean tercero = false;
	
	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}

	@Override
    public void onCreate() {
		super.onCreate();
		timeDriving = new DesglossedTime(new Date(0), new Date(0), new Date(0), null);
		timeDispose = new DesglossedTime(new Date(0), new Date(0), new Date(0), null);
		timeRelax = new DesglossedTime(new Date(0), new Date(0), new Date(0), null);
		timeWork = new DesglossedTime(new Date(0), new Date(0), new Date(0), null);
		timeTotalDesgloss = new ArrayList<DesglossedTime>();
		drivingDate = new Date(0);
		relaxDate = new Date(0);
		type = 0;
		run = true;
        initService();
        
                
    }

	public static void setType(int tipo){
		updateFinalTimes();
		type = tipo;
		if (type == 1)
			timeDriving = new DesglossedTime(new Date(), new Date(0), new Date(0), DRIVING);
		if (type == 2)
			timeDispose = new DesglossedTime(new Date(), new Date(0), new Date(0), DISPOSE);
		if (type == 3)
			timeRelax = new DesglossedTime(new Date(), new Date(0), new Date(0), RELAX);
		if (type == 4)
			timeWork = new DesglossedTime(new Date(), new Date(0), new Date(0), WORK);
		
	}
	
	private static void updateFinalTimes(){
		switch(type){
		case 1:
			timeDriving.setTimeFin(new Date());
			timeDriving.setDuration(timeDriving.getTimeInit(), timeDriving.getTimeFin());
			addTimes();
			timeTotalDesgloss = DesglossedTime.addElement(timeTotalDesgloss, timeDriving);
			break;
		case 2:
			timeDispose.setTimeFin(new Date());
			timeDispose.setDuration(timeDispose.getTimeInit(), timeDispose.getTimeFin());
			timeTotalDesgloss = DesglossedTime.addElement(timeTotalDesgloss, timeDispose);
			break;
		case 3:
			timeRelax.setTimeFin(new Date());
			timeRelax.setDuration(timeRelax.getTimeInit(), timeRelax.getTimeFin());
			addTimes();
			timeTotalDesgloss = DesglossedTime.addElement(timeTotalDesgloss, timeRelax);
			break;
		case 4:
			timeWork.setTimeFin(new Date());
			timeWork.setDuration(timeWork.getTimeInit(), timeWork.getTimeFin());
			timeTotalDesgloss = DesglossedTime.addElement(timeTotalDesgloss, timeWork);
			break;
		
		}
		
	}
		
	private static void addTimes() {
		int time = 0;
		Integer[] times;
		switch(type){
			case 1:
				time = (int) (timeDriving.getDuracion().getTime()
					+drivingDate.getTime());
				times = DesglossedTime.getTimers(time);
				updateTime(times,1);
				break;
			case 3:
				time = (int) (timeRelax.getDuracion().getTime()
					+relaxDate.getTime());
				times = DesglossedTime.getTimers(time);
				updateTime(times,3);
				break;
		}
	}


	private static void updateTime(Integer[] myTimes, int i) {
		switch (i){
		case 1:
			Integer[] myDriving = DesglossedTime.getTimers((int)drivingDate.getTime());
			drivingDate.setSeconds(myTimes[0] + myDriving[0]);
			drivingDate.setMinutes(myTimes[1] + myDriving[1]);
			drivingDate.setHours(myTimes[2] + myDriving[2]);
			break;
		case 3:
			Integer[] myRelax = DesglossedTime.getTimers((int)relaxDate.getTime());
			relaxDate.setSeconds(myTimes[0] + myRelax[0]);
			relaxDate.setMinutes(myTimes[1] + myRelax[1]);
			relaxDate.setHours(myTimes[2] + myRelax[2]);
			break;
		}
		
	}

	public static void regListener (ITimeService iTimeService){
		Iservice = iTimeService;
	}
	
	private void initService () {
		Counter myHandler = new Counter();
		myHandler.sendMessageDelayed(new Message(), 5000);			
	}


	
	@Override
    public void onDestroy() {
		super.onDestroy();
		updateFinalTimes();
		run = false;
		Log.d(TAG, "Stop service");				
	}	
	
	
	private class Counter extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if (Iservice != null){
				getDrivingPeriod();
				Date actual = new Date();
				long maximo = 0;
				if(primero){
					maximo = maxFirstPeriod.getTime();
				}else if (segundo){
					maximo = maxSecondPeriod.getTime();
				}
				long tiempo1 = maximo-drivingDate.getTime();
				Date periodo = new Date(0);
				
				switch (type){
				case 1 : 
					int tiempo2 = (int)(actual.getTime() - timeDriving.getTimeInit().getTime());
					Integer[] tiempo = DesglossedTime.getTimers(tiempo2);
					periodo.setSeconds(tiempo[0]);
					periodo.setMinutes(tiempo[1]);
					periodo.setHours(tiempo[2]);
					myDate = DesglossedTime.getStringTime(tiempo1-periodo.getTime());
					break;
				case 0 :
					myDate = " ";
					break;
				default :
					myDate = DesglossedTime.getStringTime(tiempo1);
				}			
				
				if(tercero)
					myDate = getString(R.string.alerta);
					
				Iservice.updateName();
				Iservice.updateMat();
				Iservice.updateTime(myDate);
			}
			if (run)
				initService();
		}
		
		private void getDrivingPeriod(){
			primero = false;
			segundo = false;
			tercero = false;
			if(drivingDate.getTime()< maxFirstPeriod.getTime())
				primero = true;
				
			else{
				if(relaxDate.getTime()> minRelaxTime.getTime()){
					segundo = true;
					if(drivingDate.getTime()> maxSecondPeriod.getTime())
						tercero = true;
				}else
					tercero = true;
			}	
		}
	}

	
	public static ArrayList<DesglossedTime> getList(int type){
		ArrayList<DesglossedTime> myArrayList = new ArrayList<DesglossedTime>();
		switch(type){
		case 0: 
			myArrayList = timeTotalDesgloss;
			break;
		case 1:
			myArrayList = DesglossedTime.findByType(timeTotalDesgloss, DRIVING);
			break;
		case 2:
			myArrayList = DesglossedTime.findByType(timeTotalDesgloss, DISPOSE);
			break;
		case 3:
			myArrayList = DesglossedTime.findByType(timeTotalDesgloss, RELAX);
			break;
		case 4:
			myArrayList = DesglossedTime.findByType(timeTotalDesgloss, WORK);
			break;
		}
		return myArrayList;
	}
	
	
	public static void setSavedState(boolean mainState){
		savedState = mainState;
	}
	
	public static boolean getSavedState(){
		return savedState;
	}
	
	public static void setSavedDriving(boolean mainSriving){
		savedDriving = mainSriving;
	}
	
	public static boolean getSavedDriving(){
		return savedDriving;
	}

	public static void setSavedType(int mainType){
		savedType= mainType;
	}
	
	public static int getSavedType(){
		return savedType;
	}
	
}



	


