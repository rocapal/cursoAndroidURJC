package com.example.helloFragmentsDia6.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.helloFragmentsDia6.R;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 01/03/13
 * Time: 20:39
 * To change this template use File | Settings | File Templates.
 */
public class ImageFragment extends Fragment {
    private Integer mImageValue;
    private View myView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void setImage (Integer res)
    {
        mImageValue = res;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.content, container, false);
        updateImage();
        return myView;
    }

    public void updateImage ()
    {
        ImageView image = (ImageView) myView.findViewById(R.id.image);
        if (image != null && mImageValue != null)
            image.setImageDrawable(getResources().getDrawable(mImageValue));
    }
}
