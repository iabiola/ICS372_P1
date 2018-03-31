
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;


import junit.framework.TestCase;

public class FileReaderTest extends TestCase {

	// test the private constructor
    @Test
	public void testPrivateCons() throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
	{
    	 final Constructor<?>[] constructors = FileReader.class.getDeclaredConstructors();
    	    for (Constructor<?> constructor : constructors) {
    	        constructor.setAccessible(true);
    	    }
	}

	 // this folder gets cleaned up automatically by JUnit
			@Rule
			public TemporaryFolder tmpFolder = new TemporaryFolder();
			
	 // test the read method
	     @Test
	      public void testRead(String fileName) throws IOException{
	    	   File created = tmpFolder.newFile();
				 assertTrue(created.isFile());
				 assertTrue(created.canRead());
				 assertEquals(tmpFolder.getRoot(), created.getParentFile());
	      }
	     
 
		// test deserialize method
	    @SuppressWarnings("null")
	    @Test
	    public Object testDeserialize() throws IOException, ClassNotFoundException {
	    	 Object thisTrail = null;
	    	 FileInputStream fis = new FileInputStream("test.ser");
	    	 ObjectInputStream oin = new ObjectInputStream(fis);
	    	
	         Assert.assertTrue(thisTrail.equals(oin));
	         Assert.assertEquals(thisTrail, oin);
	         
			return null;

	     }

}
