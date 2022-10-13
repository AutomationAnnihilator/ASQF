package commons;

import org.apache.commons.lang.RandomStringUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Reusable {
	
	//returns response in XML format
			public static XmlPath rawToXML(Response r) {
			String respon = r.asString();
			XmlPath x = new XmlPath(respon);
			return x;
			}

			//returns response in JSON format
			public static JsonPath rawToJson(Response res) {
			String respon = (res).asString();
			JsonPath x = new JsonPath(respon);
			return x;
			}
			
		
}
