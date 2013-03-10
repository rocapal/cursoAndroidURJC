package com.example.ConectividadDia7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 08/03/13
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
public class PlaceInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView whereFrom = (TextView) findViewById(R.id.whereFrom);
        TextView infoTextView = (TextView) findViewById(R.id.infoNotification);
        Intent intent = getIntent();
        if (getIntent() == null) {
            whereFrom.setText("vienen por la navegación");
        } else {
            whereFrom.setText("vienen por la notificación");
            String infoData = intent.getStringExtra(MainActivity.MESSAGE_KEY);
            infoTextView.setText(infoData);
        }
    }
}
