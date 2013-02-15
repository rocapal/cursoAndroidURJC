package com.example.listas;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListaCompleja extends ListActivity {
	
	public class Elemento {
		String titulo;
		String descripcion;
	}
	
	private void setElementos (ArrayList<Elemento> elementos) {
		elementos.clear();
		
		Elemento e1 = new Elemento();
		e1.titulo="Titulo1";
		e1.descripcion="Descripcion1";
		elementos.add(e1);

		Elemento e2 = new Elemento();
		e2.titulo="Titulo2";
		e2.descripcion="Descripcion2";
		elementos.add(e2);
		
		Elemento e3 = new Elemento();
		e3.titulo="Titulo3";
		e3.descripcion="Descripcion3";
		elementos.add(e3);
	}
	
	private ArrayList<Elemento> elementos = new ArrayList<Elemento>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listacompleja);

		setElementos(elementos);
		
		ListAdapter la = new ElementoAdapter(this);
		
		setListAdapter(la);
	}
	
	public class ElementoAdapter extends BaseAdapter {
		ListaCompleja lista;
		
		public ElementoAdapter(ListaCompleja l) {
			lista = l;
		}

		@Override
		public int getCount() {
			return lista.elementos.size();
		}

		@Override
		public Object getItem(int index) {
			return lista.elementos.get(index);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int index, View vista, ViewGroup grupoVista) {
			Elemento e = lista.elementos.get(index);
			
			LayoutInflater inflater = (LayoutInflater) lista.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			View view = inflater.inflate(R.layout.elemento, null);
			
			TextView txTitulo = (TextView) view.findViewById(R.id.txTitulo);
			txTitulo.setText(e.titulo);
			
			TextView txDescripcion = (TextView) view.findViewById(R.id.txDescripcion);
			txDescripcion.setText(e.descripcion);

			return view;
		}
		
	}

}
