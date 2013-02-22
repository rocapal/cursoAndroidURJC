package com.example.listasimple;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

// Para utilizar la "lista simple" la clase principal hereda de ListActivity, no de Activity
public class Main extends ListActivity {

	private ListView lv1 = null;
	// Llamamos al widget ListView como lv1 y le damos por defecto el valor null

	private ListAdapter la1 = null;
	// Llamamos al adaptador de la lista la1 y le damos tb null

	private String[] testValues = new String[] { "Carne", "Verduras", "Fruta"

	};

	// Generamos un array de elementos para la lista (comienza en posicion 0)

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		lv1 = (ListView) findViewById(android.R.id.list);
		// Generamos el widget creado en el xml usando el metodo findViewById
		// para localizarlo por su id "list"

		la1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, testValues);
		// Generamos el adaptador:
		// Es de tipo <String>
		// El concepto this hace referencia a la clase Main (en este caso)
		// Mediante android.R.layout.simple_list_item_1 se le pasa un layout
		// predefinido en android con 1 TextView
		// en el cual aparecerán los textos de nuestro array, en la posición que
		// indique testValues

		lv1.setAdapter(la1);
		// Utilizamos el método setAdapter para asignarle el adaptador la1 a lv1
	}

	@Override
	protected void onListItemClick(ListView l, View v, int posicion, long id) {
		// Con el método onListItemClick, al pulsar en 1 item de la lista realizará {
		
		Toast.makeText(this, testValues[posicion], Toast.LENGTH_SHORT).show();
		// Toast (comentario) escribe el texto de la posición del array y el método .show lo muestra
		
	}

}
