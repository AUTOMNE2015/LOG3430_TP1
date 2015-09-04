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
		add(new ElementSuite(val2));
		
		int valeurCourante1 = val1;
		int valeurCourante2 = val2;
		
		try
		{
			switch(operateur){
			case "addition":
				for (int i = 0; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = addition(valeurCourante1, val2);
					valeurCourante1 = tampon;
					
					add(new ElementSuite(valeurCourante2));
				}
				break;
			case "soustraction":
				for (int i = 0; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = soustraction(valeurCourante1, val2);
					valeurCourante1 = tampon;
					
					add(new ElementSuite(valeurCourante2));
				}
				break;
			case "multiplication":
				for (int i = 0; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = multiplication(valeurCourante1, val2);
					valeurCourante1 = tampon;
					
					add(new ElementSuite(valeurCourante2));
				}
				break;
			case "division":
				for (int i = 0; i < taille; i++){
					int tampon = valeurCourante2;
					valeurCourante2 = division(valeurCourante1, val2);
					valeurCourante1 = tampon;
					
					add(new ElementSuite(valeurCourante2));
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
				
		// Modification des propriétés.
		properties.setProperty("val1", Integer.toString(val1));
		properties.setProperty("val2", Integer.toString(val2));
		properties.setProperty("operateur", operateur);
		properties.setProperty("taille", Integer.toString(taille));
		properties.setProperty("contenu", toString());
		
		premiereValeurInterne = val1;
		deuxiemeValeurInterne = val2;
		operateurInterne = operateur;
		
		// On utilise l'index précédent s'il existe et si on ne doit pas écraser la suite.
		if(estVide || nouveauFichier)
		{
			properties.setProperty("index", Integer.toString(taille));
		}
		else
		{
			// On additionne l'index précédent avec la taille en paramètre.
			properties.setProperty("index", Integer.toString(Integer.parseInt(properties.getProperty("index")) + taille));
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
		premierElement.add(nouvelElement);
	}
	
	public void removeItem(ElementSuite element)
	{
		ElementSuite suivant = premierElement;
		int index = 0;
		while (suivant != element || suivant.next() != null)
		{
			suivant = suivant.next();
			index++;
		}
		if(suivant != null){
			removeAt(index);
		}
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
		
	}
	
	public int getSize()
	{
		ElementSuite suivant = premierElement;
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

		while(courant.next() != null)
		{
			suivant = courant.next();
			courant.prochain = null;
		}
	}
	
	public boolean isValid()
	{
		boolean toutEstCorrecte = true;
		// La suite n'est pas vérifiable s'il y a 2 élements ou moins.
		if(premierElement != null && premierElement.next() != null)
		{
			ElementSuite elementTemp1 = premierElement;
			ElementSuite elementTemp2= premierElement.next();
			
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
				default:
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
			suite.concat(Integer.toString(getAt(i).valeur));
		}
		return suite;
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
		
		return ret;
	}
	
	private ElementSuite premierElement;
	private int premiereValeurInterne;
	private int deuxiemeValeurInterne;
	private String operateurInterne;
}
