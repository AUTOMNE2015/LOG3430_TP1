import static org.junit.Assert.*;

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
public class TestEC {


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
			suite = new SuiteChainee("test1.properties", "addition", 1, 2, 1, true);
			assertTrue("testEC1 : La liste n'est pas valide.",suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testEC2() {
		try{
		ISuiteChainee suite = new SuiteChainee("test2.properties", "soustraction", 2, 1, 2, true);
		assertTrue("testEC2 : La liste n'est pas valide.",suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEC3() {
		try{
		ISuiteChainee suite = new SuiteChainee("test3.properties", "multiplication", 1, 2, 5, false);
		assertTrue("testEC3 : La liste n'est pas valide.",suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEC4() {
		try{
		ISuiteChainee suite = new SuiteChainee("test4.properties", "division", 20, 2, 15, true);
		assertTrue("testEC4 : La liste n'est pas valide.",suite.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = Exception.class)
	public void testEC5() throws Exception {
		ISuiteChainee suite = new SuiteChainee("", "jenesuispasunoperateur", 1, 2, 0, true);
	}
	

}
