package es.cursoandorid.yncat;

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
			if(nodo.has("created_at"))
				ijs.setDate(nodo.getString("created_at"));
			if (nodo.has("from_user_name"))
				ijs.setNombre(nodo.getString("from_user_name"));
			if(nodo.has("profile_image_url"))
				ijs.setFotoUrl(nodo.getString("profile_image_url"));
			if(nodo.has("text"))
				ijs.setTexto(nodo.getString("text"));
			return ijs;
		} catch (JSONException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		

	}
	

}
