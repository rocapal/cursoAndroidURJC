package com.example.activtiesDia3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 08/02/13
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
 */
public class NewActivity1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1layout);
        TextView tv = (TextView) this.findViewById(R.id.Texto);

        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.VALUE_3, "Vuelta a Main");
        setResult(RESULT_OK, returnIntent);

        Intent i = getIntent();
        if ( i != null)
        {
            String name = i.getStringExtra(Constants.VALUE_1);
            Integer num = i.getIntExtra(Constants.VALUE_2, -1);

            tv.setText(name + " Valor " + num.toString());
        }
    }
    @Override
    protected void onPause () {//Esto se puede poner cuando sea para volver a la actividad anterior,este es el ultimo punto por el que pasa
        super.onPause();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.VALUE_3, 3);
        setResult(RESULT_OK, returnIntent);
    }
}
