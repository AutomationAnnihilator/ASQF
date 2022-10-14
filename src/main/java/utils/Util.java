package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Util {
	
		//returns response in XML format
			public static XmlPath rawToXML(Response r) {
			String response = r.asString();
			XmlPath x = new XmlPath(response);
			return x;
			}

			//returns response in JSON format
			public static JsonPath rawToJson(Response res) {
			String response = (res).asString();
			JsonPath x = new JsonPath(response);
			return x;
			}
			
		
}
