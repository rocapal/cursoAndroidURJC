package com.example.list;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AdvancedList extends ListActivity{
	
	private MyAdapter Adapter = null;
	
	public class Node 
	{
		public String Title;
		public String Description;
		public Integer Image;
	}
	
	private static ArrayList<Node> mArray = new ArrayList<Node>();
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advanced);
		
		Node mynode = new Node(); 
        mynode.Title = this.getResources().getString(R.string.title1);
        mynode.Description = this.getResources().getString(R.string.description1);
        mynode.Image = R.drawable.r1;
        mArray.add(mynode);

        Node mynode2 = new Node();
        mynode2.Title = this.getResources().getString(R.string.title2);
        mynode2.Description = this.getResources().getString(R.string.description2);
        mynode2.ImageResource = R.drawable.r2;
        mArray.add(mynode2);

        
        Node mynode3 = new Node();
        mynode3.Title = this.getResources().getString(R.string.title3);
        mynode3.Description = this.getResources().getString(R.string.description3);
        mynode3.ImageResource = R.drawable.r3;
        mArray.add(mynode3);
        
        mArray.addAll(mArray);

	}
	
}