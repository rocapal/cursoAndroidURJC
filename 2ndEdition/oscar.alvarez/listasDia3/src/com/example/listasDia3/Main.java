package com.example.listasDia3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button go_listaSimple = (Button)this.findViewById(R.id.GoListaSimple);
        Button go_listaCompleja = (Button)this.findViewById(R.id.GoListaCompleja);
       go_listaSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, SimpleList.class);
                startActivity(intent);
            }
        });
        go_listaCompleja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, AdvancedList.class);
                startActivity(intent);
            }
        });
    }
}
