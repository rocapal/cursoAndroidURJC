package com.example.gjson;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParse {

	String mJsonString;
	
	JSONParse(String str){
		super();
		if (str==null)
			throw new NullPointerException();
		mJsonString = str;
	}
	
	private JSONitem parseNode(JSONObject node){
		JSONitem item = new JSONitem();
		
		try {
			if (node.has("name"))
				item.setmName(node.getString("name"));
			if (node.has("since"))
				item.setmDate(node.getString("since"));
			if (node.has("external_info")){
				JSONObject jInfo = node.getJSONObject("external_info");
				if (jInfo.has("photo_thumb"))
					item.setmPhotoUri(jInfo.getString("photo_thumb"));
				if (jInfo.has("info_url"))
					item.setMinfoUri(jInfo.getString("info_url"));
			}
			return item;
		}catch (JSONException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<JSONitem> parser(){
		ArrayList<JSONitem> resArray = new ArrayList<JSONitem>();
		try{
			JSONObject root = new JSONObject(mJsonString);
			if (root.has("results")){
				JSONArray array = root.getJSONArray("results");
				for (int i=0; i<array.length(); i++){
					JSONObject nodo = array.getJSONObject(i);
					JSONitem it = parseNode(nodo);
					if (it!=null)
						resArray.add(it);
					else
						Log.d("a","nodonulo");
				}
				return resArray;
			}else
				return null;
		} catch (JSONException e){
			e.printStackTrace();
			return null;
		}
	}

}
