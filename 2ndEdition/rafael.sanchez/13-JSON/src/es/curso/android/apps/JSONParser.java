package es.curso.android.apps;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;

/*{"results": 
	[{"external_info": 
		{  "photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/4204054.jpg", 
		   "info_url": "http://www.panoramio.com/photo/4204054", 
		   "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/4204054.jpg"
		}, 
		"name": "Universidad Rey Juan Carlos. Campus de Fuenlabrada", 
		"uploader": {"username": "", "type": ""}, 
		"tags": [], 
		"position": {"latitude": 40.284849000000001, "since": "2013-03-08 16:50:50.639038", "radius": 0.0, "longitude": -3.819283}, 
		"since": "26 August 2007", 
		"type": "photo", 
		"description": ""
		}, 
		{"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/5982071.jpg", "info_url": "http://www.panoramio.com/photo/5982071", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/5982071.jpg"}, "name": "CC Babilonia Barrio El Vivero Fuenlabrada", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.290565999999998, "since": "2013-03-08 16:50:50.639416", "radius": 0.0, "longitude": -3.824036}, "since": "18 November 2007", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/5982157.jpg", "info_url": "http://www.panoramio.com/photo/5982157", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/5982157.jpg"}, "name": "Paruqe del Lago Barrio El Vivero", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.292611999999998, "since": "2013-03-08 16:50:50.639831", "radius": 0.0, "longitude": -3.820935}, "since": "18 November 2007", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/5982187.jpg", "info_url": "http://www.panoramio.com/photo/5982187", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/5982187.jpg"}, "name": "CC Barrio El Vivero Fuenlabrada", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.291899999999998, "since": "2013-03-08 16:50:50.640132", "radius": 0.0, "longitude": -3.8229630000000001}, "since": "18 November 2007", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/17641912.jpg", "info_url": "http://www.panoramio.com/photo/17641912", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/17641912.jpg"}, "name": "Barrio del Hospital \\u002D Soleado \\u002D 08/01/2009", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.290655999999998, "since": "2013-03-08 16:50:50.640430", "radius": 0.0, "longitude": -3.8166319999999998}, "since": "09 January 2009", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/30942062.jpg", "info_url": "http://www.panoramio.com/photo/30942062", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/30942062.jpg"}, "name": "Barrio del Hospital 02 \\u002D Nevada 11\\u002D01\\u002D2010", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.289679999999997, "since": "2013-03-08 16:50:50.640754", "radius": 0.0, "longitude": -3.8161930000000002}, "since": "11 January 2010", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/80893283.jpg", "info_url": "http://www.panoramio.com/photo/80893283", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/80893283.jpg"}, "name": "Hospital Universitario de Fuenlabrada", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.286945000000003, "since": "2013-03-08 16:50:50.641052", "radius": 0.0, "longitude": -3.8173550000000001}, "since": "20 October 2012", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/3303116.jpg", "info_url": "http://www.panoramio.com/photo/3303116", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/3303116.jpg"}, "name": "tirando agua", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.285311999999998, "since": "2013-03-08 16:50:50.641352", "radius": 0.0, "longitude": -3.8161719999999999}, "since": "14 July 2007", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/80892935.jpg", "info_url": "http://www.panoramio.com/photo/80892935", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/80892935.jpg"}, "name": "Pabell\u00f3n de Oncolog\u00eda del Hospital Universitario de Fuenlabrada", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.287944000000003, "since": "2013-03-08 16:50:50.641651", "radius": 0.0, "longitude": -3.8164319999999998}, "since": "20 October 2012", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/80892909.jpg", "info_url": "http://www.panoramio.com/photo/80892909", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/80892909.jpg"}, "name": "Entrada a Urgencias del Hospital Universitario de Fuenlabrada", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.287207000000002, "since": "2013-03-08 16:50:50.641979", "radius": 0.0, "longitude": -3.8144369999999999}, "since": "20 October 2012", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/63503284.jpg", "info_url": "http://www.panoramio.com/photo/63503284", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/63503284.jpg"}, "name": "Barrio El Vivero Fuenlabrada", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.293333333299998, "since": "2013-03-08 16:50:50.642280", "radius": 0.0, "longitude": -3.8235000000000001}, "since": "14 December 2011", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/18961281.jpg", "info_url": "http://www.panoramio.com/photo/18961281", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/18961281.jpg"}, "name": "INSYTE", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.277037, "since": "2013-03-08 16:50:50.642579", "radius": 0.0, "longitude": -3.8267609999999999}, "since": "14 February 2009", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/19018249.jpg", "info_url": "http://www.panoramio.com/photo/19018249", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/19018249.jpg"}, "name": "barrio de la universidad", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.279001999999998, "since": "2013-03-08 16:50:50.642891", "radius": 0.0, "longitude": -3.8132540000000001}, "since": "15 February 2009", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/30941174.jpg", "info_url": "http://www.panoramio.com/photo/30941174", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/30941174.jpg"}, "name": "M\\u002D407 HELADA, 11 ENE 2010", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.292129000000003, "since": "2013-03-08 16:50:50.643192", "radius": 0.0, "longitude": -3.829186}, "since": "11 January 2010", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/30940322.jpg", "info_url": "http://www.panoramio.com/photo/30940322", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/30940322.jpg"}, "name": "LLEGADA DEL INVIERNO A LORANCA, 12 ENE 2010", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.291431000000003, "since": "2013-03-08 16:50:50.643821", "radius": 0.0, "longitude": -3.8311440000000001}, "since": "11 January 2010", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/42818517.jpg", "info_url": "http://www.panoramio.com/photo/42818517", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/42818517.jpg"}, "name": "SETOS", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.290309000000001, "since": "2013-03-08 16:50:50.644123", "radius": 0.0, "longitude": -3.8133880000000002}, "since": "27 October 2010", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/73793465.jpg", "info_url": "http://www.panoramio.com/photo/73793465", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/73793465.jpg"}, "name": "CENTRO ASISTENCIAL \\u002D SOCIO CULTURAL \\u002DDIRECTOR DE LA DEPENDENCIA ", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.289133999999997, "since": "2013-03-08 16:50:50.644421", "radius": 0.0, "longitude": -3.8192879999999998}, "since": "15 June 2012", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/49232431.jpg", "info_url": "http://www.panoramio.com/photo/49232431", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/49232431.jpg"}, "name": "oto\u00f1o", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.289150999999997, "since": "2013-03-08 16:50:50.644723", "radius": 0.0, "longitude": -3.8134679999999999}, "since": "09 March 2011", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/73793339.jpg", "info_url": "http://www.panoramio.com/photo/73793339", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/73793339.jpg"}, "name": "CENTRO DIRECTOR DE LA DEPENDENCIA 2.010\\u002D11 Arquitecto Juan Antonio Juara ", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.288153999999999, "since": "2013-03-08 16:50:50.645021", "radius": 0.0, "longitude": -3.8188209999999998}, "since": "15 June 2012", "type": "photo", "description": ""}, {"external_info": {"photo_thumb": "http://mw2.google.com/mw-panoramio/photos/small/5982117.jpg", "info_url": "http://www.panoramio.com/photo/5982117", "photo_url": "http://mw2.google.com/mw-panoramio/photos/medium/5982117.jpg"}, "name": "Parque Barrio El Vivero", "uploader": {"username": "", "type": ""}, "tags": [], "position": {"latitude": 40.292481000000002, "since": "2013-03-08 16:50:50.645357", "radius": 0.0, "longitude": -3.821536}, "since": "18 November 2007", "type": "photo", "description": ""}], "code": "200", "total_pages": "3", "page": "1", "elems": "20"}*/

public class JSONParser extends JSONObject {
	
	String  mJsonString = null;
	
	public JSONParser (String str) {
		mJsonString = str;
	}
	
	private PanoramioItem parseNode(JSONObject node) {
		PanoramioItem panoramioItem = new PanoramioItem();

		try {
			if (node.has("name"))
				panoramioItem.setName(node.getString("name"));
			if (node.has("since"))
				panoramioItem.setDate(node.getString("since"));
			if (node.has("external_info")) {
				JSONObject jInfo = node.getJSONObject("external_info");
				panoramioItem.setPhotoUrl(Uri.parse(jInfo.getString("photo_thumb")));
				panoramioItem.setInfoUrl(Uri.parse(jInfo.getString("info_url")));
			}
			return panoramioItem;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
		
	
	public ArrayList<PanoramioItem> parser() {
		
		ArrayList<PanoramioItem> resArray = new ArrayList<PanoramioItem>();
		
		JSONObject root;
		try {
			root = new JSONObject(mJsonString);

			if (root.has("results")) {
				JSONArray array = root.getJSONArray("results");
				for (int i = 0; i < array.length(); i++) {
					JSONObject nodo = array.getJSONObject(i);
					PanoramioItem rafa = parseNode(nodo);
					if (rafa != null) {
						resArray.add(rafa);
					}
				}
			} else
				return null;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return resArray;
	}
}
