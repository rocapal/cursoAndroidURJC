//
//  ImageFragment.java
//  06 Fragments
//
//  Created by Omar Pedraza on 3/5/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Fragments.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.AGi.Fragments.R;

public class ImageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.image_fragment, null);

        return layout;
    }

    public void setImage(Integer image) {
        ((ImageView) getView().findViewById(R.id.icon)).setImageDrawable(getResources().getDrawable(image));
    }
}
