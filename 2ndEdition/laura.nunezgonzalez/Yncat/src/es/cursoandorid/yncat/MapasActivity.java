package es.cursoandorid.yncat;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapasActivity extends MapActivity{
	

	public class MyPoint {
		public GeoPoint point;
		public int color;
	}
	public MapView mapview = null;
	public static Handler mHandler;	
	public MapController mcontrol = null;
	private Intent servicio = null;
	private MyOverlay om;
	private static ArrayList<MyPoint> listPoints;
	private int mColor;
	private int mZoom;
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.mapas);
		mapview = (MapView) findViewById(R.id.myMapView);
		om = new MyOverlay();	
		Log.d("MAP", "create");
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();	
		mapview.setClickable(true);
		mapview.setBuiltInZoomControls(true);
		mcontrol = mapview.getController();
		mColor = Preferences.loadPreference(
				MainActivity.mContexto.getResources().getString(R.string.pref_color), Color.BLUE);
		mZoom = Preferences.loadPreference(
				MainActivity.mContexto.getResources().getString(R.string.pref_zoom), 12);
		mcontrol.setZoom(mZoom);
		final List<Overlay> capas = mapview.getOverlays();
		capas.clear();
		if(listPoints == null)
			listPoints = new ArrayList<MyPoint>();
		else
			om.insertsPoints(listPoints);
		capas.add(om);
		mapview.postInvalidate();

		mHandler = new Handler(new Callback(){
			
		public boolean handleMessage(Message msg) {
				Bundle data = msg.getData();
				GeoPoint loc = new GeoPoint((int) (data.getDouble("Latitud")*1000000), (int) (data.getDouble("Longitud")*1000000));
				mcontrol.animateTo(loc);
				MyPoint mp = new MyPoint();
				mp.point = loc;
				mp.color = mColor;
				listPoints.add(mp);
				om.insertPoint(mp);
				capas.add(om);
				mapview.postInvalidate();
				Toast.makeText(MainActivity.mContexto, MainActivity.mContexto.getString(
							R.string.toast_msg_location) + String.valueOf(msg.arg1),
							Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		if(servicio == null)
		{
			servicio = new Intent(getApplicationContext(), ServicioLocalizacion.class);		
			startService(servicio);		
		} else
			ServicioLocalizacion.setHandler(mHandler);

	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("MAPAS", "Se han pausado los mapas");
	}
	
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("MAPAS", "Se han parado los mapas");
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putIntArray("Latitudes", om.getPointsLats());
		outState.putIntArray("Longitudes", om.getPointsLongs());
		outState.putIntArray("Colors", om.getColors());
		super.onSaveInstanceState(outState);
		Log.d("SALVANDO", "Salvamos estos puntos: " + String.valueOf(
				om.getPointsLats().length));
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		restorePoints(savedInstanceState.getIntArray("Latitudes"), savedInstanceState.getIntArray("Longitudes"),
				savedInstanceState.getIntArray("Colors"));
		Log.d("RESTORE", "Recuperados " +  String.valueOf(savedInstanceState.getInt("Latitudes")));
	}
	
	private void restorePoints(int[] lats, int[] longs, int[] colors)
	{
		Log.d("RESTORE", "Recuperamos " + String.valueOf(lats.length));
		for(int i=0; i < lats.length; i++)
		{
			GeoPoint geo = new GeoPoint(lats[i], longs[i]);
			MyPoint mp = new MyPoint();
			mp.point = geo;
			mp.color = colors[i];
			om.insertPoint(mp);
		}
	}
	
	
	
	public class MyOverlay extends Overlay{
		
		private ArrayList<MyPoint> points = new ArrayList<MapasActivity.MyPoint>();
		
	    public int[] getColors(){
			int[] lista = new int[points.size()];
			for(int i=0; i < points.size(); i++)
			{
				lista[i] = points.get(i).color;
			}
			return lista;
	    }
	    
	    public void insertPoint(MyPoint mp){
	    	points.add(mp);
	    }
	    public void setColors(){
	    	
	    }
		
		public void insertsPoints(ArrayList<MyPoint> lis)
		{
			points.addAll(lis);
		}
		
		public int[] getPointsLats(){
			int[] lista = new int[points.size()];
			for(int i=0; i < points.size(); i++)
			{
				lista[i] = points.get(i).point.getLatitudeE6();
			}
			return lista;
		}
		
		public int[] getPointsLongs(){
			int[] lista = new  int[points.size()];
			for(int i=0; i < points.size(); i++)
			{
				lista[i] = points.get(i).point.getLongitudeE6();
			}
			return lista;
		}
		
		
		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) 
		{
			if (shadow == false) 
			{
				for(int i=0; i < points.size(); i++)
				{	
					drawInMapColor(canvas, mapView, points.get(i).point, points.get(i).color);
				}	
			}
		}
		
		private void drawInMapColor(Canvas can, MapView mv, GeoPoint geo, int col)
		{
			Projection projection = mv.getProjection();
			Log.d("Coordenadas draw", String.valueOf(geo.getLatitudeE6()) + ", " +String.valueOf(geo.getLongitudeE6()));
			Point centro = new Point();
			projection.toPixels(geo, centro);
			Log.d("Color puesto", String.valueOf(col));
			Log.d("Color azul", String.valueOf(Color.BLUE));
			//Definimos el pincel de dibujo
			Paint p = new Paint();
			p.setColor(col);
			
			can.drawCircle(centro.x,centro.y, 3, p);
			
		}
	}		
}
