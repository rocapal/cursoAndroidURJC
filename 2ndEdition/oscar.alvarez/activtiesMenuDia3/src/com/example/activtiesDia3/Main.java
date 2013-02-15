package com.example.activtiesDia3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button go_activity = (Button)this.findViewById(R.id.GoActivity);
        Button go_activity2 = (Button)this.findViewById(R.id.GoActivity2);
        go_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, NewActivity1.class);
                intent.putExtra(Constants.VALUE_1, "Actividad 1");//se guarda en la 'key' de value 1 el valor del segundo parametro
                intent.putExtra(Constants.VALUE_2, 1);
                startActivityForResult(intent, Constants.ACTIVITY_REQUEST_FROM_ACT1);
            }
        });
        go_activity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
                //Intent intent = new Intent(Main.this, NewActivity2.class);
                //startActivityForResult(intent, Constants.ACTIVITY_REQUEST_FROM_ACT2);
            }
        });
    }
    protected void openActivity() {
        Intent intent = new Intent(this, NewActivity2.class);
        intent.putExtra(Constants.VALUE_3, "Actividad 2");//se guarda en la 'key' de value 1 el valor del segundo parametro
        intent.putExtra(Constants.VALUE_4, 2);
        startActivityForResult(intent, Constants.ACTIVITY_REQUEST_FROM_ACT2);
    }
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        if (resultCode == Activity.RESULT_OK &&
                requestCode == Constants.ACTIVITY_REQUEST_FROM_ACT1) {
            Toast.makeText(getApplicationContext(), "Viene de actividad" + String.valueOf(requestCode) + "Valor" + String.valueOf(data.getStringExtra(Constants.VALUE_3)), Toast.LENGTH_LONG).show();

        }
        if (resultCode == Activity.RESULT_OK &&
                requestCode == Constants.ACTIVITY_REQUEST_FROM_ACT2) {
            Toast.makeText(getApplicationContext(), "Viene de actividad" + String.valueOf(requestCode) + "Valor" + String.valueOf(data.getStringExtra(Constants.VALUE_4)), Toast.LENGTH_LONG).show();
        }
    }
}
