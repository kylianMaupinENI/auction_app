package fr.eni.encheres.servlets;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesResultatServlets {

	/**
	 * SI LA DATE DE FIN DE L'ENCHERE EST DEJA D�PASS�E OU EST INFERIEURE A LA DATE
	 * DE DEBUT
	 */
	public static final int DATE_FIN_ECHERE_NON_VALIDE = 10001;
	/**
	 * SI LA DATE DE DEBUT DE L'ENCHERE EST DEJA D�PASS�E
	 */
	public static final int DATE_DEBUT_ECHERE_NON_VALIDE = 10002;
	/**
	 * SI LE FORMAT DE LA DATE N'EST PAS VALIDE
	 */
	public static final int FORMAT_DATE_ERREUR = 10003;

	public static final Integer FORMAT_PRIX_ERREUR = 10004;

}
