package edu.metrostate.ics372.domain;

import junit.framework.TestCase;

public class ClinicTest extends TestCase {

	public void test() 
	{
		@SuppressWarnings("unused")
		Clinic newClinic = new Clinic("Tempa");
		 
		// test getClinicname method
		@SuppressWarnings("unused")
		String clinicName = newClinic.getClinicID();
		
		//test setClinicName method
		newClinic.setClinicID("Highland");
		
		//test getClinicID method
		@SuppressWarnings("unused")
		String getClinicId = newClinic.getClinicID();
		
		//test setClinicID method
		newClinic.setClinicID("5399");
		
		// test toString method
		newClinic.toString();
	}

}