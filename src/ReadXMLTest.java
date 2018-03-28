import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import junit.framework.TestCase;

public class ReadXMLTest extends TestCase {
	//test read method
	@SuppressWarnings("resource")
	@Test
	public  void testRead(String filePath) throws IOException, JSONException {
		
		String line;
		String str = "";
		JSONArray jArray = new JSONArray();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		final FileWriter fw = new FileWriter(filePath);
		@SuppressWarnings("unused")
		BufferedWriter bw = new BufferedWriter(fw);
		
		JSONObject object = XML.toJSONObject(str);
		JsonObject jObj = new JsonParser().parse(object.toString()).getAsJsonObject();
		JsonArray ja = jObj.getAsJsonArray();
		// populate Json array
		String expected = "my test";
		jArray.put(expected);
		
		
		// first write to a file.
		fw.write(jArray.toString());
		
		// Read from file
		while ((line = br.readLine()) != null) 
		{   
		        str+=line;  
		}
		br.close();
		String result = str;
		
		// compare the text the stored in the file.
		Assert.assertEquals(expected, result);
		Assert.assertEquals(result, ja);
		}
	
	 @Test
     public void test(){
	}
	}


