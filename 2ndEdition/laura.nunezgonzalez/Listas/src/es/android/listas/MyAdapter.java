package es.android.listas;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context mContext;
	
	public MyAdapter(Context c){
		this.mContext = c;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return AdvanceList.mArray.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		
		if (convertView == null) {
		
			LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
		
			view = inflater.inflate(R.layout.personal_layout, null);
		} else {
			view = convertView;
		}
		TextView text1 = (TextView) view.findViewById(R.id.tvTexto1);
		text1.setText(AdvanceList.mArray.get(position).mTitle);
		TextView text2 = (TextView) view.findViewById(R.id.tvTexto2);
		text2.setText(AdvanceList.mArray.get(position).mDescrip1);
		TextView text3 = (TextView) view.findViewById(R.id.tvTexto3);
		text3.setText(AdvanceList.mArray.get(position).mDescrip2);		
		
		return view;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return false;
	}

}
