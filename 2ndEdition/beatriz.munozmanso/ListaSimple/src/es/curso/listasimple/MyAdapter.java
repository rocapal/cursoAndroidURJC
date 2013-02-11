package es.curso.listasimple;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter{



	private Context context;
	private ArrayList<Item> array;
	
	public MyAdapter(Context c, ArrayList<Item> mArray) {
		this.context = c;
		this.array = mArray;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return this.array.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.array.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.d("TAG", "Position "+ String.valueOf(position));
		View view = null;
		
		LayoutInflater inflater = (LayoutInflater) this.context.
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//view = inflater.inflate(R.layout, root)
		return null;
	}

}
