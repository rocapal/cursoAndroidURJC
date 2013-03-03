package com.example.helloFragmentsDia6.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.helloFragmentsDia6.Fragments.FormFragment;
import com.example.helloFragmentsDia6.R;

public class MainActivity extends FragmentActivity implements FormFragment.formListener {
    private boolean fragmentOnScreen = false;
    static final String FRAGMENT_TAG = "editor";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button go_ToFragment = (Button)this.findViewById(R.id.Fragments);
        go_ToFragment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!fragmentOnScreen)
                {
                    showFragment();
                    go_ToFragment.setText("Hide Fragment");
                }
                else
                {
                    hideFragment();
                    go_ToFragment.setText("Show Fragment");
                }
            }
        });
    }

    private void showFragment()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment editor = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (null == editor) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add (R.id.Container, new FormFragment(), FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
        fragmentOnScreen = true;
    }

    private void hideFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment editor = fm.findFragmentByTag(FRAGMENT_TAG);
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(editor);
        ft.commit();
        fragmentOnScreen = false;
    }

    @Override
    public void pushOk(String text) {
        Toast.makeText(this, "Has pulstado el boton Ok, texto " + text, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MobileActivity.class);
        startActivity(intent);
    }

    @Override
    public void pushCancel(String text) {
        Toast.makeText(this, "Has pulstado el boton Cancel, texto " + text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pushTest() {
        Toast.makeText(this, "Has pulstado el boton Test ", Toast.LENGTH_SHORT).show();
    }
}
