/**
 * 
 */

/**
 * @author datraa
 *
 */
public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		SuiteChainee s = new SuiteChainee("test1.properties", "addition", 1, 2, -5, true);
		System.out.println(s.isValid());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

}
