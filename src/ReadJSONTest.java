import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import junit.framework.TestCase;

public class ReadJSONTest extends TestCase{

	// test read method of type jArray.
	@Test
	public void test() throws JsonIOException, JsonSyntaxException, FileNotFoundException 
	{
		JSONArray obj2 = new JSONArray();
		JsonParser parse = new JsonParser();
		JsonElement jTree = parse.parse("filePath");
        obj2.put("record_id");
        obj2.put("patient");
		obj2.put(parse);
		obj2.put(jTree);
		Assert.assertFalse(jTree.isJsonNull());
    
	}
  
}
