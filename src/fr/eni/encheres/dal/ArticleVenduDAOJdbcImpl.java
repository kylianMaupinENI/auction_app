package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String SELECT_ALL = "SELECT  no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie  FROM ArticleVendu;";

	private static final String SELECT_BY_NOM = "SELECT  no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie  FROM ArticleVendu WHERE nom_article LIKE '%?%';";

	private static final String SELECT_BY_NOM_ET_CATEGORIE = "SELECT  no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie  FROM ArticleVendu WHERE nom_article LIKE '%?%' AND no_categorie = '?';";

	private static final String SELECT_BY_CATEGORIE = "SELECT  no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie  FROM ArticleVendu WHERE no_categorie = '?';";

	private static final String INSERT_ARTICLE_VENDU = "INSERT INTO ARTICLE_VENDU(nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial,  no_utilisateur, no_categorie  FROM ArticleVendu) VALUES ('?','?','?','?','?','?','?');";

	private static final String INSERT_RETRAIT = "INSERT INTO RETRAIT(rue, code_postal, ville) VALUES('?','?','?');";

	@Override
	public void insert(ArticleVendu articleVendu) throws BusinessException {
		//INSERTION D'UN ARTICLE_VENDU
		if (articleVendu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if (articleVendu.getNoArticle() == 0) {
					pstmt = cnx.prepareStatement(INSERT_ARTICLE_VENDU, PreparedStatement.RETURN_GENERATED_KEYS);
					setParameter(pstmt, articleVendu);
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						articleVendu.setNoArticle(rs.getInt(1));
					}
					rs.close();
					pstmt.close();
				}
				if (articleVendu.getEncheres().size() == 1) { //SI AJOUT OK ALORS AJOUT DANS LA TABLE RETRAIT DE L'ADRESSE DE L'EMETTEUR
					pstmt = cnx.prepareStatement(INSERT_RETRAIT);
					pstmt.setString(1, articleVendu.getEncheres().get(0).getEmmeteur().getAdresse().getRue());
					pstmt.setString(2, articleVendu.getEncheres().get(0).getEmmeteur().getAdresse().getCodePostal());
					pstmt.setString(3, articleVendu.getEncheres().get(0).getEmmeteur().getAdresse().getVille());
					pstmt.executeUpdate();
					pstmt.close();
				}
				cnx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<ArticleVendu> select_all() throws BusinessException {
		//SELECTION DE TOUS LES ARTICLES
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleVendu.add(new ArticleVendu());
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	@Override
	public List<ArticleVendu> select_by_nom(String nom) throws BusinessException {
		//SELECTION DES ARTICLES PAR NOM
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NOM);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleVendu.add(new ArticleVendu());
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	@Override
	public List<ArticleVendu> select_by_nom_and_categorie(String nom, String categorie) throws BusinessException {
		//SELECTION DES ARTICLES PAR NOM ET PAR CATEGORIES
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NOM_ET_CATEGORIE);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleVendu.add(new ArticleVendu());
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	@Override
	public List<ArticleVendu> select_by_categorie(String categorie) throws BusinessException {
		//SELECTION DES ARTICLES PAR CATEGORIES
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_CATEGORIE);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleVendu.add(new ArticleVendu());
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	@Override
	public void update(ArticleVendu articleVendu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	private void setParameter(PreparedStatement stm, ArticleVendu articleVendu) throws SQLException {
		//Paramètres pour la requete d'insertion dans la table ARTICLE_VENDU
		stm.setString(1, articleVendu.getNomArticle());
		stm.setString(2, articleVendu.getDescription());
		stm.setDate(3, Date.valueOf(articleVendu.getDateDebutEncheres()));
		stm.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
		stm.setInt(5, articleVendu.getMiseAPrix());
		stm.setInt(6, articleVendu.getProprietaire().getNoUtilisateur());
		stm.setString(7, articleVendu.getCategorie().getLibelle());
	}

}
