import junit.framework.TestCase;

/**
 * 
 */

/**
 * Unit testing on Patient class
 *
 */
public class PatientTest extends TestCase {

	public void  test() 
	{
		//test patient class constructor
		Patient newPatient = new Patient("231",true);
		
		// test getPatient_id
		@SuppressWarnings("unused")
		String getPatient = newPatient.getPatient_id();
		
		// test setPatient_id
		newPatient.setPatient_id("3455");
		
		// test isPatientActive
		@SuppressWarnings("unused")
		boolean isActive = newPatient.isPatient_active();
		
		// test setPatientActive
		newPatient.setPatient_active(true);
		
		//test the toString method for the patient class.
		@SuppressWarnings("unused")
		String Patient_toString = newPatient.toString(); 
	}

}
