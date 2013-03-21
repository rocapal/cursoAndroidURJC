package com.AGi.AdMob;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class Main extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        AdView adView = (AdView) findViewById(R.id.ad_banner);

        AdRequest request = new AdRequest();
        request.addTestDevice(AdRequest.TEST_EMULATOR);
        adView.loadAd(request);

        final LinearLayout container = (LinearLayout) findViewById(R.id.ad_container);

        Button show = (Button) findViewById(R.id.show_button);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (container.getVisibility() == View.VISIBLE) {
                    container.setVisibility(View.INVISIBLE);
                    ((Button) view).setText(R.string.show_button);
                }
                else {
                    container.setVisibility(View.VISIBLE);
                    ((Button) view).setText(R.string.hide_button);
                }
            }
        });
    }
}
