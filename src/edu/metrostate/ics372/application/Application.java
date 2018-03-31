package edu.metrostate.ics372.application;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;

import edu.metrostate.ics372.domain.*;
import edu.metrostate.ics372.io.*;

public class Application {
	
	public String loadFile(String filePath) {
		Date now = new Date();      
		Long currentTime = new Long(now.getTime()/1000);
		try {
			JsonObject jo = (JsonObject) FileReader.read(filePath);
			
			// We've read in a JsonObject from the file. See if it contains
			// a patient_readings array. If it does, import the readings in that array.
			JsonArray ja;
			try {
				ja = jo.get("patient_readings").getAsJsonArray(); 
				for(Object o : ja) {
					try {
						JsonObject reading = (JsonObject) o;
						// extract data from each reading
						String patient_id = reading.get("patient_id").getAsString();
						String reading_type = reading.get("reading_type").getAsString();
						String reading_id = reading.get("reading_id").getAsString();
						String reading_value = reading.get("reading_value").getAsString();
						long reading_date = reading.get("reading_date").getAsLong();
						String reading_clinic = reading.get("reading_clinic").getAsString();

						//
//						System.out.println(patient_id);
//						System.out.println(reading_type);
//						System.out.println(reading_id);
//						System.out.println(reading_value);
//						System.out.println(reading_date);
//						System.out.println(reading_clinic);
						//
						
						// populate the java objects						
						Trial.getInstance().getReadings().add( new Reading(patient_id, reading_type, reading_id, reading_value, reading_date, reading_clinic) );
						Trial.getInstance().getPatients().add( new Patient(patient_id, true) );
						Trial.getInstance().getClinics().add( new Clinic(reading_clinic) );	
					} catch (NullPointerException f) {
						System.out.println("An error occured: json missing data");
						System.out.println(o.toString());
						continue;
					} catch (Exception e) {
						System.out.println("Some other error occurred.");
						continue;
					}
				}
				return "File successfully loaded!";
			} catch (Exception e) {
				// Well, okay, so if we get here, there was no patient_readings key.
				// That probably means we got an XML file in the other file format.
				System.out.println("Formatting of this is not consistent with JSON file format. Proceeding in XML import mode.");
			}
			try {
				JsonObject rs = jo.get("ReadingSet").getAsJsonObject();
				String ClinicID = rs.get("Clinic").getAsJsonObject().get("id").toString();
				
				JsonArray ra = rs.get("Reading").getAsJsonArray();
				for (Object o : ra)
				{
//					System.out.println(o.toString());
					JsonObject reading = (JsonObject) o;
					String patient_id = reading.get("Patient").getAsString();
//					System.out.println(patient_id);
					String reading_type = reading.get("type").getAsString();
//					System.out.println(reading_type);
					String reading_id = reading.get("id").getAsString();
//					System.out.println(reading_id);
					Long reading_date = currentTime;
//					System.out.println(reading_date);
					String reading_clinic = ClinicID;
//					System.out.println(reading_clinic);				
					// Now we have to extract the Value, which may just be a Value, or
					// it may be the "content" field in the "value". Fail Through.
					String reading_value;
					try {
						reading_value = reading.get("Value").getAsJsonObject().get("content").getAsString();
					} catch (NullPointerException e) {
						reading_value = reading.get("Value").getAsString();
					} catch (IllegalStateException e) {
						reading_value = reading.get("Value").getAsString();
					}
//					System.out.println(reading_value);
					
					// populate the java objects						
					Trial.getInstance().getReadings().add( new Reading(patient_id, reading_type, reading_id, reading_value, reading_date, reading_clinic) );
					Trial.getInstance().getPatients().add( new Patient(patient_id, true) );
					Trial.getInstance().getClinics().add( new Clinic(reading_clinic) );	

				}
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return "Hello World.";			
		} catch (Exception e) {
			e.printStackTrace();
			return "File could not be loaded!";
		}
	}	
	
	 	public String loadData() {
		try {	
			Trial trial = (Trial) FileReader.deserialize();
			Trial.getInstance().setClinics(trial.getClinics());
			Trial.getInstance().setPatients(trial.getPatients());
			Trial.getInstance().setReadings(trial.getReadings());
			return "Data loaded!";
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
			return "No data to load";
		}
		return "";
	}
	
	
	public String saveFile(String filePath) {
		List<Reading> readings = Trial.getInstance().getReadings();
		
		JsonArray ja = new JsonArray();
		JsonObject jo = new JsonObject();
		Gson gson = new Gson();
		
		jo.add("patient_readings", gson.toJsonTree(readings));
		ja.add(jo);
		return FileWriter.write(ja, filePath);
	}
	
	public String saveData() {
		try {
			return FileWriter.serialize( Trial.getInstance() );
		} catch (IOException e) {
			return "Data could not be saved!";
		}
	}
	
	public String printReadings() {
		List<Reading> readings = Trial.getInstance().getReadings();
		String text = "";
		for(Reading r : readings) {
			text += (r.toString() + "\n");
		}
		return text;
	}
	
	public String beginStudy(String patient_id) {
		// If the patient exists on record then set the patient to active
		// else patient does not exist then add the patient and set to active
		List<Patient> patients = Trial.getInstance().getPatients();
		for(Patient patient : patients) {
			if(patient.getPatient_id().equals(patient_id)) {
				patient.setPatient_active(true);
				return "Patient " + patient_id + " has been activated!";
			}
		}
		patients.add( new Patient(patient_id, true) );
		return "Patient " + patient_id + " has been added and has been activated!";
	}

	public String endStudy(String patient_id) {
		// If the patient exists on record
		// then set the patient to inactive
		List<Patient> patients = Trial.getInstance().getPatients();
		for(Patient patient : patients) {
			if(patient.getPatient_id().equals(patient_id)) {
				patient.setPatient_active(false);
				return "Patient " + patient_id + " has been deactivated!";
			}
		}
		return "Patient " + patient_id + " could not be found!";
	}
	
	public String addReading(String patient_id, String reading_type, String reading_id, String reading_value, long reading_date, String reading_clinic) {
		// If the patient exists on record and is set active
		// then add the reading to that patient
		List<Patient> patients = Trial.getInstance().getPatients();
		List<Reading> readings = Trial.getInstance().getReadings();
		for(Patient p : patients) {
			if(p.getPatient_id().equals(patient_id) && p.isPatient_active()) {
				readings.add(new Reading(patient_id, reading_type, reading_id, reading_value, reading_date, reading_clinic));
				return "New reading has been added to patient " + patient_id + "at the clinic " + reading_clinic +".";
			}
		}
		return "Reading could not be added: patient " + patient_id + " is not on record or is inactive. Please add the patient " + patient_id + " first!";
	}
	
	public String addClinic(String clinicID) {
		List<Clinic> clinics = Trial.getInstance().getClinics();
		
				clinics.add(new Clinic(clinicID));
				return "New clinic has been added ";
			}
		
	
	public String addPatient(String patient_id ) {
		List<Patient> patients = Trial.getInstance().getPatients();
		patients.add(new Patient(patient_id, true));
		return "Patient has been added";
	}
}