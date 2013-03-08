package com.example.apires;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	private String mJsonStr = null;
	
	public JSONParser (String str) {
		if (str == null)
			throw new NullPointerException();
		mJsonStr = str;
	}
	
	public ArrayList<PhotoItem> parse() {
		ArrayList<PhotoItem> photos = new ArrayList<PhotoItem>();
		
		try {
			JSONObject root = new JSONObject(mJsonStr);
			if (root.has("results")) {
				JSONArray results = root.getJSONArray("results");
				for (int i = 0; i < results.length(); i++) {
					JSONObject node = results.getJSONObject(i);
					PhotoItem photoItem = parseNode(node);
					if (photoItem != null) {
						photos.add(photoItem);
					}
				}
			}
			else {
				return null;
			}
			return photos;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	private PhotoItem parseNode(JSONObject node) {
		PhotoItem photoItem = new PhotoItem();
		
		try {
			if (node.has("name")) {
				photoItem.setName(node.getString("name"));
			}
			if (node.has("since")) {
				photoItem.setDate(node.getString("since"));
			}
			if (node.has("external_info")) {
				JSONObject extInfo = node.getJSONObject("external_info");
				if (extInfo.has("photo_thumb")) {
					photoItem.setThumb(extInfo.getString("photo_thumb"));
				}
				if (extInfo.has("info_url")) {
					photoItem.setInfo(extInfo.getString("info_url"));
				}
			}
			return photoItem;
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
