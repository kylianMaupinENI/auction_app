package fr.eni.encheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {

	/**
	 * Echec l'adresse email de l'utilisateur ne respecte pas les règles définies
	 */
	public static final int EMAIL_INVALIDE = 20000;

	/**
	 * Echec le mot de passe ne respecte pas les règles définies
	 */
	public static final int MOT_DE_PASSSE_INVALIDE = 20001;

	/**
	 * Echec le nom ne respecte pas les règles définies
	 */
	public static final int NOM_INVALIDE = 20002;

	/**
	 * Echec le prenom ne respecte pas les règles définies
	 */
	public static final int PRENOM_INVALIDE = 20003;

	/**
	 * Echec la ville ne respecte pas les règles définies
	 */
	public static final int VILLE_INVALIDE = 20004;

	/**
	 * Echec le code postal ne respecte pas les règles définies
	 */
	public static final int CODE_POSTAL_INVALIDE = 20005;

	/**
	 * Echec la rue ne respecte pas les règles définies
	 */
	public static final int RUE_INVALIDE = 20006;

	/**
	 * Echec le numéro de téléphone ne respecte pas les règles définies
	 */
	public static final int TELEPHONE_INVALIDE = 20007;

	/**
	 * Echec le pseudo ne respecte pas les règles définies
	 */
	public static final int PSEUDO_INVALIDE = 20008;

	/**
	 * Echec le pseudo est déja utilisé par un autre utilisateur
	 */
	public static final int PSEUDO_UTILISE = 20009;

	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */
	public static final int NOM_ARTICLE_INVALIDE = 20010;

	/**
	 * Echec la description de l'article ne respecte pas les règles définies
	 */
	public static final int DESCRIPTION_INVALIDE = 20011;

	/**
	 * Echec la description de l'article ne respecte pas les règles définies
	 */
	public static final int PRIX_ARTICLE_INVALIDE = 20012;

	/**
	 * Echec la date de début ne respecte pas les règles définies
	 */
	public static final int DATE_DEBUT_INVALIDE = 20013;

	/**
	 * Echec la date de fin ne peut pas être antérieure à la date de début
	 */
	public static final int DATE_FIN_ANTERIEURE_INVALIDE = 20014;

	/**
	 * Echec la durée maximale d'une enchère ne peut pas excéder 2 mois
	 */
	public static final int DUREE_TROP_LONGUE = 20015;

	/**
	 * Echec aucun compte n'est associé à ce pseudo
	 */
	public static final int PSEUDO_INCONNU = 20016;

	/**
	 * Echec le mot de passe entré est incorrect
	 */
	public static final int MOT_DE_PASSE_INVALIDE = 20017;

	/**
	 * Echec le mot de passe et la validation du mot de passe ne correspondent pas
	 */
	public static final int CONFIRMATION_INVALIDE = 20018;

	/**
	 * Echec le code postal entré ne respecte pas les règles définies
	 */
	public static final int CODE_POSTAL_NON_RENSEIGNE = 20019;

	/**
	 * Echec le téléphone entré ne respecte pas les règles définies
	 */
	public static final int TELEPHONE_NON_RENSEIGNE = 20020;
	/**
	 *Echec la proposition d'enchere est inférieure a la mise à prix
	 */
	public static final int PRIX_INFERIEUR_AU_PRIX_DE_BASE = 20021;
	/**
	 * Echec la proposition d'enchere est inférieure a la plus haute proposition
	 */
	public static final int PRIX_NON_VALIDE = 20022;

}
