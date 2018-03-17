
public class FileReader {

	private FileReader() {
		return;
	}

	public Object read(String fileName)
	{
		if (fileName.toLowerCase().contains("xml"))
		{
			return ReadJSON.read(fileName);
		} else if (fileName.toLowerCase().contains("json"))
		{
			return ReadXML.read(fileName);
		} else {
			System.out.println("File type could not be determined: please select a valid JSON file ending in \".json\", or a valid XML file ending in \".xml\".");
		}
		return null;
	}
	
	public void deserialize(Object e)
	{
		
	}
	
}
