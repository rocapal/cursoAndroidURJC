package com.AGi.ActivityTest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

        Button url_button = (Button) this.findViewById(R.id.url_button);
        url_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.es"));
                startActivity(intent);
            }
        });

        Button phone_button = (Button) this.findViewById(R.id.phone_button);
        phone_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+34654532452"));
                startActivity(intent);
            }
        });

        Button call_button = (Button) this.findViewById(R.id.call_button);
        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+34654532452"));
                startActivity(intent);
            }
        });

    }
}
