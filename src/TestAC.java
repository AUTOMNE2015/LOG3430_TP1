import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestAC {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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

	@Test
	public void testAC1() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac1.properties", "addition", 10, 2, 0, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC2() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac2.properties");
			suite = new SuiteChainee("testac2.properties", "addition", 10, 2, 0, false);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC3() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac3.properties", "addition", 10, 2, 1, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC4() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac4.properties", "addition", 10, 2, 1, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC5() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac5.properties", "addition", 10, 2, 2, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC6() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac6.properties", "addition", 10, 2, 2, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC7() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac7.properties", "addition", 10, 2, 5, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC8() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac8.properties", "addition", 10, 2, 5, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC9() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac9.properties", "soustraction", 10, 2, 0, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC10() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac10.properties", "soustraction", 10, 2, 0, false);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC11() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac11.properties", "soustraction", 10, 2, 1, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC12() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac12.properties", "soustraction", 10, 2, 1, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC13() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac13.properties", "soustraction", 10, 2, 2, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC14() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac14.properties", "soustraction", 10, 2, 2, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC15() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac15.properties", "soustraction", 10, 2, 5, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC16() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac16.properties", "soustraction", 10, 2, 5, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC17() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac17.properties", "multiplication", 10, 2, 0, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC18() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac18.properties", "multiplication", 10, 2, 0, false);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC19() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac19.properties", "multiplication", 10, 2, 1, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC20() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac20.properties", "multiplication", 10, 2, 1, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC21() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac21.properties", "multiplication", 10, 2, 2, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC22() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac22.properties", "multiplication", 10, 2, 2, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC23() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac23.properties", "multiplication", 10, 2, 5, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC24() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac24.properties", "multiplication", 10, 2, 5, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC25() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac25.properties", "division", 10, 2, 0, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC26() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac26.properties", "division", 10, 2, 0, false);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC27() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac27.properties", "division", 10, 2, 1, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC28() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac28.properties", "division", 10, 2, 1, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC29() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac29.properties", "division", 10, 2, 2, true);
			assertTrue(suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC30() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac30.properties", "division", 10, 2, 2, false);
			assertTrue(!suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = Exception.class)
	public void testAC31() throws Exception {
		ISuiteChainee suite;
		suite = new SuiteChainee("testac31.properties", "division", 10, 2, 5, true);

	}
	
	@Test (expected = Exception.class)
	public void testAC32() throws Exception {
		ISuiteChainee suite;
		suite = new SuiteChainee("testac32.properties", "division", 10, 2, 5, false);

	}
}
