package com.AGi.ListTest;

import android.app.Activity;
import android.content.Intent;
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

        Button simple_button = (Button) this.findViewById(R.id.simple_button);
        simple_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, SimpleList.class);
                startActivity(intent);
            }
        });

        Button composed_button = (Button) this.findViewById(R.id.composed_button);
        composed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, ComposedList.class);
                startActivity(intent);
            }
        });
    }
}
