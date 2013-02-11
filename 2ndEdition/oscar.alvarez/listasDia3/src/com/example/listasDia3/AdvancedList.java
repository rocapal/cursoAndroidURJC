package com.example.listasDia3;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: AGMacBookOscar
 * Date: 08/02/13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */

public class AdvancedList extends ListActivity {
    private MyAdapter mAdapter = null;
    //private ListView list;
    //private ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements);

    /**
     * Called when the activity is first created.
     */
    public class Node
    {
        public String mTitle;
        public String mDescription;
        public Integer mImageResource;
    }

    private static ArrayList<Node> mArray = new ArrayList<Node>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listlayout);
        setData();

        mAdapter = new MyAdapter(this);
        setListAdapter(mAdapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        Toast toast = Toast.makeText(this, "has pulsado la posición " + position , Toast.LENGTH_LONG);
        toast.show();
    }

    private void setData ()
    {
        mArray.clear();

        Node mynode = new Node();

        mynode.mTitle = "Titulo 1";
        mynode.mDescription = "Descripción 1";
        mynode.mImageResource = R.drawable.android;

        mArray.add(mynode);

        //Restaurant 2
        Node mynode2 = new Node();

        mynode2.mTitle = "Titulo 2";
        mynode2.mDescription = "Descrición 2";
        mynode2.mImageResource = R.drawable.facebook;

        mArray.add(mynode2);

        //Restaurant 3
        Node mynode3 = new Node();


        mynode3.mTitle = "Titulo 3";
        mynode3.mDescription = "Descrición 3";
        mynode3.mImageResource = R.drawable.twitter;

        mArray.add(mynode3);
    }

    public static class MyAdapter extends BaseAdapter
    {

        private Context mContext;

        public MyAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return mArray.size();
        }

        @Override
        public Object getItem(int position) {
            return mArray.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;

            if (convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.itemlayout, null);
            } else {
                view = convertView;
            }

            ImageView img = (ImageView) view.findViewById(R.id.image);
            img.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).mImageResource));

            TextView tTitle = (TextView) view.findViewById(R.id.name);
            tTitle.setText(mArray.get(position).mTitle);

            TextView Tdescription = (TextView) view.findViewById(R.id.description);
            Tdescription.setText(mArray.get(position).mDescription);

            return view;

        }

    }
}
