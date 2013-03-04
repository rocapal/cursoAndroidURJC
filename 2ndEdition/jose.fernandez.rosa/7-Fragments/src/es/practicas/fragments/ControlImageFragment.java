package es.practicas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ControlImageFragment extends Fragment {
	private Integer mResourceID=-1;
	private View mView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mView = inflater.inflate(R.layout.control_image_fragment,container,false);
		updateImage();
		return mView;
		
	}
	
	
	
	public void updateImage(){
		ImageView image = (ImageView) mView.findViewById(R.id.imageView1);
		
		if (image!=null && mResourceID!=-1)
			image.setImageDrawable(getResources().getDrawable(mResourceID));
	}

	public void setmResourceID(Integer mResourceID) {
		this.mResourceID = mResourceID;
	}
	
}
