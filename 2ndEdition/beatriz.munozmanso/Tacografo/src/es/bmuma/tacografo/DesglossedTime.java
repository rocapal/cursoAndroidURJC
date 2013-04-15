package es.bmuma.tacografo;

import java.util.ArrayList;
import java.util.Date;

public class DesglossedTime {

	
	private String type;
	private Date duration;
	private Date timeInit;
	private Date timeFin;
	
	/**
	 * Constructor of the class
	 * @param fechaInic
	 * @param fechaFin
	 * @param interv
	 * @param tipo
	 */
	public DesglossedTime(Date fechaInic, Date fechaFin, Date interv, String tipo){
		this.timeInit = fechaInic;
		this.timeFin = fechaFin;
		this.duration = interv;
		this.type = tipo;
		
	}
	/**
	 * Getter of Duration
	 * @return
	 */
	public Date getDuracion(){
		return this.duration;
	}
	
	/**
	 * Getter of initial Time
	 * @return
	 */
	public Date getTimeInit(){
		return this.timeInit;
	}
	
	/**
	 * Getter of final time
	 * @return
	 */
	public Date getTimeFin(){
		return this.timeFin;
	}
	
	/**
	 * Getter of type
	 * @return
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * Setter of Duration of the period
	 * @param interv
	 */
	public void setDuration (Date inic, Date fin){
		
		this.duration = resolveInterval(inic, fin);
	}
	
	/**
	 * Setter of initial time
	 * @param fecha
	 */
	public void setTimeInic (Date fecha){
		this.timeInit = fecha;
	}
	
	/**
	 * Setter of end time
	 * @param fecha
	 */
	public void setTimeFin (Date fecha){
		this.timeFin = fecha;
	}
	
	/**
	 * Setter of type
	 * @param tipo
	 */
	public void setType (String tipo){
		this.type = tipo;
	}
	
	/**
	 * Add a new element into the list
	 * @param lista
	 * @param element
	 * @return list
	 */
	public static ArrayList<DesglossedTime> addElement (ArrayList<DesglossedTime> lista, 
			DesglossedTime element){
		if(lista.isEmpty()){
			lista = new ArrayList<DesglossedTime>();
		}
		lista.add(element);
		return lista;
		
	}
	
	
	/**
	 * Create a new list with the elements from a specified type
	 * @param lista
	 * @param tipo
	 * @return newList
	 */
	public static ArrayList<DesglossedTime> findByType(ArrayList<DesglossedTime> lista, String tipo){
		
		if(lista.isEmpty()){
			return lista;
		}
		ArrayList<DesglossedTime> newList = new ArrayList<DesglossedTime>();
		for(DesglossedTime elem: lista){
			if(elem.getType().equals(tipo)){
				newList.add(elem);
			}
		}
		return newList;
		
	}
	
	/**
	 * Convert Date to String. Only it's
	 * necessary for seconds, minutes and hours.
	 * @param time
	 */
	public static String stringTime(Date time){
		String timeString = time.getHours() + ":" + time.getMinutes()
				+ ":" + time.getSeconds();
		return timeString;
	}
	
	/**
	 * figure out the time between two Dates
	 * @param init
	 * @param fin
	 * @return Date Interval
	 */
	public static Date resolveInterval(Date init, Date fin){
		Date interval = new Date(0);
		int diffMils = (int) (fin.getTime() - init.getTime());
		Integer[] myTime = getTimers(diffMils);
		interval.setSeconds(myTime[0]);
		interval.setMinutes(myTime[1]);
		interval.setHours(myTime[2]);
		
		return interval;
		
	}
	
	public static Integer[] getTimers(int time){
		Integer segs = time / 1000;
		Integer hours = segs / 3600;
		segs -= hours * 3600;
		Integer minutes = segs / 60;
		segs -= minutes * 60;
		Integer[] myTimes = new Integer[3];
		myTimes[0] = segs;
		myTimes[1] = minutes;
		myTimes[2] = hours;
		return myTimes;
		
	}
	public static String getStringTime(long timeDiff) {
		Integer diffMils = (int) timeDiff;
		Integer[] times = DesglossedTime.getTimers(diffMils);
		String time = times[2].toString() + ":" + times[1].toString() + ":" 
				+ times[0].toString();
		
		return time;
	}
	
	
	
	/**
	 * Give the necessity of start an Alarm
	 * @param init
	 * @param fin
	 * @return the state
	 */
	public Boolean isAlarmNecesary(Date init, Date fin){
		Date diff = resolveInterval(init, fin);
		if((diff.getHours())==0 && (diff.getMinutes()<15)){
			return true;
		}
		return false;
	}
	
	
}
