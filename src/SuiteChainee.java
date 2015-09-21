import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author David Binh Quang Tran (1688466) & Charles Tremblay (1687959)
 * LOG3430 TP1
 */

/**
 *	Classe renpresentant la suite chainee pour le tp1.
 */
public class SuiteChainee implements ISuiteChainee {

    /*
    * Constructeur definissant l'operateur, les deux premieres valeurs et la
    * taille de la suite chainee.
    */
	public SuiteChainee(String chemin, String operateur, int val1, int val2, int taille, boolean estVide)
	{
		// Initialiser les membres de la classe.
		
		operateurInterne = operateur;
		cheminInterne = chemin;
		calculatrice = new Calculatrice();
		listeInterne = new MaListe();
		listeInterne.setIndexInterne(0);
		
		// Creer les proprietes.
		Properties properties = new Properties();
		
		// Essai d'ouvrir un stream du fichier.
		File file = new File(chemin);
		FileInputStream fileInput;
		try 
		{
			
			fileInput = new FileInputStream(file);
			properties.load(fileInput);
			
		} 
		catch (FileNotFoundException e)
		{
			// Fichier introuvable, on va creer un nouveau fichier plus tard.
			System.out.println("Fichier introuvable.");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// Creation de la suite.
		
		listeInterne.setPremierElement(new ElementSuite(val1));
		listeInterne.add(new ElementSuite(val2));
		
		int valeurCourante1 = val1;
		int valeurCourante2 = val2;
		
		try
		{
			if(taille > 10){
				throw new Exception("Taille excedant 10.");
			}
			
			// Puisque l'element #1 et l'element #2 sont deja dans la suite chainee, on commence a i=2
			switch(operateur){
			case "addition":
				for (int i = 2; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = calculatrice.addition(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					listeInterne.add(new ElementSuite(valeurCourante2));
				}
				break;
			case "soustraction":
				for (int i = 2; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = calculatrice.soustraction(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					listeInterne.add(new ElementSuite(valeurCourante2));
				}
				break;
			case "multiplication":
				for (int i = 2; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = calculatrice.multiplication(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					listeInterne.add(new ElementSuite(valeurCourante2));
				}
				break;
			case "division":
				for (int i = 2; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = calculatrice.division(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					listeInterne.add(new ElementSuite(valeurCourante2));
				}
				break;
			default:
				throw new Exception("OpÃ©rateur invalide.");
			}
		
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return;
		}
		
		// Remplir le fichier.
		properties.setProperty("val1", Integer.toString(val1));
		properties.setProperty("val2", Integer.toString(val2));
		properties.setProperty("operateur", operateurInterne);
		properties.setProperty("index", Integer.toString(listeInterne.getIndexInterne()));
		properties.setProperty("taille", Integer.toString(taille));
		
		// Charge le contenu deja present.
		String ancienContenu = properties.getProperty("contenu");
		if(ancienContenu == null)
		{
			ancienContenu = "";
		}
		
		if(estVide || ancienContenu == ""){
		properties.setProperty("contenu", toString());
		} else {
			properties.setProperty("contenu", ancienContenu + ", " + toString());
		}
		
		// On ecrit dans le fichier specifie.
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			properties.store(fileOut, "Suite Chainee");
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
                
                charger();
	}

    /*
    * Appeler cette méthode pour réinitialiser la suite chaînée.
    * Elle n’aura pas d’éléments.
    */
	@Override
	public void reset() {
            charger();
		ElementSuite courant = listeInterne.getPremierElement();
		ElementSuite suivant;

		while(courant != null && courant.next() != null)
		{
			suivant = courant.next();
			courant.prochain = null;
			courant = suivant;
		}
		listeInterne.setPremierElement(null);
		listeInterne.setIndexInterne(0);
		sauvegarder();
	}

    /*
    * Appeler cette méthode afin de vérifier si la suite chaînée courante 
    * est valide (i.e. que les valeurs sont cohérentes avec l’opérateur 
    * assigné. Si la suite est valide, la valeur retournée sera true. 
    * Sinon, la valeur retournée sera false.
    */
	@Override
	public boolean isValid() {
		boolean toutEstCorrecte = true;
		charger();
		// La suite n'est pas verifiable s'il y a 2 elements ou moins.
		if(listeInterne.getPremierElement() != null && listeInterne.getPremierElement().next() != null)
		{
			ElementSuite elementTemp1 = listeInterne.getPremierElement();
			ElementSuite elementTemp2 = listeInterne.getPremierElement().next();
			
			try
			{
				switch(operateurInterne){
				case "addition":
					while(toutEstCorrecte)
					{
						// On dÃ©termine la prochaine valeur.
						int prochaineValeur = calculatrice.addition(elementTemp1.valeur, elementTemp2.valeur);
						// On avance dans la suite.
						elementTemp1 = elementTemp2;
						if(elementTemp2.next() == null){
							break;
						}
						elementTemp2 = elementTemp2.next();
						// On vÃ©rifie si la nouvelle valeur respecte la rÃ¨gle ou non.
						if(elementTemp2.valeur != prochaineValeur)
						{
							toutEstCorrecte = false;
						}
					}
					break;
				case "soustraction":
					while(toutEstCorrecte)
					{
						// On dÃ©termine la prochaine valeur.
						int prochaineValeur = calculatrice.soustraction(elementTemp1.valeur, elementTemp2.valeur);
						// On avance dans la suite.
						elementTemp1 = elementTemp2;
						if(elementTemp2.next() == null){
							break;
						}
						elementTemp2 = elementTemp2.next();
						// On vÃ©rifie si la nouvelle valeur respecte la rÃ¨gle ou non.
						if(elementTemp2.valeur != prochaineValeur)
						{
							toutEstCorrecte = false;
						}
					}
					break;
				case "multiplication":
					while(toutEstCorrecte)
					{
						// On dÃ©termine la prochaine valeur.
						int prochaineValeur = calculatrice.multiplication(elementTemp1.valeur, elementTemp2.valeur);
						// On avance dans la suite.
						elementTemp1 = elementTemp2;
						if(elementTemp2.next() == null){
							break;
						}
						elementTemp2 = elementTemp2.next();
						// On vÃ©rifie si la nouvelle valeur respecte la rÃ¨gle ou non.
						if(elementTemp2.valeur != prochaineValeur)
						{
							toutEstCorrecte = false;
						}
					}
					break;
				case "division":
					while(toutEstCorrecte)
					{
						// On dÃ©termine la prochaine valeur.
						int prochaineValeur = calculatrice.division(elementTemp1.valeur, elementTemp2.valeur);
						// On avance dans la suite.
						elementTemp1 = elementTemp2;
						if(elementTemp2.next() == null){
							break;
						}
						elementTemp2 = elementTemp2.next();
						// On vÃ©rifie si la nouvelle valeur respecte la rÃ¨gle ou non.
						if(elementTemp2.valeur != prochaineValeur)
						{
							toutEstCorrecte = false;
						}
					}
					break;
				default:
					System.out.println(operateurInterne);
					throw new Exception("OpÃ©rateur invalide.");
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				return false;
			}
		}
		return toutEstCorrecte;
	}
	
    /*
	* Charge les proprietes du fichier en memoire.
	*/
	private void charger(){
		// Essai d'ouvrir un stream du fichier.
        Properties properties = new Properties();
		File file = new File(cheminInterne);
		FileInputStream fileInput;
		try 
		{
			
			fileInput = new FileInputStream(file);
			properties.load(fileInput);
			
		} 
		catch (FileNotFoundException e)
		{
			// Fichier introuvable, on va créer un nouveau fichier plus tard.
			System.out.println("Fichier introuvable.");
			return;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return;
		}
		
		// On charge les properties du fichier (si elles existent)
		listeInterne.setIndexInterne(Integer.parseInt(properties.getProperty("index")));
		contenuInterne = properties.getProperty("contenu");
		if(contenuInterne == null)
		{
			contenuInterne = "";
		} else {
			// On reset la suite chainee interne
			ElementSuite courant = listeInterne.getPremierElement();
			ElementSuite suivant;
			while(courant != null && courant.next() != null)
			{
				suivant = courant.next();
				courant.prochain = null;
				courant = suivant;
			}

			// On cree un Array contenant les valeurs de contenu. Ex: ["1", "2", "3", "5"]
			String[] StringArrayContenu = contenuInterne.split(", ");
			// On batit la suite chainee grace a ce Array
			listeInterne.setPremierElement(new ElementSuite(Integer.parseInt(StringArrayContenu[0])));
			for(int i = 1; i < StringArrayContenu.length; i++) {
				listeInterne.add(new ElementSuite(Integer.parseInt(StringArrayContenu[i])));
			}
		}
	}
	
	/*
	* Sauvegarde l'etat de la suite dans le fichier.
	*/
	private void sauvegarder()
	{            
        // On charge le fichier .properties
        Properties properties = new Properties();
        // Essai d'ouvrir un stream du fichier.
        File file = new File(cheminInterne);
        FileInputStream fileInput;
        try
        {
               
                fileInput = new FileInputStream(file);
                properties.load(fileInput);
               
        }
        catch (FileNotFoundException e)
        {
                // Fichier introuvable, on va créer un nouveau fichier plus tard.
                System.out.println("Fichier introuvable.");
                return;
        }
        catch (IOException e)
        {
                e.printStackTrace();
                return;
        }
       
        // Remplir le fichier.
        properties.setProperty("index", Integer.toString(listeInterne.getIndexInterne()));
        properties.setProperty("contenu", toString());
       
        // On écrit dans le fichier spécifié.
        FileOutputStream fileOut;
        try {
                fileOut = new FileOutputStream(file);
                properties.store(fileOut, "Suite Chainee");
                fileOut.close();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
	}
	
	/*
	* Appeler cette méthode lorsqu’il est désirable d’ajouter un élément à la fin de la suite chaînée.
	*/
	@Override
	public void add(ElementSuite nouvelElement) {
	    charger();
		listeInterne.add(nouvelElement);
		sauvegarder();
	}

	/*
	* Appeler cette méthode pour obtenir l’élément d’une suite chaînée à 
	* l’index « position » désiré. Dans le cas où « position » est plus 
	* grand que la taille de la suite chaînée, la méthode retournera null.
	*/
	@Override
	public ElementSuite getAt(int position) {
		charger();	
	    return listeInterne.getAt(position);
	}

	/*
	* Appeler cette méthode pour obtenir la grandeur de la suite chaînée.
	*/
	@Override
	public int getSize() {
		charger();
	    return listeInterne.getSize();
	}

	/*
	* Appeler cette méthode pour supprimer l’élément d’une suite chaînée à
	* l’index « position » désiré. Dans le cas où « position » est plus 
	* grand que la taille de la suite chaînée, rien ne se passera.
	*/
	@Override
	public void removeAt(int position) {
		charger();
		listeInterne.removeAt(position);
	    sauvegarder();
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
		charger();
		listeInterne.removeItem(element);
	    sauvegarder();
	}

	/*
	* Appeler cette méthode pour modifier un élément d’une suite chaînée à 
	* l’index « position » en écrasant l’élément courant avec la valeur 
	* « nouvelElement ». Dans le cas où « position » est plus grand que la
	* taille de la suite chaînée, rien ne se passera.
	*/
	@Override
	public void setAt(int nouvelElement, int position) {
		charger();
		listeInterne.setAt(nouvelElement, position);
	    sauvegarder();
	}

	/*
	* Appeler cette méthode afin d’obtenir une chaîne de caractères 
	* représentant les valeurs de la suite chaînée dans le bon ordre. 
	* Par exemple : « 0, 1, 1, 2 ».
	*/
	@Override
	public String toString(){
		return listeInterne.toString();
	}

	// Represente la calculatrice pour effectuer les calculs.
    private ICalculatrice calculatrice;

	// Represente l'operateur en memoire.
	private String operateurInterne;

	// Represente le chemin vers le fichier.
	private String cheminInterne;

	// Represente le contenu de la suite chainee en memoire.
	private String contenuInterne;

	// Reference vers la liste de la suite chainee.
	private IMaListe listeInterne;
	
}
