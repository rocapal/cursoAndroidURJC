package com.example.HelloWorld2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exaframelayout);
        Button map_button = (Button)this.findViewById(R.id.buttonMap);
        final TextView mapDescription = (TextView)this.findViewById(R.id.descriptionMapa);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To change body of implemented methods use File | Settings | File Templates.
                Log.d("tag","message");
                mapDescription.setText("Esta es la descripción del mapa y has clickado el botón");
                Button button = (Button)view;
                button.setText(R.string.mensaje);
            }
        });
    }
}
