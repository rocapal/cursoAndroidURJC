package com.example.helloFragmentsDia6.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.example.helloFragmentsDia6.Fragments.MyListFragment;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 01/03/13
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */
public class MobileActivity extends FragmentActivity implements MyListFragment.IListFragment
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the list fragment and add it as our sole content.
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            MyListFragment list = new MyListFragment();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
        }
    }

    @Override
    public void itemClick(Integer imageResource) {
        Toast.makeText(this, "Has pulstado el boton con imagen numero " + imageResource, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MobileActivity.this, ImageActivity.class);
        intent.putExtra("IMAGE", imageResource);
        startActivity(intent);
    }
}
