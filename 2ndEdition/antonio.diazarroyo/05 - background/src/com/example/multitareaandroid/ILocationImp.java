package com.example.multitareaandroid;

import android.location.Location;

public class ILocationImp implements ILocation {

	@Override
	public void updateLocation(Location location) {
		if (MainActivity.MY_TEXT!=null) {
			MainActivity.MY_TEXT.setText("Location: " + location.getLatitude() + "-" + location.getLongitude() );
		}
	}
}
