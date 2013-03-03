package com.example.helloFragmentsDia6.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.helloFragmentsDia6.R;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 01/03/13
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class FormFragment extends Fragment {
    private formListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);    //To change body of overridden methods use File | Settings | File Templates.
        if (activity instanceof formListener) {
            listener = (formListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {    //To change body of overridden methods use File | Settings | File Templates.

        View vista = inflater.inflate(R.layout.fragmentlayout, container, false);

        final EditText editText = (EditText) vista.findViewById(R.id.textoForumulario);

        ((Button) vista.findViewById(R.id.Button1))
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        pushButton(v);
                    }
                });
        ((Button) vista.findViewById(R.id.Button2))
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        pushButton(v);
                    }
                });
        ((Button) vista.findViewById(R.id.Button3))
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        pushButton(v);
                    }
                });

        return vista;
    }

    public void pushButton(View v) {
        if (null == listener) {
            return;
        }
        if (((Button) v).getText().equals("Ok")) {
            listener.pushOk (((EditText) getActivity().findViewById(R.id.textoForumulario)).getText().toString());
        } else if (((Button) v).getText().equals("Cancel")) {
            listener.pushCancel("Has cancelado");
        } else if (((Button) v).getText().equals("Test")) {
            listener.pushTest();
        }
    }

    public interface  formListener {
        public void pushOk (String text);
        public void pushCancel (String text);
        public void pushTest ();
    }


}
