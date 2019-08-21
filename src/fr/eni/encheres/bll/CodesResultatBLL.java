package fr.eni.encheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec l'adresse email de l'utilisateur ne respecte pas les r�gles d�finies
	 */
	public static final int EMAIL_INVALIDE = 20000;
	
	/**
	 * Echec le mot de passe ne respecte pas les r�gles d�finies
	 */
	public static final int MOT_DE_PASSSE_INVALIDE = 20001;
	
	/**
	 * Echec le nom ne respecte pas les r�gles d�finies
	 */
	public static final int NOM_INVALIDE = 20002;
	
	/**
	 * Echec le prenom ne respecte pas les r�gles d�finies
	 */
	public static final int PRENOM_INVALIDE = 20003;

	/**
	 * Echec la ville ne respecte pas les r�gles d�finies
	 */
	public static final int VILLE_INVALIDE = 20004;

	/**
	 * Echec le code postal ne respecte pas les r�gles d�finies
	 */
	public static final int CODE_POSTAL_INVALIDE = 20005;

	/**
	 * Echec la rue ne respecte pas les r�gles d�finies
	 */
	public static final int RUE_INVALIDE = 20006;

	/**
	 * Echec le num�ro de t�l�phone ne respecte pas les r�gles d�finies
	 */
	public static final int TELEPHONE_INVALIDE = 20007;

	/**
	 * Echec le pseudo ne respecte pas les r�gles d�finies
	 */
	public static final int PSEUDO_INVALIDE = 20008;

	/**
	 * Echec le pseudo est d�ja utilis� par un autre utilisateur
	 */
	public static final int PSEUDO_UTILISE = 20009;
	
	/**
	 * Echec le nom de l'article ne respecte pas les r�gles d�finies
	 */
	public static final int NOM_ARTICLE_INVALIDE = 20010;

	/**
	 * Echec la description de l'article ne respecte pas les r�gles d�finies
	 */
	public static final int DESCRIPTION_INVALIDE = 20011;

	/**
	 * Echec la description de l'article ne respecte pas les r�gles d�finies
	 */
	public static final int PRIX_ARTICLE_INVALIDE = 20012;

	
}