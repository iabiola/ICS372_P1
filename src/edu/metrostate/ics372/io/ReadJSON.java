package edu.metrostate.ics372.io;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ReadJSON {

	private ReadJSON()
	{
		
	}
	
	public static JsonObject read(String filePath) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		// Parser parses the JSON file into a JSON tree
		JsonParser parser = new JsonParser();
		JsonElement jsontree = parser.parse(new FileReader(filePath));
		// Convert the JSON tree to JSON object
		JsonObject jo = jsontree.getAsJsonObject();
		// JsonArray ja = jo.getAsJsonArray();
		
		//
		// System.out.println(jo.toString());
		//
		
		return jo;
	}
}
