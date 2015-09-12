/**
 * @author David Binh Quang Tran (1688466) & Charles Tremblay (1687959)
 * LOG3430 TP1
 */

/**
 *	Classe renpresentant une element de la suite chainee pour le tp1.
 */
public class ElementSuite {
	
	/*
	* Constructeur d'un element de la suite chainee avec sa valeur.
	*/
	public ElementSuite(int nouvelleValeur){
		valeur = nouvelleValeur;
		prochain = null;
	}
	
	/*
	* Ajoute un element a la fin de la suite chainee.
	*/
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
	
	/*
	* Retourne une reference vers le prochain element de la suite chainee.
	*/
	public ElementSuite next(){
		return prochain;
	};
	
	// Valeur que cet element possede.
	public int valeur;

	// Reference vers le prochain element.
	public ElementSuite prochain;
}
