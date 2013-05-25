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

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class YncatMapsActivity extends MapActivity{

	public class MyPoint {
		public GeoPoint point;
		public int color;
	}
	public MapView mapview = null;
	public static Handler mHandler;	
	public MapController mcontrol = null;
	private Intent mService = null;
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
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();	
		mapview.setClickable(true);
		mapview.setBuiltInZoomControls(true);
		mcontrol = mapview.getController();

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
				mColor = Preferences.loadPreference(YncatConstants.PREF_COLOR, Color.BLUE);
				mZoom = Preferences.loadPreference(YncatConstants.PREF_ZOOM, 12);
				mcontrol.setZoom(mZoom);
			
				Bundle data = msg.getData();
				GeoPoint loc = new GeoPoint((int) (data.getDouble(
						YncatConstants.LATITUDE)*YncatConstants.FACTOR_LOC_TO_GEO), 
								(int) (data.getDouble(YncatConstants.LONGITUDE)*YncatConstants.FACTOR_LOC_TO_GEO));
				mcontrol.animateTo(loc);
				MyPoint mp = new MyPoint();
				mp.point = loc;
				mp.color = mColor;
				listPoints.add(mp);
				om.insertPoint(mp);
				capas.add(om);
				mapview.postInvalidate();
				return false;
			}
		});
		if(mService == null)
		{
			mService = new Intent(getApplicationContext(), YncatServiceLocalizacion.class);		
			startService(mService);		
		} else
			YncatServiceLocalizacion.setHandler(mHandler);

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
	}
	
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putIntArray(YncatConstants.LATITUDES, om.getPointsLats());
		outState.putIntArray(YncatConstants.LONGITUDES, om.getPointsLongs());
		outState.putIntArray(YncatConstants.COLORS, om.getColors());
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		restorePoints(savedInstanceState.getIntArray(YncatConstants.LATITUDES), 
					savedInstanceState.getIntArray(YncatConstants.LONGITUDES),
				savedInstanceState.getIntArray(YncatConstants.COLORS));
	}
	
	private void restorePoints(int[] lats, int[] longs, int[] colors)
	{
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
		
		private ArrayList<MyPoint> points = new ArrayList<YncatMapsActivity.MyPoint>();
		
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
			Point centro = new Point();
			projection.toPixels(geo, centro);

			Paint p = new Paint();
			p.setColor(col);
			
			can.drawCircle(centro.x,centro.y, 3, p);
		}
	}		
}
