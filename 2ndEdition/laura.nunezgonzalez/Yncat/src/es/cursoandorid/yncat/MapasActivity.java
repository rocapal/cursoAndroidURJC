package es.cursoandorid.yncat;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
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
	

	public static Bitmap mbitmap;
	public MapView mapview = null;
	public static Handler mHandler;	
	public MapController mcontrol = null;
	private Intent servicio = null;
	private MyOverlay om;

	
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
		mcontrol.setZoom(9);
		final List<Overlay> capas = mapview.getOverlays();
		capas.clear();
		mapview.postInvalidate();
		mHandler = new Handler(new Callback(){
			
		public boolean handleMessage(Message msg) {
				Bundle data = msg.getData();
				GeoPoint loc = new GeoPoint((int) (data.getDouble("Latitud")*1000000), (int) (data.getDouble("Longitud")*1000000));
				mcontrol.animateTo(loc);
				om.insertarPunto(loc);
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
		super.onSaveInstanceState(outState);
		Log.d("SALVANDO", "Salvamos estos puntos: " + String.valueOf(
				om.getPointsLats().length));
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		restorePoints(savedInstanceState.getIntArray("Latitudes"), savedInstanceState.getIntArray("Longitudes"));
	}
	
	private void restorePoints(int[] lats, int[] longs)
	{
		Log.d("RESTORE", "Recuperamos " + String.valueOf(lats.length));
		for(int i=0; i < lats.length; i++)
		{
			GeoPoint geo = new GeoPoint(lats[i], longs[i]);
			om.insertarPunto(geo);
		}
	}
	
	
	
	public class MyOverlay extends Overlay{
		
		private ArrayList<GeoPoint> puntos = new ArrayList<GeoPoint>();
	
	    public void insertarPunto(GeoPoint loc){
			puntos.add(loc);
		}
		
		
		public void insertsPoints(ArrayList<GeoPoint> lis)
		{
			puntos.addAll(lis);
		}
		
		public int[] getPointsLats(){
			int[] lista = new int[puntos.size()];
			for(int i=0; i < puntos.size(); i++)
			{
				lista[i] = puntos.get(i).getLatitudeE6();
			}
			return lista;
		}
		
		public int[] getPointsLongs(){
			int[] lista = new int[puntos.size()];
			for(int i=0; i < puntos.size(); i++)
			{
				lista[i] = puntos.get(i).getLongitudeE6();
			}
			return lista;
		}
		
		
		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) 
		{
			if (shadow == false) 
			{
				for(int i=0; i < puntos.size(); i++)
				{	
					dibujarEnMapa(canvas, mapView, puntos.get(i));
				}	
			}
		}

		private void dibujarEnMapa(Canvas can, MapView mv, GeoPoint geo)
		{
			Projection projection = mv.getProjection();

			Point centro = new Point();
			projection.toPixels(geo, centro);

			//Definimos el pincel de dibujo
			Paint p = new Paint();
			p.setColor(Color.BLUE);
			
/*			Bitmap bm = BitmapFactory.decodeResource(
					mv.getResources(), 
					R.drawable.ic_launcher);
			
			can.drawBitmap(bm, centro.x - bm.getWidth(), 
					              centro.y - bm.getHeight(), p);*/
			can.drawCircle(centro.x,centro.y, 3, p);
			
		}
	}		
}
