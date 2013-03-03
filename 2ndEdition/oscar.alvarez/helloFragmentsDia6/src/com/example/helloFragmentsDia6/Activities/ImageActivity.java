package com.example.helloFragmentsDia6.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.example.helloFragmentsDia6.Fragments.ImageFragment;

public class ImageActivity extends FragmentActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int imageNumber = intent.getIntExtra("IMAGE", 0);

        // Create the list fragment and add it as our sole content.
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            ImageFragment content = new ImageFragment();
            content.setImage (imageNumber);
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, content).commit();
        }
    }
}