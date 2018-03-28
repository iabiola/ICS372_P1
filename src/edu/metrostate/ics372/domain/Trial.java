package edu.metrostate.ics372.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trial implements Serializable {
	
	private static final long serialVersionUID = 8968341436485461953L;
	private List<Patient> patients = new ArrayList<Patient>();
	private List<Reading> readings = new ArrayList<Reading>();
	private List <Clinic> clinics = new ArrayList<Clinic>();
	private static Trial instance = null;
	
	public Trial() {}
	
	public static Trial getInstance() {
		if (instance == null) {
			instance = new Trial();		
		}	
		return instance;
	}
	
	public List<Patient> getPatients() { return patients; }
	public List<Reading> getReadings() { return readings; }
	public List<Clinic> getClinics() { return clinics; }
	
	public void setPatients(List<Patient> newPatients) { patients = newPatients; }
	public void setReadings(List<Reading> newReadings) { readings = newReadings; }
	public void setClinics(List<Clinic> newClinics) { clinics = newClinics; }
	
}