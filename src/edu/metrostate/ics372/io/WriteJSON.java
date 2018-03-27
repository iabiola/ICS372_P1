package edu.metrostate.ics372.io;
import java.io.FileWriter;
import com.google.gson.JsonArray;

public class WriteJSON {

	private WriteJSON()
	{
		return;
	}
	
	public static String write(JsonArray JSONStructure, String filePath) {

		// Create a new JSON file from the string of JSON object
		try {	
			FileWriter writer = new FileWriter(filePath);
			writer.write(JSONStructure.toString());
			writer.close();
			return "File successfully exported.";
		} catch(Exception e) {
			e.printStackTrace();
			return "File could not be exported.";
		}	
	}
	
}
