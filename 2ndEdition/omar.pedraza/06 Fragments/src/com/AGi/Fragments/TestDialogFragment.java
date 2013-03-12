//
//  TestDialogFragment.java
//  06 Fragments
//
//  Created by Omar Pedraza on 3/1/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class TestDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private static DialogFragmentListener mListener = null;
    private static EditText mTextField = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof DialogFragmentListener) {
            mListener = (DialogFragmentListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.test_dialog, container, false);

        mTextField = (EditText) layout.findViewById(R.id.text_field);
        getDialog().setTitle(R.string.dialog_title);

        mTextField.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mTextField.setOnEditorActionListener(this);

        return layout;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            mListener.addText(mTextField.getText().toString());
            this.dismiss();

            return true;
        }
        return false;
    }
}
