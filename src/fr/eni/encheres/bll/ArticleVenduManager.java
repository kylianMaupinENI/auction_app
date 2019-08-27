package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
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
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, Adresse lieuRetrait, Utilisateur proprietaire,
			Categorie categorie) throws BusinessException {

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
			articleVendu.setPrixVente(prixVente);
			articleVendu.setLieuRetrait(lieuRetrait);
			articleVendu.setProprietaire(proprietaire);
			articleVenduDAO.insert(articleVendu);

		} else {
			throw businessException;
		}

		return articleVendu;
	}

	public ArticleVendu modifieArticleVendu(int noArticle, String nomArticle, String description,
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, int prixVente, String rue,
			String codePostal, String ville, Utilisateur proprietaire, Categorie categorie) throws BusinessException {

		BusinessException businessException = new BusinessException();

		validerNomArticle(nomArticle, businessException);
		validerDescription(description, businessException);
		validerDateDebut(dateDebutEncheres, businessException);
		validerDateFin(dateFinEncheres, dateDebutEncheres, businessException);
		validerMiseAPrix(miseAPrix, businessException);
		validerRue(rue, businessException);
		validerCodePostal(codePostal, businessException);
		validerVille(ville, businessException);

		if (businessException.hasErreurs()) {
			throw businessException;
		}

		ArticleVendu articleVendu = null;

		return articleVendu;

	}

	public List<ArticleVendu> selectionArticleVendu(String recherche, String categorie, Utilisateur utilisateur,
			boolean isVente) throws BusinessException {

		Categorie categ = null;

		List<ArticleVendu> articles = new ArrayList<>();

		// Ventes
		if (isVente) {
			if (utilisateur != null) {

			}
		}

		// Achats
		else {
			if (utilisateur != null) {

			}
		}

		return articles;
	}

	public ArticleVendu selectById(int id) throws BusinessException {
		return articleVenduDAO.selectById(id);
	}

	public void updatePrixVenteEnchere(int prixVente, int noArticle, LocalDate dateEnchere) throws BusinessException {
		int no_utilisateur = 1;
		
		BusinessException businessException = new BusinessException();

		validerEnchere(noArticle,prixVente, businessException);
		articleVenduDAO.updatePrixVente(prixVente, noArticle, no_utilisateur, dateEnchere);
	}

	private void validerEnchere(int noArticle, int prixVente, BusinessException businessException)
			throws BusinessException {
		ArticleVendu articleVendu = articleVenduDAO.selectById(noArticle);

		if ((articleVendu.getPrixVente() == 0) && (prixVente <= articleVendu.getMiseAPrix())) {
			businessException.ajouterErreur(CodesResultatBLL.PRIX_INFERIEUR_AU_PRIX_DE_BASE);

		} else if (prixVente <= articleVendu.getPrixVente()) {
			businessException.ajouterErreur(CodesResultatBLL.PRIX_NON_VALIDE);

		}
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
