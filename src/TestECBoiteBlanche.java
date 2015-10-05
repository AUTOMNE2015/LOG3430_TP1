import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author datraa
 *
 */
public class TestECBoiteBlanche {


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	


	@Test
	public void testEC1() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testec1.properties", "addition", 1, 2, 5, true);
			assertTrue("testEC1 : La liste n'est pas valide.", suite.toString().equals("1, 2, 3, 5, 8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testEC2() {
		try{
		ISuiteChainee suite = new SuiteChainee("testec2.properties", "soustraction", 2, 1, 5, true);
		assertTrue("testEC2 : La liste n'est pas valide.", suite.toString().equals("2, 1, 1, 0, 1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEC3() {
		try{

			String vieuxContenu = loadOldContent("testec3.properties");
			ISuiteChainee suite = new SuiteChainee("testec3.properties", "multiplication", 1, 2, 5, false);
			assertTrue("testEC3 : La liste n'est pas valide.", suite.toString().equals(vieuxContenu + "1, 2, 2, 4, 8"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = Exception.class)
	public void testEC4() throws Exception {
		ISuiteChainee suite = new SuiteChainee("testec4.properties", "division", 20, 0, 15, true);		
	}
	
	@Test(expected = Exception.class)
	public void testEC5() throws Exception {
		ISuiteChainee suite = new SuiteChainee("", "jenesuispasunoperateur", 1, 2, 0, true);
	}
	
	
	@Test
	public void testEC6() throws Exception {
		ISuiteChainee suite = new SuiteChainee("testec6.properties", "division", 20, 5, 3, true);		
	}
	
	@Test(expected = Exception.class)
	public void testEC7() throws Exception {
		ISuiteChainee suite = new SuiteChainee("testec7.properties", "jenesuispasunoperateur", 1, 2, 5, true);
	}
	
	@Test(expected = Exception.class)
	public void testEC8() throws Exception {
		ISuiteChainee suite = new SuiteChainee("testec8.properties", "addition", 1, 2, -1, true);
	}
	
	
	private String loadOldContent(String filename){
		// Essai d'ouvrir un stream du fichier.
        Properties properties = new Properties();
		File file = new File(filename);
		FileInputStream fileInput;
		try 
		{	
			fileInput = new FileInputStream(file);
			properties.load(fileInput);
		} 
		catch (FileNotFoundException e)
		{
			// Fichier introuvable, on va créer un nouveau fichier plus tard.
			System.out.println("Fichier introuvable.");
			return null;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
		String oldContent = properties.getProperty("contenu");
		if(!(oldContent == null || oldContent.isEmpty()))
		{
			oldContent += ", ";
		}

		return oldContent;
	}
	

}
