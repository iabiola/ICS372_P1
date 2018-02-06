import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Trial {

	static private List<Patient> patients;
	static private List<Record> records;

	public Trial() {
		patients = new ArrayList<Patient>();
		records = new ArrayList<Record>();
	}

	public String loadFile(String filePath) {
		// Parser parses the JSON file into a JSON tree
		JsonParser parser = new JsonParser();
		try {
			JsonElement jsontree = parser.parse(new FileReader(filePath));
			// Convert the JSON tree to JSON object
			JsonObject jo = jsontree.getAsJsonObject();
			JsonArray ja = jo.getAsJsonArray("patient_readings");
			
			// Extract all JSON objects from the JSON array
			// and put them into the records array
			for(Object o : ja) {
				JsonObject reading = (JsonObject) o;
				String patient_id = reading.get("patient_id").getAsString();
				String reading_type = reading.get("reading_type").getAsString();
				String reading_id = reading.get("reading_id").getAsString();
				String reading_value = reading.get("reading_value").getAsString();
				long reading_date = reading.get("reading_date").getAsLong();
				
				records.add(new Record(
					patient_id, reading_type, reading_id, reading_value, reading_date));
			}
			return "File successfully loaded.";
		} catch(FileNotFoundException e) {
			return "File not found.";
		} catch(JsonSyntaxException e ) {
			return "File could not be loaded.";
		} catch(JsonIOException e) {
			return "File could not be loaded";
		}	
	}

	public String saveFile(String filePath) {
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		Gson gson = new Gson();
		
		// Convert the Record objects into JSON strings
		// in order to populate JSON array
		for(Record record : records) {
			String jsonString = gson.toJson(record);
			ja.add(jsonString);
		}
		
		// Add the JSON array as a value to the JSON object
		jo.add("patient_readings", ja);

		// Create a new JSON file from the string of JSON object
		try {	
			FileWriter writer = new FileWriter(filePath);
			writer.write(jo.toString());
			writer.close();
			return "File successfully saved.";
		} catch(Exception e) {
			e.printStackTrace();
			return "File could not be saved.";
		}	
	}

	public String addReadnig(String patient_id, String reading_type, String reading_id, String reading_value, long reading_date) {
		// If the patient exists on record and is set active
		// then add the reading to that patient
		for(Patient patient : patients) {
			if(patient.getPatient_id() == patient_id && patient.isPatient_active()) {
				records.add(new Record(patient_id, reading_type, reading_id, reading_value, reading_date));
				return "New record has been added to patient " + patient_id + ".";
			}
		}
		return "Reading could not be added: patient " + patient_id + " is not on record or is inactive.";
	}

	public String beginStudy(String patient_id) {
		// If the patient exists on record then set the patient to active
		// else patient does not exist then add the patient and set to active
		for(Patient patient : patients) {
			if(patient_id == patient.getPatient_id()) {
				patient.setPatient_active(true);
				return "Patient " + patient_id + " has started trial.";
			}
		}
		patients.add( new Patient(patient_id, true) );
		return "Patient " + patient_id + " has been added and has started trial.";
	}

	public String endStudy(String patient_id) {
		// If the patient exists on record
		// then set the patient to inactive
		for(Patient patient : patients) {
			if(patient_id == patient.getPatient_id()) {
				patient.setPatient_active(false);
				return "Patient " + patient_id + " has ended trial.";
			}
		}
		return "Patient " + patient_id + " has not been found.";
	}
}