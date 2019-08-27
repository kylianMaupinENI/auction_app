package fr.eni.encheres.servlets;

public class ServletUtils {
	
	public final static String ATT_SESSION_USER = "sessionUtilisateur";
	
	public static final String CHAMP_PSEUDO_INSCRIPTION = "pseudo";
	public static final String CHAMP_NOM_INSCRIPTION = "nom";
	public static final String CHAMP_PRENOM_INSCRIPTION = "prenom";
	public static final String CHAMP_EMAIL_INSCRIPTION = "email";
	public static final String CHAMP_TELEPHONE_INSCRIPTION = "telephone";
	public static final String CHAMP_CODE_POSTAL_INSCRIPTION = "codePostal";
	public static final String CHAMP_RUE_INSCRIPTION = "rue";
	public static final String CHAMP_CONFIRMATION_INSCRIPTION = "confirmation";
	public static final String CHAMP_MOT_DE_PASSE_INSCRIPTION = "motDePasse";
	public static final String CHAMP_VILLE_INSCRIPTION = "ville";
	public static final String CHAMP_CREDIT_INSCRIPTION = "credit";
	
	public static final String BTN_ANNULER = "annuler";
	public static final String BTN_INSCRIPTION = "inscription";
	public static final String BTN_ENREGISTRER = "enregistrer";
	public static final String BTN_SUPPRIMER = "supprimer";
	
	public static final String JSP_PROFIL = "/WEB-INF/profilUtilisateur.jsp";
	public static final String JSP_ACCUEIL = "/WEB-INF/index.jsp";
	public static final String JSP_INSCRIPTION = "/WEB-INF/creationDeCompte.jsp";
	public static final String JSP_CONNEXION = "/WEB-INF/connexion.jsp";
	public static final String JSP_NOUVELLE_VENTE = "/WEB-INF/nouvelle_vente.jsp";
	public static final String JSP_403 = "/WEB-INF/403.jsp";
	public static final String DETAIL_ENCHERE = "/WEB-INF/detailEnchere.jsp";

	public static final String ACCUEIL = "/";
	public static final String NOUVELLE_VENTE = "/nouveau";
	public static final String CONNEXION = "/connexion";
	public static final String DECONNEXION = "/deconnexion";
	public static final String INSCRIPTION = "/inscription";
	public static final String DETAILS_ARTICLE = "/article";
	public static final String DETAILS_PROFIL = "/profil";
	public static final String MODIFIER_PROFIL = "/modifier_profil";
	public static final String MODIFIER_ARTICLE = "/modifier_article";

	public static final String ATT_LISTE_ERREURS = "listeCodesErreur";
	public static final String ATT_MODIFIABLE = "modifiable";
	public final static String ATT_REQUEST_USER = "utilisateur";
	public final static String ATT_LISTE_ARTICLES = "listArticle";
	
	public static final String CHAMP_NOM_ARTICLE = "nom_vente";
	public static final String CHAMP_DESCRIPTION_ARTICLE = "description_vente";
	public static final String CHAMP_CATEGORIE_ARTICLE = "selectCategoriesAccueilDeco";
	public static final String CHAMP_PRIX_INITIAL_ARTICLE = "prix_initial";
	public static final String CHAMP_DATE_DEBUT_ARTICLE = "date_debut_enchere";
	public static final String CHAMP_DATE_FIN_ARTICLE = "date_fin_enchere";
	public static final String CHAMP_RUE_ARTICLE = "rue_proprietaire";
	public static final String CHAMP_CODE_POSTAL_ARTICLE = "code_postal_proprietaire";
	public static final String CHAMP_VILLE_ARTICLE = "ville_proprietaire";
	
	public static final String CHAMP_MOT_CLE_ACCUEIL = "search";
	public static final String CHAMP_CATEGORIE_ACCUEIL = "selectCategoriesAccueilDeco";

	public static final String CHAMP_PSEUDO_CONNEXION = "pseudo";
	public static final String CHAMP_MOT_DE_PASSE_CONNEXION = "motDePasse";
	
	public static final String ID_ARTICLE_PARAM = "?noArticle=";
	public static final String PSEUDO_UTILISATEUR_PARAM = "?pseudo=";
	
	
}
