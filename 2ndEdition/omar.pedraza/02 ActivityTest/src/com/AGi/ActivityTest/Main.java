package com.AGi.ActivityTest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

        Button activity_1_button = (Button) this.findViewById(R.id.activity_1_button);
        activity_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, Activity1.class);
                intent.putExtra(Constants.kName, getString(R.string.app_name));
                intent.putExtra(Constants.kMessage, "Test message");
                intent.putExtra(Constants.kNumber, 19);
                startActivity(intent);
            }
        });

        Button activity_2_button = (Button) this.findViewById(R.id.activity_2_button);
        activity_2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, Activity2.class);
                startActivityForResult(intent, Constants.kActivity2Request);
            }
        });

        Button activity_3_button = (Button) this.findViewById(R.id.activity_3_button);
        activity_3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, Activity3.class);
                startActivityForResult(intent, Constants.kActivity3Request);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Toast toast = Toast.makeText(this, "You've just returned from Activity " + String.valueOf(requestCode) + " with value: " + String.valueOf(data.getIntExtra(Constants.kValue, 0)), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
