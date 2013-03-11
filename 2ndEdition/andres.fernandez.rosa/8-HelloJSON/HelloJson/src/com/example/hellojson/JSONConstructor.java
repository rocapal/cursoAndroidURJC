package com.example.hellojson;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;


public class JSONConstructor {

	private String mJSONString;

	public JSONConstructor(String str) {

		if (str == null) {
			throw new NullPointerException();

		}
		this.mJSONString = str;
	}

	private JSONItem ParseNode(JSONObject node) {

		JSONItem item = new JSONItem();
		try {
			if (node.has("name"))
				item.setmName(node.getString("name"));
			if (node.has("since"))
				item.setmDate(node.getString("since"));
			if (node.has("external_info")) {
				JSONObject jInfo = node.getJSONObject("external_info");

				if (jInfo.has("photo_thumb"))
					item.setmPhotoURL(Uri.parse(jInfo.getString("photo_thumb")));
				if (jInfo.has("info_url"))
					item.setmInfoURL(Uri.parse(jInfo.getString("info_url")));

			}

		} catch (JSONException e) {

			e.printStackTrace();

		}

		return item;

	}

	public ArrayList<JSONItem> parse() {

		ArrayList<JSONItem> resultado = new ArrayList<JSONItem>();
		try {
			JSONObject root = new JSONObject(mJSONString);

			if (root.has("results")) {

				JSONArray array = root.getJSONArray("results");

				for (int i = 0; i < array.length(); i++) {

					JSONObject nodo = array.getJSONObject(i);

					JSONItem item = ParseNode(nodo);

					if (item != null)
						resultado.add(item);
				}

				return resultado;

			} else
				return null;
		} catch (JSONException e) {
			e.printStackTrace();

			return null;
		}

	}

}
