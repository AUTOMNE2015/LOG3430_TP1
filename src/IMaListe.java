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
	 * Appeler cette m�thode lorsqu�il est d�sirable d�ajouter un �l�ment � la fin de la suite cha�n�e.
	 */
	public abstract void add(ElementSuite nouvelElement);

	/*
	 * Appeler cette m�thode pour obtenir l��l�ment d�une suite cha�n�e � 
	 * l�index � position � d�sir�. Dans le cas o� � position � est plus 
	 * grand que la taille de la suite cha�n�e, la m�thode retournera null.
	 */
	public abstract ElementSuite getAt(int position);

	/*
	 * Appeler cette m�thode pour obtenir la grandeur de la suite cha�n�e.
	 */
	public abstract int getSize();

	/*
	 * Appeler cette m�thode pour supprimer l��l�ment d�une suite cha�n�e �
	 * l�index � position � d�sir�. Dans le cas o� � position � est plus 
	 * grand que la taille de la suite cha�n�e, rien ne se passera.
	 */
	public abstract void removeAt(int position);

	/*
	 * Appeler cette m�thode lorsqu�il faut supprimer l��l�ment avec la valeur
	 * � element �  de la suite cha�n�e. Dans le cas o� � element � est 
	 * pr�sent multiples fois dans la suite cha�n�e, alors la premi�re 
	 * instance d� � element � sera supprim�e et les suivantes seront 
	 * intactes.
	 */
	public abstract void removeItem(int element);

	/*
	 * Appeler cette m�thode pour modifier un �l�ment d�une suite cha�n�e � 
	 * l�index � position � en �crasant l��l�ment courant avec la valeur 
	 * � nouvelElement �. Dans le cas o� � position � est plus grand que la
	 * taille de la suite cha�n�e, rien ne se passera.
	 */
	public abstract void setAt(int nouvelElement, int position);

	/*
	 * Appeler cette m�thode afin d�obtenir une cha�ne de caract�res 
	 * repr�sentant les valeurs de la suite cha�n�e dans le bon ordre. 
	 * Par exemple : � 0, 1, 1, 2 �.
	 */
	public abstract String toString();

}