package es.android.wifiandogepeto;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class JsonParsing {


	private String mJsonString;

	JsonParsing(String json){
		super();
		
		if (json ==null)
			throw new NullPointerException();
		
		mJsonString = json;
	}

	public ArrayList<ItemJs> parser(){
		ArrayList<ItemJs> lista =  new ArrayList<ItemJs>();
		try {
			JSONObject root = new JSONObject(mJsonString);
			if(root.has("results"))
			{
				JSONArray array = root.getJSONArray("results");
				for (int i=0; i < array.length(); i++)
				{
					JSONObject nodo = array.getJSONObject(i);
					ItemJs item = parseNode(nodo);
					if (item != null)
						lista.add(item);
				}
			}
			else
				return null;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
		return lista;
	}	

	private ItemJs parseNode(JSONObject nodo)
	{
		ItemJs ijs = new ItemJs();

		try{
			if(nodo.has("since"))
				ijs.setDate(nodo.getString("since"));
			if (nodo.has("name"))
				ijs.setNombre(nodo.getString("name"));
			if(nodo.has("external_info"))
			{
				JSONObject info = nodo.getJSONObject("external_info");
				if(info.has("photo_thumb"))
					ijs.setFotoThumb(info.getString("photo_thumb"));
				if(info.has("photo_url"))
					ijs.setFotoUrl(info.getString("photo_url"));
				if(info.has("info_url"))
					ijs.setInfoUrl(info.getString("info_url"));
			}
			return ijs;
		} catch (JSONException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		

	}
	

}
