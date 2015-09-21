public interface ICalculatrice {

	/*
	 * Appeler cette méthode afin d’obtenir la somme de valeur1 et de valeur2
	 * (valeur1 + valeur2).
	 */
	public abstract int addition(int valeur1, int valeur2) throws Exception;

	/*
	 * Appeler cette méthode afin d’obtenir la différence de valeur1 et 
	 * de valeur2 (valeur1 - valeur2).
	 */
	public abstract int soustraction(int valeur1, int valeur2) throws Exception;

	/*
	 * Appeler cette méthode afin d’obtenir le produit de valeur1 et de 
	 * valeur2 (valeur1 * valeur2).
	 */
	public abstract int multiplication(int valeur1, int valeur2)
			throws Exception;

	/*
	 * Appeler cette méthode afin d’obtenir le quotient de valeur1 et de 
	 * valeur2 (valeur1 / valeur2). Dans le cas d’une division par zéro,
	 * la suite chaînée ne sera pas complétée.
	 */
	public abstract int division(int valeur1, int valeur2) throws Exception;

}