

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
		return "Hello World!";
	}
	
	public String beginStudy(String patientID)
	{
		return "Hello World!";
	}
	
	public String saveFile(String filePathToSave)
	{
		return "Hello World!";
	}
	
	public void addReading(String patient_id, String reading_type, 
			String reading_id,long reading_date)
	{
		
		for( int i = 0; i < patients.size(); i++) {
			
			
			
			if(patients.get(i).getPatient_id() == patient_id && patients.get(i).isPatient_active()){
				Record newRecord = new Record(patient_id,reading_type,reading_id,reading_date);
				
				records.add(newRecord);
			System.out.println("New record added");
				
				
			}
			else {
				System.out.println("Patient not in the system or inactive for the trial");
			}
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
			
		
			
		
  
		
	
	


