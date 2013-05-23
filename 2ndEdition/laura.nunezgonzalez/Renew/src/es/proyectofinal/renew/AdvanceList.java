/*
 *
 *  Copyright (C) Roberto Calvo Palomino
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/. 
 *
 *  Author : Roberto Calvo Palomino <rocapal at gmail dot com>
 *
 */

package es.proyectofinal.renew;


import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class AdvanceList extends ListActivity 
{
	private MyAdapter mAdapter = null;
	public static int posicion=0;
	
	// We define a structure to save the data
	public class Node 
	{
		public int[] mImageResource = {0,0,0,0,0,0,0};
	}
	
	// ArrayList
	private static ArrayList<Node> mArray = new ArrayList<Node>();
	

	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);                       
        setContentView(R.layout.list);
        setData();
        
        mAdapter = new MyAdapter(this);
        setListAdapter(mAdapter);			   
	    	   
	}
	
    protected void onListItemClick(ListView l, View v, int position, long id) 
	{

    	// Create a new intent to call other Activity. 
    	// Using the methods "putExtra" we can
    	// send data to the new activity
    	boolean completado = true;
    	Log.d("ClickITEM", "Item clicado");
    	for(int j=0; j < mArray.get(position).mImageResource.length; j++)
    	{
			if (mArray.get(position).mImageResource[j] == R.drawable.android1)
				mArray.get(position).mImageResource[j] = R.drawable.android2;
			else
				mArray.get(position).mImageResource[j] = R.drawable.android1;
    	}
    	mAdapter.notifyDataSetChanged();
    	for(int i=0; i < mArray.size()-1; i++)
    	{
    		if(!(mArray.get(i).mImageResource[0] == mArray.get(i+1).mImageResource[0]))
    			completado = false;
    	}
    	if(completado)
    		Toast.makeText(this, "Felicidades!!", Toast.LENGTH_SHORT).show();
	}
	
	public void prueba(View v){
		Log.d("SEPARAR", "se ha pulsado la imagen 2");
		Toast.makeText(this, "Prueba img 2", Toast.LENGTH_SHORT).show();
	}
    
    private void setData ()
    {

            mArray.clear();
            Node mynode = null;
            
            for(int i=0; i < 10; i++)
            {
            	mynode = new Node();
            	for (int j=0;j < 7; j++)
            	{	
		        	if (Math.random() > 0.5)
		        		mynode.mImageResource[j] = R.drawable.android1;
		        	else
		        		mynode.mImageResource[j] = R.drawable.android2;
            	}
            	mArray.add(mynode);
            }	
    }

    
	
	public static class MyAdapter extends BaseAdapter 
	{
        
		private Context mContext;
		
		public MyAdapter(Context c) {
			mContext = c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mArray.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mArray.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = null;
				
			if (convertView == null) {
				// Make up a new view
				LayoutInflater inflater = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				view = inflater.inflate(R.layout.myitem2, null);
			} else {
				// Use convertView if it is available
				view = convertView;
			}
							
			// Example to get an image resource	
			ImageView img = (ImageView) view.findViewById(R.id.image1);
			img.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).mImageResource[0]));			
		/*	ImageView img2 = (ImageView) view.findViewById(R.id.image2);
			img2.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).mImageResource[1]));			
			ImageView img3 = (ImageView) view.findViewById(R.id.image3);
			img3.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).mImageResource[2]));			
			ImageView img4 = (ImageView) view.findViewById(R.id.image4);
			img4.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).mImageResource[3]));			
			ImageView img5 = (ImageView) view.findViewById(R.id.image5);
			img5.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).mImageResource[4]));			
			ImageView img6 = (ImageView) view.findViewById(R.id.image6);
			img6.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).mImageResource[5]));			
			ImageView img7 = (ImageView) view.findViewById(R.id.image7);
			img7.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).mImageResource[6]));	*/								
			return view;
	
		}

	}
}
