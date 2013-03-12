//
//  NotificationActivity.java
//  07 Connectivity
//
//  Created by Omar Pedraza on 3/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Connectivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notification);

        Bundle extras = getIntent().getExtras();
        Integer number = extras.getInt(Constants.kNotificationNumber, 0);

        TextView textView = (TextView) findViewById(R.id.notification_text);
        textView.setText("The number sent by the notification is: " + number);
    }
}
