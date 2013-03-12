//
//  ImageAsyncTask.java
//  07 Connectivity
//
//  Created by Omar Pedraza on 3/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Connectivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageAsyncTask extends AsyncTask {
    private static ImageView mImageView = null;
    private static Uri mUri = null;

    public ImageAsyncTask(ImageView imageView, Uri url) {
        super();

        mImageView = imageView;
        mUri = url;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        try {
            URL url = new URL(mUri.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream stream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(stream);

            return bitmap;
        }
        catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        mImageView.setImageBitmap((Bitmap) o);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
