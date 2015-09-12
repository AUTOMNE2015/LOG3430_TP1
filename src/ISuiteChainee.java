public interface ISuiteChainee {

	/*
    * Appeler cette méthode lorsqu’il est désirable d’ajouter un élément à la fin de la suite chaînée.
    */
	void add(ElementSuite nouvelElement);

	/*
    * Appeler cette méthode pour supprimer l’élément d’une suite chaînée à
    * l’index « position » désiré. Dans le cas où « position » est plus 
    * grand que la taille de la suite chaînée, rien ne se passera.
    */
	void removeAt(int position);

    /*
    * Appeler cette méthode lorsqu’il faut supprimer l’élément avec la valeur
    * « element »  de la suite chaînée. Dans le cas où « element » est 
    * présent multiples fois dans la suite chaînée, alors la première 
    * instance d’ « element » sera supprimée et les suivantes seront 
    * intactes.
    */
	void removeItem(int element);

    /*
    * Appeler cette méthode pour modifier un élément d’une suite chaînée à 
    * l’index « position » en écrasant l’élément courant avec la valeur 
    * « nouvelElement ». Dans le cas où « position » est plus grand que la
    * taille de la suite chaînée, rien ne se passera.
    */
	void setAt(int nouvelElement, int position);

    /*
    * Appeler cette méthode pour obtenir l’élément d’une suite chaînée à 
    * l’index « position » désiré. Dans le cas où « position » est plus 
    * grand que la taille de la suite chaînée, la méthode retournera null.
    */
	ElementSuite getAt(int position);

    /*
    * Appeler cette méthode pour obtenir la grandeur de la suite chaînée.
    */
	int getSize();

    /*
    * Appeler cette méthode pour réinitialiser la suite chaînée.
    * Elle n’aura pas d’éléments.
    */
	void reset();

	/*
    * Appeler cette méthode afin de vérifier si la suite chaînée courante 
    * est valide (i.e. que les valeurs sont cohérentes avec l’opérateur 
    * assigné. Si la suite est valide, la valeur retournée sera true. 
    * Sinon, la valeur retournée sera false.
    */
	boolean isValid();
}