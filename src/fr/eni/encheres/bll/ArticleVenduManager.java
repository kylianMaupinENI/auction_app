package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleVenduManager {

	private static final String CODE_POSTAL_PATTERN = "^(([0-8][0-9])|(9[0-5]))[0-9]{3}$";

	private ArticleVenduDAO articleVenduDAO;

	public ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}

	public ArticleVendu ajouteArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, EtatVente etatVente, Adresse lieuRetrait,
			Utilisateur proprietaire, Categorie categorie) throws BusinessException {

		ArticleVendu articleVendu = null;
		
		BusinessException businessException = new BusinessException();
		if (!businessException.hasErreurs()) {
			articleVendu = new ArticleVendu();
			
			articleVendu.setNomArticle(nomArticle);
			articleVendu.setDescription(description);
			articleVendu.setCategorie(categorie);
			articleVendu.setMiseAPrix(miseAPrix);
			articleVendu.setDateDebutEncheres(dateDebutEncheres);
			articleVendu.setDateFinEncheres(dateFinEncheres);
			articleVendu.setEtatVente(etatVente);
			System.out.println(lieuRetrait);
			articleVendu.setLieuRetrait(lieuRetrait);
			articleVendu.setProprietaire(proprietaire);
			articleVenduDAO.insert(articleVendu);

		} else {
			throw businessException;
		}

		return articleVendu;
	}

	public ArticleVendu modifieArticleVendu(int noArticle, String nomArticle, String description,
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, EtatVente etatVente, String rue,
			String codePostal, String ville, Utilisateur proprietaire, Categorie categorie) throws BusinessException {

		BusinessException businessException = new BusinessException();

		validerNomArticle(nomArticle, businessException);
		validerDescription(description, businessException);
		validerDateDebut(dateDebutEncheres, businessException);
		validerDateFin(dateFinEncheres, dateDebutEncheres, businessException);
		validerMiseAPrix(miseAPrix, businessException);
		validerEtatVente(etatVente, businessException);
		validerRue(rue, businessException);
		validerCodePostal(codePostal, businessException);
		validerVille(ville, businessException);

		if (businessException.hasErreurs()) {
			throw businessException;
		}

		ArticleVendu articleVendu = null;

		return articleVendu;

	}
//Attends un objet de type CATEGORIE
	public List<ArticleVendu> SelectionArticleVendu(String mot_cle, String categorie) throws BusinessException {
		
		Categorie categ = null ;
		switch(categorie) {
		case "INFORMATIQUE" : categ = Categorie.INFORMATIQUE; break;
		case "AMEUBLEMENT" : categ = Categorie.AMEUBLEMENT; break;
		case "VETEMENTS" : categ = Categorie.VETEMENT; break;
		case "SPORTS&LOISIRS" : categ = Categorie.SPORT_LOISIRS; break;
		case "TOUTES" : categ = Categorie.TOUTES; break;
		}
		
		if (mot_cle.equals("") && categ.equals(Categorie.TOUTES)) {

			return articleVenduDAO.select_all();

		} else if (!mot_cle.equals("") && ! categ.equals(Categorie.TOUTES)) {

			return articleVenduDAO.select_by_nom_and_categorie(mot_cle, categ);

		} else if (!mot_cle.equals("") && categ.equals(Categorie.TOUTES)) {

			return articleVenduDAO.select_by_nom(mot_cle);
			
		} else if (mot_cle.equals("") && ! categ.equals(Categorie.TOUTES)) {
			
			return articleVenduDAO.select_by_categorie(categ);
		}

		return null;

	}

	private void validerVille(String ville, BusinessException businessException) {
		if ((ville == null) || (ville.equals(""))) {
			businessException.ajouterErreur(CodesResultatBLL.VILLE_INVALIDE);
		}
	}

	private void validerCodePostal(String codePostal, BusinessException businessException) {
		if ((codePostal == null) || (!codePostal.matches(CODE_POSTAL_PATTERN))) {
			businessException.ajouterErreur(CodesResultatBLL.CODE_POSTAL_INVALIDE);
		}
	}

	private void validerRue(String rue, BusinessException businessException) {
		if ((rue == null) || (rue.equals(""))) {
			businessException.ajouterErreur(CodesResultatBLL.RUE_INVALIDE);
		}
	}

	private void validerEtatVente(EtatVente etatVente, BusinessException businessException) {

	}

	private void validerDescription(String description, BusinessException businessException) {
		if ((description == null) || (description.equals(""))) {
			businessException.ajouterErreur(CodesResultatBLL.DESCRIPTION_INVALIDE);
		}
	}

	private void validerNomArticle(String nomArticle, BusinessException businessException) {
		if ((nomArticle == null) || (nomArticle.equals(""))) {
			businessException.ajouterErreur(CodesResultatBLL.NOM_ARTICLE_INVALIDE);
		}
	}

	private void validerMiseAPrix(int miseAPrix, BusinessException businessException) {
		if (miseAPrix <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.PRIX_ARTICLE_INVALIDE);
		}
	}

	private void validerDateDebut(LocalDate dateDebutEncheres, BusinessException businessException) {
		if (dateDebutEncheres.isBefore(LocalDate.now())) {
			businessException.ajouterErreur(CodesResultatBLL.DATE_DEBUT_INVALIDE);
		}
	}

	private void validerDateFin(LocalDate dateFinEncheres, LocalDate dateDebutEncheres,
			BusinessException businessException) {
		if (dateFinEncheres.isBefore(LocalDate.now())) {
			businessException.ajouterErreur(CodesResultatBLL.DATE_FIN_ANTERIEURE_INVALIDE);
		}
		// TODO Ajouter la vérification de la durée (inférieure à deux mois)
	}

}
