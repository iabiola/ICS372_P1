import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Application{
	private List <Patient> patients;
	private List <Reading> reading;
	private List <Clinic> clinic;
	


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
			if (c.getClinicName().equals(clinicName) && c.getClinicID().equals(ClinicID)) {
				clinic.add(new Clinic(clinicName, clinicID));
				return "New clinic has been added ";
			}
		}
		return "Clinic could not be added because we the Clinic is not on our record ";
	}
	
	public String addPatient(String patient_id, boolean patient_active ) {
		patients.add(new Patient(patient_id, patient_active));
		return "Patient has been added";
				
	}
	
}