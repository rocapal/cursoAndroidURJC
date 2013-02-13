package com.example.listasDia3;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 08/02/13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class SimpleList extends ListActivity {
    private String[] elements = new String[] {"URJC", "EOI", "Android"};
    //private ListView list;
    //private ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements);

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listlayout);

        //o esto
        ListView list = (ListView) this.findViewById(android.R.id.list);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements));
        //list = (ListView) this.findViewById(android.R.id.list);
        //list.setAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        Toast toast = Toast.makeText(this, "has pulsado la posición " + position , Toast.LENGTH_LONG);
        toast.show();
    }
}
