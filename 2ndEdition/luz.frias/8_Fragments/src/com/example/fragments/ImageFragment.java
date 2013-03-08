package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {
	
	private Integer mImageId;
	private ImageView mImageView;
	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.image, container, false);
		updateImage();
		return mView;		
	}
	
	public void setImage(int imageId) {
		mImageId = imageId;
		
	}
	
	public void updateImage () {
		ImageView image = (ImageView) mView.findViewById(R.id.ivImage);
		
		if (image != null && mImageId != null) {
			image.setImageDrawable(getResources().getDrawable(mImageId));
		}
	}
	
}
