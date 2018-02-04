

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Trial {
	

	ArrayList<Patient> patients = new ArrayList<Patient>();
	ArrayList<Record> records = new ArrayList<Record>();
	
	public Trial() {}

	public String endStudy(String patientID)
	{
		/*
		 *  Set patient_active = False for Patient in patients with
		 *  patient_id matching patientID parameter.
		 *  
		 *  Return useful String for console output.
		 */
		return "Hello World!";
	}
	
	public String beginStudy(String patientID)
	{
		/*
		 *  Set patient_active = True for Patient in patients with
		 *  patient_id matching patientID parameter.
		 *  
		 *  Return useful String for console output.
		 */
		return "Hello World!";
	}
	
	public String saveFile(String filePathToSave)
	{
		
		// Output JSON file with records.
		// Return useful String for console output.
		return "Hello World!";
		try (FileWriter file = new FileWriter(" ")) {
			file.write(obj.toString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		
	}
	
	public String addReading(String patient_id, String reading_type, 
			String reading_id,long reading_date)
	{
		Boolean result = false;
		for( int i = 0; i < patients.size(); i++) {
			if(patients.get(i).getPatient_id() == patient_id && patients.get(i).isPatient_active())
			{
				Record newRecord = new Record(patient_id,reading_type,reading_id,reading_date);
				
				records.add(newRecord);
				result = true;
				break;
			}
		}
		if ( result )
		{
			return "New record added";
		} else {
			return "Patient not in the system or inactive for the trial";
		}
	}
		
		
	public static String loadFile(String loadPath)
	{
		JSONStringer parser = new JSONStringer();
		try 
		{     
		    Object jsonObj = (parser)Parser(new FileReader("file.json"));

		    JSONObject jsonObject =  (JSONObject) jsonObj;

		    int id_num = (int) jsonObject.get("patient_id");
		    String reading_type = (String) jsonObject.get("weight");

		    int reading_id = (int) jsonObject.get("reading_id");

		    // loop through array
		    JSONArray patient_readings = (JSONArray) jsonObject.get("patient_readings");
		    Iterator<String> iterator = ((Object) patient_readings).iterator();
		    while (iterator.hasNext())
		    {
		    	System.out.println(iterator.next());
		    }
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}
		
		
}
			
		
			
		
  
		
	
	


