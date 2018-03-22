import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Application{
	private List <Patient> patients;
	private List <Reading> reading;
	private List <Clinic> clinic;
	
	JsonObject jo = new JsonObject();
	JsonArray j = new JsonArray();
	Gson gson = new Gson();

	public Application() {
		patients = new ArrayList<Patient>();
		reading = new ArrayList<Reading>();
		clinic = new ArrayList<Clinic>();
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
							JsonObject record = (JsonObject) o;
							String patient_id = record.get("patient_id").getAsString();
							String reading_type = record.get("reading_type").getAsString();
							String reading_id = record.get("reading_id").getAsString();
							String reading_value = record.get("reading_value").getAsString();
							long reading_date = record.get("reading_date").getAsLong();
							String reading_clinic = record.get("reading_clinic").getAsString();
							reading.add(new Reading(patient_id, reading_type, reading_id, reading_value, reading_date, reading_clinic));
							
							// Ensure that patient_id exists as a patient and patient is active:
							beginStudy(patient_id);
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
		

		// Just dump the entire array to a Json object.
		jo.add("patient_readings", gson.toJsonTree(reading));
		j.add(jo);

		// Create a new JSON file from the string of JSON object
		try {	
			FileWriter writer = new FileWriter();
			// Write the file to the array using the method in the FileWriter class.
			writer.write(j, filePath);
			
			return "File successfully saved.";
		} catch(Exception e) {
			e.printStackTrace();
			return "File could not be saved.";
		}	
	}
	
	public String saveData() {
		try {	
			FileWriter writer = new FileWriter();
			// Write the file to the array using the method in the FileWriter class and serialize it 
			writer.serialize(j);
			
			return "Data successfully saved.";
		} catch(Exception e) {
			e.printStackTrace();
			return "Data could not be saved.";
		}	
		
	}
	
	
	
	public void printReadings() {
		for (Reading r : reading) {
			System.out.println(r.toString());
		}
	}
	
	public String beginStudy(String patient_id) {
		// If the patient exists on record then set the patient to active
		// else patient does not exist then add the patient and set to active
		for(Patient patient : patients) {
			if(patient.getPatient_id().equals(patient_id)) {
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
			if(patient.getPatient_id().equals(patient_id)) {
				patient.setPatient_active(false);
				return "Patient " + patient_id + " has ended trial.";
			}
		}
		return "Patient " + patient_id + " has not been found.";
	}
	
	public String addReading(String patient_id, String reading_type, String reading_id, String reading_value, long reading_date, String reading_clinic) {
		// If the patient exists on record and is set active
		// then add the reading to that patient
		for(Patient p : patients) {
			if(p.getPatient_id().equals(patient_id) && p.isPatient_active()) {
				reading.add(new Reading(patient_id, reading_type, reading_id, reading_value, reading_date, reading_clinic));
				return "New record has been added to patient " + patient_id + "at the clinic " + reading_clinic +".";
			}
		}
		return "Reading could not be added: patient " + patient_id + " is not on record or is inactive.";
	}
	
	public String addClinic(String clinicName, String clinicID) {
		for (Clinic c: clinic) {
			if (c.getClinicName().equals(clinicName) && c.getClinicID().equals(clinicID)) {
				clinic.add(new Clinic(clinicName, clinicID));
				return "New clinic has been added ";
			}
		}
		return "Clinic could not be added because we the Clinic is not on our record ";
	}
	
	public String addPatient(String patient_id ) {
		patients.add(new Patient(patient_id, true));
		return "Patient has been added";
				
	}
	
}