package com.AGi.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.AGi.Fragments.List.TestListActivity;

public class Main extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        ((Button) findViewById(R.id.fragment_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, FragmentsActivity.class);
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.list_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, TestListActivity.class);
                startActivity(intent);
            }
        });
    }
}
