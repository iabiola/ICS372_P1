package edu.metrostate.ics372.io;

import com.google.gson.JsonArray;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileWriter {

	private FileWriter() {
		return;
		// TODO Auto-generated constructor stub
	}

	public static void write(JsonArray e, String fileName)
	{
		WriteJSON.write(e, fileName);
		return;
	}
	
	public static void serialize(Object e) throws IOException
	{
        //Saving of object in a file
        FileOutputStream fos = new FileOutputStream("state.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(e);
        oos.close();
        fos.close();
	}
	
}
