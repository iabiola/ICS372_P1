import java.util.ArrayList;
import java.util.List;

public class Trial implements java.io.Serializable {
	
	private List<Patient> patients;
	private List<Reading> records;
	private List <Clinic> clinic;
	private static Trial trial;
	
	public Trial() {
		
	}
	
	
	
	private static Trial getInstance() {
		if (trial == null) {
			synchronized(trial){
				
				if (trial == null) {
					trial = new Trial();
				}
			}
		}
		
		return trial;
	}
	
	

	
	
	

}
