package edu.metrostate.ics372.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FileWriterTest {
	
	/*JsonArray value = Json.createArrayBuilder()
    .add(Json.createObjectBuilder()
        .add("type", "home")
        .add("number", "212 555-1234"))
    .add(Json.createObjectBuilder()
        .add("type", "fax")
        .add("number", "646 555-4567"))
    .build();*/
	

	@Test
	void test() {
		FileWriter fw = new FileWriter();
		
		JsonArray ja = new JsonArray();
		
		//JsonObject jo = new JsonObject();
		ja.add("patient_id");
		ja.add("reading_type");
		ja.add("reading_value");
		
		
		String fileName =new String("temp.txt");
		fw.write(ja, fileName);
		
		
		//assertNull();
		/*JSONObject data = getRESTData("/friends/367.json");
		String expected = "{friends:[{id:123,name:\"Corby Page\"},{id:456,name:\"Carter Page\"}]}";
		JSONAssert.assertEquals(expected, data, false);
		*/
	}
	
	/*JSONArray jsonArray = new JSONArray();
    JSONObject j1 = new JSONObject();
    j1.put ("name", "John");
    JSONObject j2 = new JSONObject();
    j2.put ("name", "David");
    jsonArray.add(j1);
    jsonArray.add(j2);
    Stream<String> ss = jsonArray.stream().map (json->json.toString ());
    List<String> list = ss.collect (Collectors.toList ());
    System.out.println(list);*/

}
