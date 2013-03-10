package com.example.ConectividadDia7;

import android.net.Uri;

import java.net.URI;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 08/03/13
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
public class PlaceObject {
    private String mName;
    private String mDate;
    private Uri mInformationUrl;
    private Uri mPhotoUrl;

    public Uri getmPhotoUrl() {
        return mPhotoUrl;
    }

    public void setmPhotoUrl(Uri mPhotoUrl) {
        this.mPhotoUrl = mPhotoUrl;
    }

    public Uri getmInformationUrl() {
        return mInformationUrl;
    }

    public void setmInformationUrl(Uri mInformationUrl) {
        this.mInformationUrl = mInformationUrl;
    }

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
}
