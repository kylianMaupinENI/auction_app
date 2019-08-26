package fr.eni.encheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL = 10000;
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC = 10001;
	/**
	 * Echec de la lecture de l'articles vendu
	 */
	public static final int LECTURE_ARTICLE_VENDU_ECHEC = 10002;
	/**
	 * Echec de la lecture d'une liste d'articles vendu
	 */
	public static final int LECTURE_LISTE_ARTICLE_VENDU_ECHEC = 10003;
	/**
	 * Liste d'articles vendus inexistante
	 */
	public static final int LECTURE_LISTE_ARTICLE_VENDU_INEXISTANTE = 10004;
	/**
	 * Echec mise à jour de l'utilisateur
	 */
	public static final int UPDATE_UTILISATEUR_ECHEC = 10005;
	/**
	 * Echec suppression de l'utilisateur
	 */
	public static final int DELETE_UTILISATEUR_ECHEC = 10006;
}












