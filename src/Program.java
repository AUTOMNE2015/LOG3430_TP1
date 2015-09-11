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
		SuiteChainee s = new SuiteChainee("test1.properties", "addition", 1, 2, 5, false);
		System.out.println(s.isValid());
		s.add(new ElementSuite(5));
		System.out.println(s.isValid());
	}

}
