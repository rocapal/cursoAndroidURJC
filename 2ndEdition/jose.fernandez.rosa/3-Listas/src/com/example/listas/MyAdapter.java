package com.example.listas;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	public class Node {
		public String mTitle;
		public String mDescription;
		public Integer mImageResource;

		public Node(String title, String description, Integer imageResource) {
			this.mTitle = title;
			this.mDescription = description;
			this.mImageResource = imageResource;
		}

		public Node() {
		}

	}

	public ArrayList<Node> datos = new ArrayList<Node>();

	private Context mContext;

	public MyAdapter(Context context) {
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("TAG", "position: " + String.valueOf(position));

		View view = null;

		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		view=  inflater.inflate(R.layout.list_node_view, null);
		
		TextView Title = (TextView) view.findViewById(R.id.title);
		TextView Description = (TextView) view.findViewById(R.id.description);
		ImageView icon = (ImageView) view.findViewById(R.id.image);
		
		Title.setText(datos[position].mTitle);
		Description.setText(datos[position].mDescription);
		icon.setImageDrawable(R.drawable.ic_launcher);
		
		return view;
		
		
	}

}
