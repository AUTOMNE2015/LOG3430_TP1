
public class Calculatrice implements ICalculatrice {
	 /*
	    * Appeler cette m�thode afin d�obtenir la somme de valeur1 et de valeur2
	    * (valeur1 + valeur2).
	    */
		@Override
		public int addition(int valeur1, int valeur2) throws Exception
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
		@Override
		public int soustraction(int valeur1, int valeur2) throws Exception
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
		@Override
		public int multiplication(int valeur1, int valeur2) throws Exception
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
		@Override
		public int division(int valeur1, int valeur2) throws Exception
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
}
