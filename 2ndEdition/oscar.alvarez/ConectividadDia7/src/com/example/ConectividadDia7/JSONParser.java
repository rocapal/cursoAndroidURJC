package com.example.ConectividadDia7;

import android.net.Uri;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    private String mJsonString;
    private ArrayList mJsonArray;


    public JSONParser(String urlString) {
        super();
        if (urlString == null)
            throw new NullPointerException();
        mJsonString = urlString;
    }

    public ArrayList<PlaceObject> parser()
    {
        mJsonArray = new ArrayList();
        try {
        JSONObject root = new JSONObject(mJsonString);
            if (root.has("results")) {
                JSONArray  jsonArray = root.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject nodo = jsonArray.getJSONObject(i);
                    PlaceObject placeObject = parseNodo(nodo);
                    if (placeObject != null)
                        mJsonArray.add(parseNodo(nodo));
                }
                return mJsonArray;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return mJsonArray;
    }

    private PlaceObject parseNodo (JSONObject nodo) {
        PlaceObject placeObject = new PlaceObject();

        try {
            if (nodo.has("name")) {
                placeObject.setmName(nodo.getString("name"));
            }
            if (nodo.has("since")) {
                placeObject.setmDate(nodo.getString("since"));
            }
            if (nodo.has("external_info")) {
                JSONObject jExternalInfo = nodo.getJSONObject("external_info");
                if (jExternalInfo.has("photo_thumb")) {
                    Uri myPhotoUri = Uri.parse(jExternalInfo.getString("photo_thumb"));
                    placeObject.setmPhotoUrl(myPhotoUri);
                }
                if (jExternalInfo.has("info_url")) {
                    Uri myInfoUri = Uri.parse(jExternalInfo.getString("info_url"));
                    placeObject.setmInformationUrl(myInfoUri);
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return placeObject;
    }
}
