package es.curso.android.apps;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AdvancedList extends ListActivity {

	public class Elemento {
		public String servidor;
		public String descripcion;
		public Integer imagen;
	}
	
	private static ArrayList<Elemento> miArray = new ArrayList<Elemento>();
	private MiAdaptador miAdaptador = null; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		rellenarDatos();
		
		miAdaptador = new MiAdaptador(this);
		setListAdapter(miAdaptador);
		
		
	}
	
	private void rellenarDatos() {
		
		miArray.clear();
		
		Elemento e = new Elemento();
		e.servidor = "irc.servidor1.net";
		e.descripcion = "Este es el servidor 1 con estos detalles";
		e.imagen = android.R.drawable.btn_plus;
		miArray.add(e);
		
		Elemento e2 = new Elemento();
		e2.servidor = "irc.servidor2.net";
		e2.descripcion = "Este es el servidor 2 con estos detalles";
		e2.imagen = android.R.drawable.btn_plus;
		miArray.add(e2);
		
		Elemento e3 = new Elemento();
		e3.servidor = "irc.servidor3.net";
		e3.descripcion = "Este es el servidor 3 con estos detalles";
		e3.imagen = android.R.drawable.btn_plus;
		miArray.add(e3);
		
		//miArray.addAll(miArray);
		
	}
	
	private static class MiAdaptador extends BaseAdapter {
		
		private Context miContexto;

		public MiAdaptador(Context c) {
			miContexto = c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return miArray.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return miArray.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			
			LayoutInflater inflater = (LayoutInflater) miContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view =inflater.inflate(R.layout.item, null);
			
			ImageView img = (ImageView) view.findViewById(R.id.imagen);
			img.setImageDrawable(miContexto.getResources().getDrawable(miArray.get(position).imagen));
			
			TextView tvServidor = (TextView) view.findViewById(R.id.servidor);
			tvServidor.setText(miArray.get(position).servidor);
			
			TextView tvDescripcion = (TextView) view.findViewById(R.id.descripcion);
			tvDescripcion.setText(miArray.get(position).descripcion);
			
			return view;
			
		}
		
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		//super.onListItemClick(l, v, position, id);
		
		Toast.makeText(this, "Posicion: " + String.valueOf(position), 
				Toast.LENGTH_SHORT).show();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item, menu);
		return true;
	}

}
