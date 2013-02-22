package com.example.listacompleja;

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
import android.widget.TextView;

public class Main extends ListActivity {

	// Definimos la variable mAdaptador del tipo MiAdaptador y la ponemos a null
	private MiAdaptador mAdaptador = null;

	// Definimos la estructura de los items de la lista
	public class Nodo {
		public String mTitulo;
		public String mExplicacion;
		public Integer mImagen;
	}

	// Generamos un ArrayList de tipo Nodo para mostrar todos los items
	private static ArrayList<Nodo> mArray = new ArrayList<Nodo>();

	// Método onCreate necesario en todas las actividades de Android
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Realizamos la llamada al método padre de onCreate
		super.onCreate(savedInstanceState);

		// Generamos un nuevo método para establecer los datos
		setDatos();

		// Generamos un nuevo adaptador (nuestro), utilizando
		// el contexto this para poder utilizar posteriormente las imágenes
		mAdaptador = new MiAdaptador(this);

		// Lo seleccionamos para nuestra lista
		setListAdapter(mAdaptador);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setDatos() {
		// Limpiamos el Array
		mArray.clear();

		// Generamos una instancia de Nodo (creamos 1 OBJETO de clase Nodo)
		Nodo nodo1 = new Nodo();
		// Asignamos el atributo titulo al objeto Nodo1
		nodo1.mTitulo = this.getResources().getString(R.string.titulo1);
		// Asignamos el atributo explicacion al objeto Nodo1
		nodo1.mExplicacion = this.getResources().getString(R.string.explicacion1);
		// Cargamos la imagen de Ndo1
		nodo1.mImagen = R.drawable.r1;
		// Cargamos en el Array el Nodo1
		mArray.add(nodo1);

		// Realizamos las mismas operaciones para todos los nodos
		Nodo nodo2 = new Nodo();

		nodo2.mTitulo = this.getResources().getString(R.string.titulo2);
		nodo2.mExplicacion = this.getResources().getString(R.string.explicacion2);
		nodo2.mImagen = R.drawable.r2;

		mArray.add(nodo2);

		Nodo nodo3 = new Nodo();

		nodo3.mTitulo = this.getResources().getString(R.string.titulo3);
		nodo3.mExplicacion = this.getResources().getString(R.string.explicacion3);
		nodo3.mImagen = R.drawable.r3;

		mArray.add(nodo3);

	}

	// Creamos la nueva clase MiAdaptador extendiendo de BaseAdapter e
	// implementamos
	// métodos
	public static class MiAdaptador extends BaseAdapter {

		private Context mContexto;
		public MiAdaptador(Context c)
		{
			mContexto = c;
		}

		@Override
		public int getCount() {
			// gerCount retorna el nº de items que tiene la lista
			return mArray.size();
		}

		@Override
		public Object getItem(int position) {
			// funcion que devuelve un item concreto
			return mArray.get(position);
		}

		@Override
		public long getItemId(int position) {
			// devuelve los id de los item
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Este método getView devuelve una vista del item seleccionado
			
			View vista = null;
			
			// Si convertView no tiene un view cargado...
			if (convertView==null){
				
				// Método que transforma un archivo xml en 1 View
				LayoutInflater inflater = (LayoutInflater) mContexto.
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			vista = inflater.inflate(R.layout.vgenerica, null);
			
			// si no es así...
			} else {
				vista = convertView;
			}
			// Rellenamos "vista" con la imagen y los textos del xml "vgenerica" {
			ImageView imagen = (ImageView) vista.findViewById(R.id.imagenNodo);
			imagen.setImageDrawable(mContexto.getResources().getDrawable(mArray.get(position).mImagen));
			
			TextView mTitulo = (TextView) vista.findViewById(R.id.cabecera);
			mTitulo.setText(mArray.get(position).mTitulo);
			
			TextView mExplicacion = (TextView) vista.findViewById(R.id.comentario);
			mExplicacion.setText(mArray.get(position).mExplicacion);
			// }
			
			// Devuelve "vista"
			return vista;
		}

	}

	
	
}
