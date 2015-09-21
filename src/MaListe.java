
public class MaListe implements IMaListe {

	/*
	 * Appeler cette methode pour acceder au premier element de la suite. 
	 */
	@Override
	public ElementSuite getPremierElement() {
		return premierElement;
	}
	
	/*
	 * Appeler cette methode pour modifier le premier element de la suite. 
	 */
	@Override
	public void setPremierElement(ElementSuite element) {
		premierElement = element;
	}
	
	/*
	 * Appeler cette methode pour acceder a l'index de la suite. 
	 */
	@Override
	public int getIndexInterne() {
		return indexInterne;
	}
	
	
	/*
	 * Appeler cette methode pour modifier l'index de la suite. 
	 */
	@Override
	public void setIndexInterne(int indexInterne) {
		this.indexInterne = indexInterne;
	}

	/*
	* Appeler cette méthode lorsqu’il est désirable d’ajouter un élément à la fin de la suite chaînée.
	*/
	@Override
	public void add(ElementSuite nouvelElement) {
		addInterne(nouvelElement);
	}

	/*
	* Implementation interne de add.
	*/
	private void addInterne(ElementSuite nouvelElement) {
		if(premierElement == null)
		{
			premierElement = nouvelElement;
		} else {
			premierElement.add(nouvelElement);
			setIndexInterne(getIndexInterne() + 1);
		}
	}

	/*
	* Appeler cette méthode pour obtenir l’élément d’une suite chaînée à 
	* l’index « position » désiré. Dans le cas où « position » est plus 
	* grand que la taille de la suite chaînée, la méthode retournera null.
	*/
	@Override
	public ElementSuite getAt(int position) {
	
		if(position < 0)
	    {
	        System.out.println("Erreur getAt: Element hors de portee");
	        return null;
	    }
	
	    ElementSuite suivant = premierElement;
	    
	    // On parcourt la suite jusqu'a la position voulue.
	    for (int i = 0; i < position; i++){
	        if (suivant == null)
	        {
	            System.out.println("Erreur getAt: Element hors de portee");
	            return null;
	        }
	        suivant = suivant.next();
	    }
	
	    return suivant;
	}

	/*
	* Appeler cette méthode pour obtenir la grandeur de la suite chaînée.
	*/
	@Override
	public int getSize() {
	    int grosseur = 0;
	    ElementSuite suivant = premierElement;
	    
	    // On itere dans la suite en comptant.
	    while(suivant != null){
	        grosseur++;  
	        suivant = suivant.next();
	    }
	    return grosseur;
	}

	/*
	* Appeler cette methode pour supprimer l’element d’une suite chainee à
	* l’index « position » desire. Dans le cas ou « position » est plus 
	* grand que la taille de la suite chainee, rien ne se passera.
	*/
	@Override
	public void removeAt(int position) {   
	    ElementSuite courant = getAt(position);
	    if (courant == null)
	    {
	            System.out.println("Erreur removeAt: element hors de portee");
	            return;
	    }
	    // On construit un lien entre l'element avant et apres.
	    ElementSuite avant = getAt(position-1);
	    if(avant != null)
	    {
	        avant.prochain = courant.next();
	    }
	    // Si c'est le premier element, on remplace le premier element.
	    if(position == 0)
	    {
	        premierElement = courant.next();
	    }
	    courant.prochain = null;
	    setIndexInterne(position);
	}

	/*
	* Appeler cette méthode lorsqu’il faut supprimer l’élément avec la valeur
	* « element »  de la suite chaînée. Dans le cas où « element » est 
	* présent multiples fois dans la suite chaînée, alors la première 
	* instance d’ « element » sera supprimée et les suivantes seront 
	* intactes.
	*/
	@Override
	public void removeItem(int element) {
	    ElementSuite suivant = premierElement;
	    int index = 0;
	    // On cherche le premier element qui correspond a ce qu'on veut.
	    while (suivant != null && suivant.valeur != element && suivant.next() != null)
	    {
	        suivant = suivant.next();
	        index++;
	    }
	    // Si on le trouve, on l'efface.
	    if(suivant != null && suivant.valeur == element){
	        removeAt(index);
	        setIndexInterne(index);
	    }
	    else
	    {
	         System.out.println("Erreur removeItem: Element \""+element+"\" introuvable.");
	    }
	}

	/*
	* Appeler cette méthode pour modifier un élément d’une suite chaînée à 
	* l’index « position » en écrasant l’élément courant avec la valeur 
	* « nouvelElement ». Dans le cas où « position » est plus grand que la
	* taille de la suite chaînée, rien ne se passera.
	*/
	@Override
	public void setAt(int nouvelElement, int position) {
	    // On trouve l'element a la position voulue pour remplacer sa valeur.
	    ElementSuite remplace = getAt(position);
	    if (remplace == null)
	    {
	            System.out.println("Erreur setAt: Element hors de portee");
	            return;
	    }
	    remplace.valeur = nouvelElement;
	    setIndexInterne(position);
	}

	/*
	* Appeler cette méthode afin d’obtenir une chaîne de caractères 
	* représentant les valeurs de la suite chaînée dans le bon ordre. 
	* Par exemple : « 0, 1, 1, 2 ».
	*/
	@Override
	public String toString(){
		String suite = "";
		ElementSuite prochain = premierElement;
		boolean premier = true;
		while(prochain != null)
		{
			if (!premier)
			{
				suite += ", ";
			}
			suite = suite.concat(Integer.toString(prochain.valeur));
			prochain = prochain.next();
			premier = false;
		}
		return suite;
		
	}
	
	// Represente la valeur de l'index en memoire.
	private int indexInterne;
	
	// Reference vers le premier element de la suite chainee.
	private ElementSuite premierElement;

}
