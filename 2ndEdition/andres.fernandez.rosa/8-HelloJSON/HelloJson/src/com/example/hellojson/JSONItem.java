package com.example.hellojson;

import android.net.Uri;

public class JSONItem {
	private String mDate;
	private String mName;
	private Uri mInfoURL;
	private Uri mPhotoURL;
	
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public Uri getmInfoURL() {
		return mInfoURL;
	}
	public void setmInfoURL(Uri url) {
		this.mInfoURL = url;
	}
	public Uri getmPhotoURL() {
		return mPhotoURL;
	}
	public void setmPhotoURL(Uri url) {
		this.mPhotoURL = url;
	}
	
	
	
	
}
