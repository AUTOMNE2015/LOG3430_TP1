/**
 * 
 */

/**
 * @author datraa
 *
 */
public class ElementSuite {
	
	public ElementSuite(int nouvelleValeur){
		valeur = nouvelleValeur;
		prochain = null;
	}
	
	public void add(ElementSuite nouvelElement){
		if(prochain == null)
		{
			prochain = nouvelElement;
		}
		else
		{
			prochain.add(nouvelElement);
		}
	};
	
	int valeur;
	ElementSuite prochain;
}
