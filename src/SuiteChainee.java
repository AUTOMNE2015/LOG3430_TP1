import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 */

/**
 * @author datraa
 *
 */

// Constructor is done.
// Reset is done.
// Add is done.
// isvalid is done.
// tostring is done.
// addition,soustraction, multi, divi are done.

// todo:
// removeat, removeitem, setat, getat, getsize, sauvegarder

// considerer creer une fonction Charger() qui met a jour le premierElement et l'indexInterne avant les operations.

public class SuiteChainee implements ISuiteChainee {
	
	public SuiteChainee(String chemin, String operateur, int val1, int val2, int taille, boolean estVide)
	{
		// Initialiser les membres de la classe.
		indexInterne = 0;
		operateurInterne = operateur;
		cheminInterne = chemin;

		
		// Créer les propriétés.
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
			// Fichier introuvable, on va créer un nouveau fichier plus tard.
			System.out.println("Fichier introuvable.");
			nouveauFichier = true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// Création de la suite.
		
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
		
		// Charge le contenu deja présent.
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

	@Override
	public void add(ElementSuite nouvelElement) {
		addInterne(nouvelElement);
		sauvegarder();
	}
	
	private void addInterne(ElementSuite nouvelElement) {
		if(premierElement == null)
		{
			premierElement = nouvelElement;
		} else {
			premierElement.add(nouvelElement);
			indexInterne++;
		}
	}

	@Override
	public void removeAt(int position) {
		//charger();
        ElementSuite avant = getAt(position-1);
        ElementSuite courant = getAt(position);
        if (courant == null)
        {
                System.out.println("Erreur removeAt: element hors de portee");
                return;
        }
        // On construit un lien entre l'element avant et apres.
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

	@Override
	public void removeItem(ElementSuite element) {
		//Charger();
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

	@Override
	public void setAt(ElementSuite nouvelElement, int position) {
		//Charger();
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

	@Override
	public ElementSuite getAt(int position) {

		if(position < 0)
        {
            System.out.println("Erreur getAt: Element hors de portee");
            return null;
        }
        
		//Charger();
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

	@Override
	public int getSize() {
		//Charger();
        int grosseur = 0;
        ElementSuite suivant = premierElement;
        
        // On itere dans la suite en comptant.
        while(suivant != null){
            grosseur++;  
            suivant = suivant.next();
        }
        return grosseur;
	}

	@Override
	public void reset() {
		ElementSuite courant = premierElement;
		ElementSuite suivant;

		while(courant != null && courant.next() != null)
		{
			suivant = courant.next();
			courant.prochain = null;
		}
		indexInterne = 0;
		sauvegarder();
	}

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
	
	private void sauvegarder()
	{
		// charger, remplacer index, remplacer contenu, fermer
	}
	
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
