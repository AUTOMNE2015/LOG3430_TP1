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
	
	@Test
	public void testAC1() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac1.properties", "addition", 10, 2, 0, true);
			assertTrue(suite.toString().equals(""));
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
			assertTrue(suite.toString().equals(vieuxContenu+""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC3() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac3.properties", "addition", 10, 2, 1, true);
			assertTrue(suite.toString().equals("10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC4() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac4.properties");
			suite = new SuiteChainee("testac4.properties", "addition", 10, 2, 1, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC5() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac5.properties", "addition", 10, 2, 2, true);
			assertTrue(suite.toString().equals("10, 2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC6() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac6.properties");
			suite = new SuiteChainee("testac6.properties", "addition", 10, 2, 2, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC7() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac7.properties", "addition", 10, 2, 5, true);
			assertTrue(suite.toString().equals("10, 2, 12, 14, 26"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC8() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac8.properties");
			suite = new SuiteChainee("testac8.properties", "addition", 10, 2, 5, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 2, 12, 14, 26"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC9() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac9.properties", "soustraction", 10, 2, 0, true);
			assertTrue(suite.toString().equals(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC10() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac10.properties");
			suite = new SuiteChainee("testac10.properties", "soustraction", 10, 2, 0, false);
			assertTrue(suite.toString().equals(vieuxContenu+""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC11() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac11.properties", "soustraction", 10, 2, 1, true);
			assertTrue(suite.toString().equals("10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC12() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac12.properties");
			suite = new SuiteChainee("testac12.properties", "soustraction", 10, 2, 1, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC13() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac13.properties", "soustraction", 10, 2, 2, true);
			assertTrue(suite.toString().equals("10, 2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC14() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac14.properties");
			suite = new SuiteChainee("testac14.properties", "soustraction", 10, 2, 2, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC15() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac15.properties", "soustraction", 10, 2, 5, true);
			assertTrue(suite.toString().equals("10, 2, 8, -6, 14"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC16() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac16.properties");
			suite = new SuiteChainee("testac16.properties", "soustraction", 10, 2, 5, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 2, 8, -6, 14"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC17() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac17.properties", "multiplication", 10, 2, 0, true);
			assertTrue(suite.toString().equals(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC18() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac18.properties");
			suite = new SuiteChainee("testac18.properties", "multiplication", 10, 2, 0, false);
			assertTrue(suite.toString().equals(vieuxContenu+""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC19() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac19.properties", "multiplication", 10, 2, 1, true);
			assertTrue(suite.toString().equals("10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC20() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac20.properties");
			suite = new SuiteChainee("testac20.properties", "multiplication", 10, 2, 1, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC21() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac21.properties", "multiplication", 10, 2, 2, true);
			assertTrue(suite.toString().equals("10, 2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC22() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac22.properties");
			suite = new SuiteChainee("testac22.properties", "multiplication", 10, 2, 2, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC23() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac23.properties", "multiplication", 10, 2, 5, true);
			assertTrue(suite.toString().equals("10, 2, 20, 40, 800"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC24() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac24.properties");
			suite = new SuiteChainee("testac24.properties", "multiplication", 10, 2, 5, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 2, 20, 40, 800"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC25() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac25.properties", "division", 10, 2, 0, true);
			assertTrue(suite.toString().equals(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC26() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac26.properties");
			suite = new SuiteChainee("testac26.properties", "division", 10, 2, 0, false);
			assertTrue(suite.toString().equals(vieuxContenu+""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC27() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac27.properties", "division", 10, 2, 1, true);
			assertTrue(suite.toString().equals("10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC28() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac28.properties");
			suite = new SuiteChainee("testac28.properties", "division", 10, 2, 1, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC29() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac29.properties", "division", 10, 2, 2, true);
			assertTrue(suite.toString().equals("10, 2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC30() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac30.properties");
			suite = new SuiteChainee("testac30.properties", "division", 10, 2, 2, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 2"));
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
	
	@Test
	public void testAC33() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac33.properties", "addition", 10, 0, 0, true);
			assertTrue(suite.toString().equals(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC34() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac34.properties");
			suite = new SuiteChainee("testac34.properties", "addition", 10, 0, 0, false);
			assertTrue(suite.toString().equals(vieuxContenu+""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC35() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac35.properties", "addition", 10, 0, 1, true);
			assertTrue(suite.toString().equals("10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC36() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac36.properties");
			suite = new SuiteChainee("testac36.properties", "addition", 10, 0, 1, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC37() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac37.properties", "addition", 10, 0, 2, true);
			assertTrue(suite.toString().equals("10, 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC38() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac38.properties");
			suite = new SuiteChainee("testac38.properties", "addition", 10, 0, 2, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC39() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac39.properties", "addition", 10, 0, 5, true);
			assertTrue(suite.toString().equals("10, 0, 10, 10, 20"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC40() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac40.properties");
			suite = new SuiteChainee("testac40.properties", "addition", 10, 0, 5, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 0, 10, 10, 20"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC41() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac41.properties", "soustraction", 10, 0, 0, true);
			assertTrue(suite.toString().equals(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC42() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac42.properties");
			suite = new SuiteChainee("testac42.properties", "soustraction", 10, 0, 0, false);
			assertTrue(suite.toString().equals(vieuxContenu+""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC43() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac43.properties", "soustraction", 10, 0, 1, true);
			assertTrue(suite.toString().equals("10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC44() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac44.properties");
			suite = new SuiteChainee("testac44.properties", "soustraction", 10, 0, 1, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC45() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac45.properties", "soustraction", 10, 0, 2, true);
			assertTrue(suite.toString().equals("10, 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC46() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac46.properties");
			suite = new SuiteChainee("testac46.properties", "soustraction", 10, 0, 2, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC47() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac47.properties", "soustraction", 10, 0, 5, true);
			assertTrue(suite.toString().equals("10, 0, 10, -10, 20"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC48() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac48.properties");
			suite = new SuiteChainee("testac48.properties", "soustraction", 10, 0, 5, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 0, 10, -10, 20"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC49() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac49.properties", "multiplication", 10, 0, 0, true);
			assertTrue(suite.toString().equals(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC50() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac50.properties");
			suite = new SuiteChainee("testac50.properties", "multiplication", 10, 0, 0, false);
			assertTrue(suite.toString().equals(vieuxContenu+""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC51() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac51.properties", "multiplication", 10, 0, 1, true);
			assertTrue(suite.toString().equals("10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC52() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac52.properties");
			suite = new SuiteChainee("testac52.properties", "multiplication", 10, 0, 1, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC53() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac53.properties", "multiplication", 10, 0, 2, true);
			assertTrue(suite.toString().equals("10, 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC54() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac54.properties");
			suite = new SuiteChainee("testac54.properties", "multiplication", 10, 0, 2, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC55() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac55.properties", "multiplication", 10, 0, 5, true);
			assertTrue(suite.toString().equals("10, 0, 0, 0, 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC56() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac56.properties");
			suite = new SuiteChainee("testac56.properties", "multiplication", 10, 0, 5, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 0, 0, 0, 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC57() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac57.properties", "division", 10, 0, 0, true);
			assertTrue(suite.toString().equals(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC58() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac58.properties");
			suite = new SuiteChainee("testac58.properties", "division", 10, 0, 0, false);
			assertTrue(suite.toString().equals(vieuxContenu+""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAC59() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac59.properties", "division", 10, 0, 1, true);
			assertTrue(suite.toString().equals("10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC60() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac60.properties");
			suite = new SuiteChainee("testac60.properties", "division", 10, 0, 1, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC61() {
		ISuiteChainee suite;
		try {
			suite = new SuiteChainee("testac61.properties", "division", 10, 0, 2, true);
			assertTrue(suite.toString().equals("10, 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAC62() {
		ISuiteChainee suite;
		try {
			String vieuxContenu = loadOldContent("testac62.properties");
			suite = new SuiteChainee("testac62.properties", "division", 10, 0, 2, false);
			assertTrue(suite.toString().equals(vieuxContenu+"10, 0"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = Exception.class)
	public void testAC63() throws Exception {
		ISuiteChainee suite;
		suite = new SuiteChainee("testac63.properties", "division", 10, 0, 5, true);

	}
	
	@Test (expected = Exception.class)
	public void testAC64() throws Exception {
		ISuiteChainee suite;
		suite = new SuiteChainee("testac64.properties", "division", 10, 0, 5, false);

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
