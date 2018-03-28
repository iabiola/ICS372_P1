package edu.metrostate.ics372.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.json.JSONException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import edu.metrostate.ics372.domain.Trial;

public class FileReader {

	private FileReader() {
		return;
	}

	public static Object read(String fileName) throws JsonIOException, JsonSyntaxException, IOException, JSONException
	{
		if (fileName.toLowerCase().contains("xml"))
		{
			return ReadXML.read(fileName);
		} else if (fileName.toLowerCase().contains("json"))
		{
			return ReadJSON.read(fileName);
		} else {
			System.out.println("File type could not be determined: please select a valid JSON file ending in \".json\", or a valid XML file ending in \".xml\".");
		}
		return null;
	}
	
	public static Object deserialize() throws ClassNotFoundException, IOException
	{
        FileInputStream fis = new FileInputStream("state.ser");
        ObjectInputStream oin = new ObjectInputStream(fis);
         
        Object thisTrial = oin.readObject();
         
        fis.close();
        oin.close();
        
        return thisTrial;
	}
	
}
