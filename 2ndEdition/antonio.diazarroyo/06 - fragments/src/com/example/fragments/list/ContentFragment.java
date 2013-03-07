package com.example.fragments.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fragments.R;

public class ContentFragment extends Fragment {

	private View view;
	private Integer imageId;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.content, container, false);
		updateImage();
		return view;
	}

	private void updateImage() {
		ImageView imageView = (ImageView)view.findViewById(R.id.image);
		if (imageView!=null && imageId!=null) {
			imageView.setImageDrawable(this.getResources().getDrawable(imageId));
		}
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	
}
