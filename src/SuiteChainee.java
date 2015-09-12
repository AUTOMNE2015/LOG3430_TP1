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
		indexInterne = 0;
		operateurInterne = operateur;
		cheminInterne = chemin;

		
		// Creer les proprietes.
		Properties properties = new Properties();
		
		// Essai d'ouvrir un stream du fichier.
		File file = new File(chemin);
		FileInputStream fileInput;
		boolean nouveauFichier = false;
		try 
		{
			
			fileInput = new FileInputStream(file);
			properties.load(fileInput);
			
		} 
		catch (FileNotFoundException e)
		{
			// Fichier introuvable, on va creer un nouveau fichier plus tard.
			System.out.println("Fichier introuvable.");
			nouveauFichier = true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// Creation de la suite.
		
		premierElement = new ElementSuite(val1);
		addInterne(new ElementSuite(val2));
		
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
					valeurCourante2 = addition(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					addInterne(new ElementSuite(valeurCourante2));
				}
				break;
			case "soustraction":
				for (int i = 2; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = soustraction(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					addInterne(new ElementSuite(valeurCourante2));
				}
				break;
			case "multiplication":
				for (int i = 2; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = multiplication(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					addInterne(new ElementSuite(valeurCourante2));
				}
				break;
			case "division":
				for (int i = 2; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = division(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					addInterne(new ElementSuite(valeurCourante2));
				}
				break;
			default:
				throw new Exception("Opérateur invalide.");
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
		properties.setProperty("index", Integer.toString(indexInterne));
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
    * Appeler cette m�thode lorsqu�il est d�sirable d�ajouter un �l�ment � la fin de la suite cha�n�e.
    */
	@Override
	public void add(ElementSuite nouvelElement) {
            charger();
		addInterne(nouvelElement);
		sauvegarder();
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
			indexInterne++;
		}
	}

    /*
    * Appeler cette m�thode pour supprimer l��l�ment d�une suite cha�n�e �
    * l�index � position � d�sir�. Dans le cas o� � position � est plus 
    * grand que la taille de la suite cha�n�e, rien ne se passera.
    */
	@Override
	public void removeAt(int position) {
		charger();
        
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
        indexInterne = position;
        sauvegarder();
	}

    /*
    * Appeler cette m�thode lorsqu�il faut supprimer l��l�ment avec la valeur
    * � element �  de la suite cha�n�e. Dans le cas o� � element � est 
    * pr�sent multiples fois dans la suite cha�n�e, alors la premi�re 
    * instance d� � element � sera supprim�e et les suivantes seront 
    * intactes.
    */
	@Override
	public void removeItem(int element) {
		charger();
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
            indexInterne = index;
        }
        else
        {
             System.out.println("Erreur removeItem: Element \""+element+"\" introuvable.");
        }
        sauvegarder();
	}

    /*
    * Appeler cette m�thode pour modifier un �l�ment d�une suite cha�n�e � 
    * l�index � position � en �crasant l��l�ment courant avec la valeur 
    * � nouvelElement �. Dans le cas o� � position � est plus grand que la
    * taille de la suite cha�n�e, rien ne se passera.
    */
	@Override
	public void setAt(int nouvelElement, int position) {
		charger();
        // On trouve l'element a la position voulue pour remplacer sa valeur.
        ElementSuite remplace = getAt(position);
        if (remplace == null)
        {
                System.out.println("Erreur setAt: Element hors de portee");
                return;
        }
        remplace.valeur = nouvelElement;
        indexInterne = position;
        sauvegarder();
	}

    /*
    * Appeler cette m�thode pour obtenir l��l�ment d�une suite cha�n�e � 
    * l�index � position � d�sir�. Dans le cas o� � position � est plus 
    * grand que la taille de la suite cha�n�e, la m�thode retournera null.
    */
	@Override
	public ElementSuite getAt(int position) {

		if(position < 0)
        {
            System.out.println("Erreur getAt: Element hors de portee");
            return null;
        }

		charger();
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
    * Appeler cette m�thode pour obtenir la grandeur de la suite cha�n�e.
    */
	@Override
	public int getSize() {
		charger();
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
    * Appeler cette m�thode pour r�initialiser la suite cha�n�e.
    * Elle n�aura pas d��l�ments.
    */
	@Override
	public void reset() {
            charger();
		ElementSuite courant = premierElement;
		ElementSuite suivant;

		while(courant != null && courant.next() != null)
		{
			suivant = courant.next();
			courant.prochain = null;
		}
		premierElement = null;
		indexInterne = 0;
		sauvegarder();
	}

    /*
    * Appeler cette m�thode afin de v�rifier si la suite cha�n�e courante 
    * est valide (i.e. que les valeurs sont coh�rentes avec l�op�rateur 
    * assign�. Si la suite est valide, la valeur retourn�e sera true. 
    * Sinon, la valeur retourn�e sera false.
    */
	@Override
	public boolean isValid() {
		boolean toutEstCorrecte = true;
		// La suite n'est pas vérifiable s'il y a 2 élements ou moins.
		if(premierElement != null && premierElement.next() != null)
		{
			ElementSuite elementTemp1 = premierElement;
			ElementSuite elementTemp2 = premierElement.next();
			
			try
			{
				switch(operateurInterne){
				case "addition":
					while(toutEstCorrecte)
					{
						// On détermine la prochaine valeur.
						int prochaineValeur = addition(elementTemp1.valeur, elementTemp2.valeur);
						// On avance dans la suite.
						elementTemp1 = elementTemp2;
						if(elementTemp2.next() == null){
							break;
						}
						elementTemp2 = elementTemp2.next();
						// On vérifie si la nouvelle valeur respecte la règle ou non.
						if(elementTemp2.valeur != prochaineValeur)
						{
							toutEstCorrecte = false;
						}
					}
					break;
				case "soustraction":
					while(toutEstCorrecte)
					{
						// On détermine la prochaine valeur.
						int prochaineValeur = soustraction(elementTemp1.valeur, elementTemp2.valeur);
						// On avance dans la suite.
						elementTemp1 = elementTemp2;
						if(elementTemp2.next() == null){
							break;
						}
						elementTemp2 = elementTemp2.next();
						// On vérifie si la nouvelle valeur respecte la règle ou non.
						if(elementTemp2.valeur != prochaineValeur)
						{
							toutEstCorrecte = false;
						}
					}
					break;
				case "multiplication":
					while(toutEstCorrecte)
					{
						// On détermine la prochaine valeur.
						int prochaineValeur = multiplication(elementTemp1.valeur, elementTemp2.valeur);
						// On avance dans la suite.
						elementTemp1 = elementTemp2;
						if(elementTemp2.next() == null){
							break;
						}
						elementTemp2 = elementTemp2.next();
						// On vérifie si la nouvelle valeur respecte la règle ou non.
						if(elementTemp2.valeur != prochaineValeur)
						{
							toutEstCorrecte = false;
						}
					}
					break;
				case "division":
					while(toutEstCorrecte)
					{
						// On détermine la prochaine valeur.
						int prochaineValeur = division(elementTemp1.valeur, elementTemp2.valeur);
						// On avance dans la suite.
						elementTemp1 = elementTemp2;
						if(elementTemp2.next() == null){
							break;
						}
						elementTemp2 = elementTemp2.next();
						// On vérifie si la nouvelle valeur respecte la règle ou non.
						if(elementTemp2.valeur != prochaineValeur)
						{
							toutEstCorrecte = false;
						}
					}
					break;
				default:
					System.out.println(operateurInterne);
					throw new Exception("Opérateur invalide.");
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
    * Appeler cette m�thode afin d�obtenir une cha�ne de caract�res 
    * repr�sentant les valeurs de la suite cha�n�e dans le bon ordre. 
    * Par exemple : � 0, 1, 1, 2 �.
    */
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
                // Fichier introuvable, on va cr�er un nouveau fichier plus tard.
                System.out.println("Fichier introuvable.");
                return;
        }
        catch (IOException e)
        {
                e.printStackTrace();
                return;
        }
       
        // Remplir le fichier.
        properties.setProperty("index", Integer.toString(indexInterne));
        properties.setProperty("contenu", toString());
       
        // On �crit dans le fichier sp�cifi�.
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
    * Appeler cette m�thode afin d�obtenir la somme de valeur1 et de valeur2
    * (valeur1 + valeur2).
    */
	private int addition(int valeur1, int valeur2) throws Exception
	{
		float tampon1 = valeur1;
		float tampon2 = valeur2;
		
		if(tampon2 > 0)
		{
			while(tampon2-- != 0)
			{
				tampon1++;
			}
		}
		else if (tampon2 < 0)
		{
			while(tampon2++ != 0)
			{
				tampon1--;
			}
		}
		
		// Lance une exception en cas d'overflow.
		if(tampon1 > Integer.MAX_VALUE || tampon1 < Integer.MIN_VALUE)
		{
			throw new Exception("Overflow du type Integer.");
		}
		
		return (int) tampon1;
	}
        
    /*
    * Appeler cette m�thode afin d�obtenir la diff�rence de valeur1 et 
    * de valeur2 (valeur1 - valeur2).
    */
	private int soustraction(int valeur1, int valeur2) throws Exception
	{
		float tampon1 = valeur1;
		float tampon2 = valeur2;
		
		if(tampon2 > 0)
		{
			while(tampon2-- != 0)
			{
				tampon1--;
			}
		}
		else if (tampon2 < 0)
		{
			while(tampon2++ != 0)
			{
				tampon1++;
			}
		}
		
		// Lance une exception en cas d'overflow.
		if(tampon1 > Integer.MAX_VALUE || tampon1 < Integer.MIN_VALUE)
		{
			throw new Exception("Overflow du type Integer.");
		}
		
		return (int) tampon1;
	}

    /*
    * Appeler cette m�thode afin d�obtenir le produit de valeur1 et de 
    * valeur2 (valeur1 * valeur2).
    */
	private int multiplication(int valeur1, int valeur2) throws Exception
	{
		int ret = 0;
		int valeurAbsolue1 = valeur1;
		if (valeur1 < 0){valeurAbsolue1 = soustraction(0, valeur1);}
		
		int valeurAbsolue2 = valeur2;
		if (valeur2 < 0){valeurAbsolue2 = soustraction(0, valeur2);}
		
		for (int i = 0; i < valeurAbsolue2; i++)
		{
			ret = addition(ret, valeurAbsolue1);
		}
		
		if((valeur1<0)^(valeur2<0)){ret = soustraction(0, ret);}
		
		// Pas besoin de lancer une exception, car l'addition/soustraction le font déjà.
		return ret;
	}

    /*
    * Appeler cette m�thode afin d�obtenir le quotient de valeur1 et de 
    * valeur2 (valeur1 / valeur2). Dans le cas d�une division par z�ro,
    * la suite cha�n�e ne sera pas compl�t�e.
    */
	private int division(int valeur1, int valeur2) throws Exception
	{
		if(valeur1 == 0){
			return 0;
		}
		
		if(valeur2 == 0){
			throw new Exception("Division par zéro.");
		}
		
		int ret = 0;
		int valeurAbsolue1 = valeur1;
		if (valeur1 < 0){valeurAbsolue1 = soustraction(0, valeur1);}
		
		int valeurAbsolue2 = valeur2;
		if (valeur2 < 0){valeurAbsolue2 = soustraction(0, valeur2);}
		
		while (valeurAbsolue1 >= valeurAbsolue2)
		{
			ret++;
			valeurAbsolue1 = soustraction(valeurAbsolue1, valeurAbsolue2);
		}
		
		if((valeur1<0)^(valeur2<0)){ret = soustraction(0, ret);}
		
		// Pas besoin de lancer une exception, car l'addition/soustraction le font déjà.
		return ret;
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
			// Fichier introuvable, on va cr�er un nouveau fichier plus tard.
			System.out.println("Fichier introuvable.");
			return;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return;
		}
		
		// On charge les properties du fichier (si elles existent)
		indexInterne = Integer.parseInt(properties.getProperty("index"));
		contenuInterne = properties.getProperty("contenu");
		if(contenuInterne == null)
		{
			contenuInterne = "";
		} else {
			// On reset la suite chainee interne
			ElementSuite courant = premierElement;
			ElementSuite suivant;
			while(courant != null && courant.next() != null)
			{
				suivant = courant.next();
				courant.prochain = null;
			}

			// On cree un Array contenant les valeurs de contenu. Ex: ["1", "2", "3", "5"]
			String[] StringArrayContenu = contenuInterne.split(", ");
			// On batit la suite chainee grace a ce Array
			premierElement = new ElementSuite(Integer.parseInt(StringArrayContenu[0]));
			for(int i = 1; i < StringArrayContenu.length; i++) {
				addInterne(new ElementSuite(Integer.parseInt(StringArrayContenu[i])));
			}
		}
	}
	
	
	private int indexInterne;
	private String operateurInterne;
	private String cheminInterne;
	private String contenuInterne;
	private ElementSuite premierElement;
	
}
