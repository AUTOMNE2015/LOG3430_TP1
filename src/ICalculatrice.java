public interface ICalculatrice {

	/*
	 * Appeler cette m�thode afin d�obtenir la somme de valeur1 et de valeur2
	 * (valeur1 + valeur2).
	 */
	public abstract int addition(int valeur1, int valeur2) throws Exception;

	/*
	 * Appeler cette m�thode afin d�obtenir la diff�rence de valeur1 et 
	 * de valeur2 (valeur1 - valeur2).
	 */
	public abstract int soustraction(int valeur1, int valeur2) throws Exception;

	/*
	 * Appeler cette m�thode afin d�obtenir le produit de valeur1 et de 
	 * valeur2 (valeur1 * valeur2).
	 */
	public abstract int multiplication(int valeur1, int valeur2)
			throws Exception;

	/*
	 * Appeler cette m�thode afin d�obtenir le quotient de valeur1 et de 
	 * valeur2 (valeur1 / valeur2). Dans le cas d�une division par z�ro,
	 * la suite cha�n�e ne sera pas compl�t�e.
	 */
	public abstract int division(int valeur1, int valeur2) throws Exception;

}