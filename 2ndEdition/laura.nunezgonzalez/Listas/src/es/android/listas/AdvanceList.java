package es.android.listas;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AdvanceList extends ListActivity {

	public static class Node
	{
		public int mTitle;
		public int mDescrip1;
		public int mDescrip2;
	}
	
	public static ArrayList<Node> mArray = new ArrayList<Node>();
	

	private ListAdapter mAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista);
		setData();
		
		this.mAdapter = new MyAdapter(this);
		setListAdapter(this.mAdapter);
	}
	
	private void setData() {
		mArray.clear();
		
		Node n1 = new Node();
		n1.mTitle = R.string.texto1a;
		n1.mDescrip1= R.string.texto2a;
		n1.mDescrip2=R.string.texto3a;
		
		Node n2 = new Node();
		n2.mTitle = R.string.texto1b;
		n2.mDescrip1= R.string.texto2b;
		n2.mDescrip2=R.string.texto3b;
		
		Node n3 = new Node();
		n3.mTitle = R.string.texto1c;
		n3.mDescrip1= R.string.texto2c;
		n3.mDescrip2=R.string.texto3c;
		
		mArray.add(n1);
		mArray.add(n2);
		mArray.add(n3);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

}
