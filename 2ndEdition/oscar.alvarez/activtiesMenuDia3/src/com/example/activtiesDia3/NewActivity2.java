package com.example.activtiesDia3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 08/02/13
 * Time: 17:10
 * To change this template use File | Settings | File Templates.
 */
public class NewActivity2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1layout);
        TextView tv = (TextView) this.findViewById(R.id.Texto);

        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.VALUE_4, "Actividad 2");
        setResult(RESULT_OK, returnIntent);

        Intent i = getIntent();
        if ( i != null)
        {
            String name = i.getStringExtra(Constants.VALUE_3);
            Integer num = i.getIntExtra(Constants.VALUE_4, -1);
            tv.setText(name + " - Valor - " + num.toString());
        }
        //Ejecutar menu
    }
    @Override
    protected void onPause () {//Esto se puede poner cuando sea para volver a la actividad anterior,este es el ultimo punto por el que pasa
        super.onPause();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.VALUE_4, "Actividad 2");
        setResult(RESULT_OK, returnIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity1menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast toast = Toast.makeText(this, null , Toast.LENGTH_LONG);
        String message;
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                message = "Facebook option";
                toast.setText("boton 1");
                break;
            case R.id.MnuOpc2:
                message = "Twitter option";
                toast.setText("boton 2");
                break;
            case R.id.MnuOpc3:
                message = "Tuenti option";
                toast.setText("boton 3");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        toast.setText(message);
        toast.show();
        return true;
    }
}
