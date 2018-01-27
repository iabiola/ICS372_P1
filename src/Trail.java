package TrailClass;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;

public class Trail 
{     
	
	public static String loadFile()
	{
		JSONStringer parser = new JSONStringer();
	        try {     
	            Object jsonObj = (parser).Parser(new FileReader("file.json"));

	            JSONObject jsonObject =  (JSONObject) jsonObj;

	            int id_num = (int) jsonObject.get("patient_id");
	            String reading_type = (String) jsonObject.get("weight");

	            int reading_id = (int) jsonObject.get("reading_id");

	            // loop through array
	            JSONArray patient_readings = (JSONArray) jsonObject.get("patient_readings");
	            Iterator<String> iterator = ((Object) patient_readings).iterator();
	            while (iterator.hasNext()) {
	             System.out.println(iterator.next());
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }
		
	}
	
		
		  

