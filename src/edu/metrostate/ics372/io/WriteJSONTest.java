import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import junit.framework.TestCase;

public class WriteJSONTest extends TestCase{
	
	// this folder gets cleaned up automatically by JUnit
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
  

	// test the write method.
	@Test
	public void testWrite(JSONArray j, String f) throws IOException, JSONException{

	String filePath = null;
	final FileWriter fw = new FileWriter(filePath);
	BufferedWriter bw = new BufferedWriter(fw);

	// Create a temporary file.
    @SuppressWarnings("unused")
	File tempFile = tempFolder.newFile(filePath);
  
    // poplute JSon array
	JSONArray Jstruc = new JSONArray();
    Jstruc.put("PatientName");
	Jstruc.put("Patient_ID");
   	
    // write on the tempFile.
     bw.write(Jstruc.toString());
     
     Assert.assertEquals("PatientName", Jstruc.getString(0));
    
	
  }
	
	@Test
	public void testRule()
	{
		//fail("Not yet implemented");
	}
}

