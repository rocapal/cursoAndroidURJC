//
//  Place.java
//  07 Connectivity
//
//  Created by Omar Pedraza on 3/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Connectivity;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.UnsupportedEncodingException;

public class Place {
    private String date = null;
    private String name = null;
    private Uri info = null;
    private Uri thumbnail = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        try
        {
            String aux = new String(date.getBytes(), "UTF-8");
            this.date = aux;
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    public Uri getInfo() {
        return info;
    }

    public void setInfo(Uri info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try
        {
            String aux = new String(name.getBytes(), "UTF-8");
            this.name = aux;
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    public Uri getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Uri thumbnail) {
        this.thumbnail = thumbnail;
    }
}
