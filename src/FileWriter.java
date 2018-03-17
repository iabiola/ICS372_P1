import com.google.gson.JsonArray;

public class FileWriter {

	private FileWriter() {
		return;
		// TODO Auto-generated constructor stub
	}

	public void write(JsonArray e, String fileName)
	{
		WriteJSON.write(e, fileName);
		return;
	}
	
	public void serialize(Object e)
	{
		
	}
	
}
