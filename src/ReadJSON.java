import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ReadJSON {

	private ReadJSON()
	{
		
	}
	
	public static JsonArray read(String filePath) {
		// Parser parses the JSON file into a JSON tree
		JsonParser parser = new JsonParser();
		try {
			JsonElement jsontree = parser.parse(new FileReader(filePath));
			// Convert the JSON tree to JSON object
			JsonObject jo = jsontree.getAsJsonObject();
			JsonArray ja = jo.getAsJsonArray("patient_readings");

			return ja;
		} catch(FileNotFoundException e) {
			System.out.println("File not found.");
			return null;
		} catch(JsonSyntaxException e ) {
			System.out.println("File could not be loaded.");
			return null;
		} catch(JsonIOException e) {
			System.out.println("File could not be loaded.");
			return null;
		}	
	}
}
