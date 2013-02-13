package com.example.actividadesdiados;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button opcion1 = (Button)this.findViewById(R.id.opcion1);
        Button opcion2 = (Button)this.findViewById(R.id.opcion2);
        Button opcion3 = (Button)this.findViewById(R.id.opcion3);
        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showTelIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:+34617196440"));
                startActivity(showTelIntent);
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callTelIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+34617196440"));
                startActivity(callTelIntent);
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
