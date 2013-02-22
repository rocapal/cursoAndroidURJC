//
//  MapOverlay.java
//  04 MapsTest
//
//  Created by Omar Pedraza on 2/15/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//
package com.AGi.MapsTest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapOverlay extends Overlay {

    private Drawable mMarker;
    private int mMarkerXOffset;
    private int mMarkerYOffset;

    private GeoPoint mGeoPoint;
    private String mText;

    public void setGeoPoint (GeoPoint geoPoint)
    {
        mGeoPoint = geoPoint;
    }

    public void setDrawable(Drawable draw)
    {
        mMarker = draw;
    }

    public void setText(String text) {
        mText = text;
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        if (!shadow) {
            // Make sure to give mMarker bounds so it will draw in the overlay
            final int intrinsicWidth = mMarker.getIntrinsicWidth() / 2;
            final int intrinsicHeight = mMarker.getIntrinsicHeight() / 2;
            mMarker.setBounds(0, 0, intrinsicWidth, intrinsicHeight);

            mMarkerXOffset = -(intrinsicWidth / 2);
            mMarkerYOffset = -(intrinsicHeight / 2);

            Paint paint = new Paint();
            paint.setARGB(250, 0, 0, 0);
            paint.setAntiAlias(true);
            paint.setFakeBoldText(true);

            Point point2 = new Point();
            Projection p = mapView.getProjection();
            p.toPixels(mGeoPoint, point2);

            if (mText != null) {
                canvas.drawText(mText, point2.x - intrinsicWidth, point2.y + intrinsicHeight, paint);
            }

            super.draw(canvas, mapView, shadow);
            drawAt(canvas, mMarker, point2.x + mMarkerXOffset, point2.y + mMarkerYOffset, shadow);
        }
    }
}