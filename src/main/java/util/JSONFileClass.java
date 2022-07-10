package util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriverException;

@SuppressWarnings("serial")
public class JSONFileClass extends WebDriverException implements Serializable {
	public JSONObject readJson() throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader("./src/test/resources/JsonFiles/url.json");
		Object obj = jsonParser.parse(reader);
		JSONObject url = (JSONObject) obj;
		return url;
	}
}
