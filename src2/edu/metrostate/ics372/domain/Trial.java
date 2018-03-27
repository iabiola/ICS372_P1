package edu.metrostate.ics372.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trial implements Serializable {
	
	private static final long serialVersionUID = 8968341436485461953L;
	private List<Patient> patients;
	private List<Reading> readings;
	private List <Clinic> clinics;
	private static Trial trial = null;
	
	public Trial() {
		this.patients = new ArrayList<Patient>();
		this.readings = new ArrayList<Reading>();
		this.clinics = new ArrayList<Clinic>();
	}
	
	public static Trial getInstance() {
		if (trial == null) {
			synchronized(trial){
				if (trial == null) {
					trial = new Trial();
				}
			}
		}	
		return trial;
	}
	
	public List<Patient> getPatients() { return patients; }
	public List<Reading> getReadings() { return readings; }
	public List<Clinic> getClinics() { return clinics; }
}