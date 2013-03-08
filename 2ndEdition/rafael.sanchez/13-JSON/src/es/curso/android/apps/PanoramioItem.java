package es.curso.android.apps;

import android.net.Uri;

public class PanoramioItem {
	
	private String mName;
	private String mDate;
	private Uri mInfoUrl;
	private Uri mPhotoUrl;
	
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	public String getDate() {
		return mDate;
	}
	public void setDate(String mDate) {
		this.mDate = mDate;
	}
	public Uri getInfoUrl() {
		return mInfoUrl;
	}
	public void setInfoUrl(Uri mInfoUrl) {
		this.mInfoUrl = mInfoUrl;
	}
	public Uri getPhotoUrl() {
		return mPhotoUrl;
	}
	public void setPhotoUrl(Uri mPhotoUrl) {
		this.mPhotoUrl = mPhotoUrl;
	}
	

}
