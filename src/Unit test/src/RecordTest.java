import junit.framework.TestCase;

/**
 * Unit test for the Record class.
 *
 */
public class RecordTest extends TestCase 
{

	public void test() 
	{
		//test Record class constructor
			Record newRecord = new Record("231","typeA","34qw",03/20/18);
			
			// test getPatient_id
			@SuppressWarnings("unused")
			String getPatientID = newRecord.getPatient_id();
			
			// test setPatient_id
			newRecord.setPatient_id("3455");
			
			// test getReadingType
			@SuppressWarnings("unused")
			String rdngTyp = newRecord.getReading_type();
			
			// test setReadingType
			newRecord.setReading_type("type x");
			
			// test getReadingID
			@SuppressWarnings("unused")
			String setRdngID = newRecord.getReading_id();
			
			//test setReadingID
			newRecord.setReading_id("334x");
			
			// test getReadingDate
			@SuppressWarnings("unused")
			long getRdngDate = newRecord.getReading_date();
			
			// test setReadingDate
			newRecord.setReading_date(03/20/18);
			
			//test the toString method for the Record class.
			@SuppressWarnings("unused")
			String Patient_toString = newRecord.toString(); 

	}

}
