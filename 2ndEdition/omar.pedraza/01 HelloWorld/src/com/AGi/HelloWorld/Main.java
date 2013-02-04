package com.AGi.HelloWorld;

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
        setContentView(R.layout.test_layout);

        Button map_button = (Button) this.findViewById(R.id.map_button);
        final TextView map = (TextView) this.findViewById(R.id.map);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag", "debug message");

                Button aux = (Button) view;
                aux.setText(R.string.hello);

                map.setText(R.string.hello);
            }
        });
    }
}
