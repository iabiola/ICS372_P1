package edu.metrostate.ics372.domain;
import java.io.Serializable;

public class Clinic implements Serializable {
	
	private static final long serialVersionUID = -7837407116511669613L;
	private String clinicID;
		
	public Clinic(String clinicID) {
		this.clinicID = clinicID;
	}

	public String getClinicID() { return clinicID; }

	public void setClinicID(String clinicID) { this.clinicID = clinicID; }

	public String toString() {
		return "Clinic [clinic_id = " + clinicID + "]";
	}
}
