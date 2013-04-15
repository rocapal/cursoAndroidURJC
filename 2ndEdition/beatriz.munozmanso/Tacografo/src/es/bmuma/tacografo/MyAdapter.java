package es.bmuma.tacografo;

import java.util.ArrayList;

import es.bmuma.tacografo.ListTime.Item;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter 
{
    
	private Context mContext;
	private ArrayList<Item> mList = ListTime.myList;
	
	public MyAdapter(Context c) {
		mContext = c;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Log.d("TAG", "position " + String.valueOf(position));
		View view = null;
	
		if (convertView == null)
		{
		
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			view = inflater.inflate(R.layout.list_item, null);
		}
		else
			view = convertView;
		
		
		ImageView img = (ImageView) view.findViewById(R.id.listTimeType);
		img.setImageDrawable(mContext.getResources().getDrawable(mList.get(position).mImage));			
					
		TextView tTitle = (TextView) view.findViewById(R.id.listTimeStart);
		tTitle.setText(mList.get(position).mTime);
		
		TextView Tdescription = (TextView) view.findViewById(R.id.listTimeDuration);
		Tdescription.setText(mList.get(position).mDuraction);
		
		return view;

	}

}
