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
public class SuiteChainee implements ISuiteChainee {
	
	public SuiteChainee(String chemin, String operateur, int val1, int val2, int taille, boolean estVide)
	{
		Properties properties = new Properties();
		
		File file = new File(chemin);
		FileInputStream fileInput;
		boolean nouveauFichier = false;
		
		try 
		{
			// Essai d'ouvrir un stream du fichier.
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
		addSansSauvegarde(new ElementSuite(val2));
				
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
					
					addSansSauvegarde(new ElementSuite(valeurCourante2));
				}
				break;
			case "soustraction":
				for (int i = 2; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = soustraction(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					addSansSauvegarde(new ElementSuite(valeurCourante2));
				}
				break;
			case "multiplication":
				for (int i = 2; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = multiplication(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					addSansSauvegarde(new ElementSuite(valeurCourante2));
				}
				break;
			case "division":
				for (int i = 2; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = division(valeurCourante1, valeurCourante2);
					valeurCourante1 = tampon;
					
					addSansSauvegarde(new ElementSuite(valeurCourante2));
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
		
		cheminInterne = chemin;
		operateurInterne = operateur;
		estVideInterne = estVide;
		ancienContenu = properties.getProperty("contenu");
		
		// Modification des propriétés.
		properties.setProperty("val1", Integer.toString(val1));
		properties.setProperty("val2", Integer.toString(val2));
		properties.setProperty("operateur", operateur);
		properties.setProperty("taille", Integer.toString(taille));
		
		if(estVide || ancienContenu == ""){
		properties.setProperty("contenu", toString());
		} else {
			properties.setProperty("contenu", ancienContenu + ", " + toString());
		}
		
		// On utilise l'index précédent s'il existe et si on ne doit pas écraser la suite.
		if(estVide || nouveauFichier)
		{
			properties.setProperty("index", "0");
		}
		else
		{
			String[] temporaire = ancienContenu.split(", ");
			int indexTestIdunno = temporaire.length;
			
			// On additionne l'index précédent avec la taille en paramètre.
			properties.setProperty("index", Integer.toString(indexTestIdunno));
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
	
	public void add(ElementSuite nouvelElement)
	{
		if(premierElement == null)
		{
			premierElement = nouvelElement;
		} else {
			premierElement.add(nouvelElement);
		}
		sauvegarder();
	}
	
	public void removeItem(ElementSuite element)
	{
		ElementSuite suivant = premierElement;
		int index = 0;
		while (suivant != null || suivant != element || suivant.next() != null)
		{
			suivant = suivant.next();
			index++;
		}
		if(suivant != null){
			removeAt(index);
		}
		sauvegarder();
	}
	
	public ElementSuite getAt(int position)
	{
		ElementSuite suivant = premierElement;
		
		for (int i = 0; i < position; i++){
			if (suivant == null)
			{
				System.out.println("Erreur getAt: élément hors de portée");
				return null;
			}
			suivant = suivant.next();
		}
		
		return suivant;
	}
	
	public void setAt(ElementSuite nouvelElement, int position)
	{
		ElementSuite remplace = getAt(position);
		if (remplace == null)
		{
			System.out.println("Erreur setAt: élément hors de portée");
			return;
		}
		remplace = nouvelElement;
		sauvegarder();
	}
	
	public void removeAt(int position)
	{
		ElementSuite avant = getAt(position-1);
		ElementSuite courant = getAt(position);
		if (avant == null || courant == null)
		{
			System.out.println("Erreur removeAt: élément hors de portée");
			return;
		}
		avant.prochain = courant.next();
		courant.prochain = null;
		sauvegarder();
		
	}
	
	public int getSize()
	{
		ElementSuite suivant = premierElement;
		if(suivant == null){
			return 0;
		}
		int grosseur = 1;

		while (suivant.next() != null)
		{
			suivant = suivant.next();
			grosseur++;
		}
		return grosseur;
	}
	
	public void reset()
	{
		ElementSuite courant = premierElement;
		ElementSuite suivant;

		while(courant != null && courant.next() != null)
		{
			suivant = courant.next();
			courant.prochain = null;
		}
		sauvegarder();
	}
	
	public boolean isValid()
	{
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
	
	public String toString()
	{
		String suite = "";
		for (int i = 0; i < getSize(); i++){
			if (i != 0)
			{
				suite += ", ";
			}
			suite = suite.concat(Integer.toString(getAt(i).valeur));
		}
		return suite;
	}
	
	private void sauvegarder(){
		
		Properties properties = new Properties();
		
		File file = new File(cheminInterne);
		FileInputStream fileInput;
		
		try 
		{
			// Essai d'ouvrir un stream du fichier.
			fileInput = new FileInputStream(file);
			properties.load(fileInput);
			
		} 
		catch (FileNotFoundException e)
		{
			// Fichier introuvable, on va créer un nouveau fichier plus tard.
			System.out.println("Fichier introuvable.");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		properties.setProperty("taille", Integer.toString(getSize()));
		if(getSize() > 0)
		{
			properties.setProperty("val1", Integer.toString(getAt(0).valeur));
		} else {
			properties.setProperty("val1","");
		}
		
		
		if(getSize() > 1)
		{
			properties.setProperty("val2", Integer.toString(getAt(1).valeur));
		} else {
			properties.setProperty("val2","");
		}
		
		if(estVideInterne || ancienContenu == ""){
			properties.setProperty("contenu", toString());
		} else if(toString() == ""){
			properties.setProperty("contenu", ancienContenu);
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
	
	private int addition(int valeur1, int valeur2)
	{
		int ret = valeur1;
		if(valeur2 > 0)
		{
			while(valeur2-- != 0)
			{
				ret++;
			}
		}
		else if (valeur2 < 0)
		{
			while(valeur2++ != 0)
			{
				ret--;
			}
		}
		return ret;
	}

	private int soustraction(int valeur1, int valeur2)
	{
		int ret = valeur1;
		if(valeur2 > 0)
		{
			while(valeur2-- != 0)
			{
				ret--;
			}
		}
		else if (valeur2 < 0)
		{
			while(valeur2++ != 0)
			{
				ret++;
			}
		}
		return ret;
	}

	private int multiplication(int valeur1, int valeur2)
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
		
		return ret;
	}

	private int division(int valeur1, int valeur2) throws Exception
	{
		if(valeur1 == 0){
			return 0;
		}
		
		if(valeur2 == 0){
			//throw new Exception("Division par zéro.");
			return 0;
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
		
		return ret;
	}
	
	private void addSansSauvegarde(ElementSuite nouvelElement)
	{
		if(premierElement == null)
		{
			premierElement = nouvelElement;
		} else {
			premierElement.add(nouvelElement);
		}
	}
	
	private ElementSuite premierElement;
	private String cheminInterne;
	private String operateurInterne;
	private String ancienContenu;
	private boolean estVideInterne;
}
