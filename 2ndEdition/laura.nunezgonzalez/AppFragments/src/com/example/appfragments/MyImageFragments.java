package com.example.appfragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MyImageFragments extends ListFragment {

	private View myView;
	private Integer mResImage;
	
	public MyImageFragments() {
		
	};
	
	public interface IListFragment
	{
		void itemClick (Integer imagenResource);
	}
	
	
	public void setImage (Integer res){
		mResImage = res;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		myView = inflater.inflate(R.layout.imagen, container,false);
		updateImage();
		return myView;
	}

	private void updateImage() {
		// TODO Auto-generated method stub
		ImageView image = (ImageView) myView.findViewById(R.id.iv);
		if (image != null && mResImage != null)
			image.setImageDrawable(getResources().getDrawable(mResImage));
	}
	
}
