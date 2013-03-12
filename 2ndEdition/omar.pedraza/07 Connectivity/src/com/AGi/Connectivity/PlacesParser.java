//
//  PlacesParser.java
//  07 Connectivity
//
//  Created by Omar Pedraza on 3/8/13.
//  Copyright (c) 2013 American Greetings Interactive. All rights reserved.
//

package com.AGi.Connectivity;

import android.net.Uri;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlacesParser {
    private static String mString = null;

    public PlacesParser(String string) {
        super();

        if (string == null) {
            throw new NullPointerException();
        }

        mString = string;
    }

    public ArrayList<Place> parseJSON() {
        ArrayList<Place> places = new ArrayList<Place>();
        try {
            JSONObject root = new JSONObject(mString);

            if (root.has("results")) {
                JSONArray results = root.getJSONArray("results");
                for (Integer i = 0; i < results.length(); i++) {
                    JSONObject node = results.getJSONObject(i);
                    places.add(parseNode(node));
                }
            }
            else {
                places = null;
            }
        }
        catch (JSONException e) {
            places = null;
        }

        return places;
    }

    private Place parseNode(JSONObject node) {
        Place place = new Place();

        try {
            if (node.has("since")) {
                place.setDate(node.getString("since"));
            }

            if (node.has("name")) {
                place.setName(node.getString("name"));
            }

            if (node.has("external_info")) {
                JSONObject info = node.getJSONObject("external_info");

                if (info.has("info_url")) {
                    place.setInfo(Uri.parse(info.getString("info_url")));
                }

                if (info.has("photo_thumb")) {
                    place.setThumbnail(Uri.parse(info.getString("photo_thumb")));
                }
            }
        }
        catch (JSONException e) {
        }

        return place;
    }
}
