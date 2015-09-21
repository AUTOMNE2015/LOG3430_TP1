public interface IMaListe {
	
	/*
	 * Appeler cette methode pour acceder au premier element de la suite. 
	 */
	public abstract ElementSuite getPremierElement();
	
	/*
	 * Appeler cette methode pour modifier le premier element de la suite. 
	 */
	public abstract void setPremierElement(ElementSuite element);
	
	/*
	 * Appeler cette methode pour acceder a l'index de la suite. 
	 */
	public abstract int getIndexInterne();
	
	/*
	 * Appeler cette methode pour modifier l'index de la suite. 
	 */
	public abstract void setIndexInterne(int indexInterne);
	
	/*
	 * Appeler cette méthode lorsqu’il est désirable d’ajouter un élément à la fin de la suite chaînée.
	 */
	public abstract void add(ElementSuite nouvelElement);

	/*
	 * Appeler cette méthode pour obtenir l’élément d’une suite chaînée à 
	 * l’index « position » désiré. Dans le cas où « position » est plus 
	 * grand que la taille de la suite chaînée, la méthode retournera null.
	 */
	public abstract ElementSuite getAt(int position);

	/*
	 * Appeler cette méthode pour obtenir la grandeur de la suite chaînée.
	 */
	public abstract int getSize();

	/*
	 * Appeler cette méthode pour supprimer l’élément d’une suite chaînée à
	 * l’index « position » désiré. Dans le cas où « position » est plus 
	 * grand que la taille de la suite chaînée, rien ne se passera.
	 */
	public abstract void removeAt(int position);

	/*
	 * Appeler cette méthode lorsqu’il faut supprimer l’élément avec la valeur
	 * « element »  de la suite chaînée. Dans le cas où « element » est 
	 * présent multiples fois dans la suite chaînée, alors la première 
	 * instance d’ « element » sera supprimée et les suivantes seront 
	 * intactes.
	 */
	public abstract void removeItem(int element);

	/*
	 * Appeler cette méthode pour modifier un élément d’une suite chaînée à 
	 * l’index « position » en écrasant l’élément courant avec la valeur 
	 * « nouvelElement ». Dans le cas où « position » est plus grand que la
	 * taille de la suite chaînée, rien ne se passera.
	 */
	public abstract void setAt(int nouvelElement, int position);

	/*
	 * Appeler cette méthode afin d’obtenir une chaîne de caractères 
	 * représentant les valeurs de la suite chaînée dans le bon ordre. 
	 * Par exemple : « 0, 1, 1, 2 ».
	 */
	public abstract String toString();

}