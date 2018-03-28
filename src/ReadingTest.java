import static org.junit.Assert.*;

import org.junit.Test;

import edu.metrostate.ics372.domain.Reading;

public class ReadingTest {

	@Test
	public void test() {
		Reading myReading = new Reading("1001","Temp","T001","98.8",
				03/24/18, "Fairview");
		String readingID = myReading.getPatient_id();
		assertEquals("1001",readingID);
		
		String readingType = myReading.getReading_type();
		assertEquals("Temp", readingType);
		
		String reading_ID = myReading.getReading_id();
		assertEquals("T001",reading_ID);
		
		String readingClinic = myReading.getClinic_id();
		assertEquals("Fairview", readingClinic);
		
		long readingDate = myReading.getReading_date();
		assertEquals(03/24/18, readingDate);
		
		String readingValue = myReading.getReading_value();
		assertEquals("98.8", readingValue);
		
		myReading.setPatient_id("1002");
		myReading.setReading_type("Temp");
		myReading.setReading_clinic("Clinic02");
		myReading.setReading_date(03/11/18);
		myReading.setReading_value("Weight");
		myReading.setReading_id("W01");
		
		
		
		
		
				
	}

}
