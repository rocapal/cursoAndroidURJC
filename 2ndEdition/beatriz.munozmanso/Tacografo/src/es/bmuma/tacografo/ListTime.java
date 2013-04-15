package es.bmuma.tacografo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class ListTime extends ListActivity{
	
	private static String DISPOSE = "dispose";
	private static String RELAX = "relax";
	private static String DRIVING = "driving";
	private static String WORK = "work";
	
	public class Item 
	{
		public String mTime;
		public String mDuraction;
		public Integer mImage;
		public String myType;
	}
	
	
	private static ArrayList<DesglossedTime> myTimes;
	static ArrayList<Item> myList = new ArrayList<Item>();
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		int type = getIntent().getExtras().getInt("type", 0);
		myTimes = TimeService.getList(type);
		TextView Ttype = (TextView) findViewById(R.id.tituloLista);
		Ttype.setText(getTypeList(type));
		setDataPrintList();
		MyAdapter mAdapter = new MyAdapter(this);
		setListAdapter(mAdapter);
	}
	
	
	
    protected void onListItemClick(ListView l, View v, int position, long id) 
	{
    	Intent browserIntent = new Intent(getBaseContext(), ListTime.class);
    	int type = getTypeList(myList.get(position).myType);
		browserIntent.putExtra("type", type);
		startActivity(browserIntent);
    	   	
    	
    	
	}
	
	private int getTypeList(String type) {
		if(type.equals(DRIVING)){
			return 1;
		}else if(type.equals(DISPOSE)){
			return 2;
		}else if(type.equals(RELAX)){
			return 3; 
		}else if(type.equals(WORK)){
			return 4;
		}
		return 0;
	}

	private String getTypeList(int type) {
		switch(type){
		case 0: 
			return getString(R.string.total);
		case 1:
			return getString(R.string.timeof) + " " + getString(R.string.driving);
		case 2:
			return getString(R.string.timeof) + " " + getString(R.string.dispose);
		case 3:
			return getString(R.string.timeof) + " " + getString(R.string.relax);
		case 4:
			return getString(R.string.timeof) + " " + getString(R.string.work);
		};
		return getString(R.string.total);
	}
	
	private void setDataPrintList() {
		myList.clear();
		
		//Populate the list with the date of the other one
		try{
			for (DesglossedTime dess: myTimes){
				Item item = new Item();
				item.mDuraction = getString(R.string.duracionItem) + " " + DesglossedTime.getStringTime(
						dess.getDuracion().getTime());
				item.mTime = getString(R.string.timeItem) + " " + getHoraActual(dess.getTimeInit());
				item.mImage = getImage(dess.getType());
				item.myType = dess.getType();
				myList.add(item);
			}
		}catch(NullPointerException e){
			Item item = new Item();
			item.mDuraction = "0";
			item.mTime = "0";
			item.mImage = getImage(DRIVING);
			
		}
	}
		
	@SuppressLint("SimpleDateFormat")
	private static String getHoraActual(Date tiempo) {
	      SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
	      return formateador.format(tiempo);
		
	}
	private Integer getImage(String type) {
		if(type.equals(DRIVING)){
			return R.drawable.conduccion;
		}else if(type.equals(DISPOSE)){
			return R.drawable.disponibilidad;
		}else if(type.equals(RELAX)){
			return R.drawable.descanso; 
		}else if(type.equals(WORK)){
			return R.drawable.trabajo;
		}
		return null;
	}
	
}
