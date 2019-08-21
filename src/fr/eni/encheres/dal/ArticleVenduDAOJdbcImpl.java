package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;

//PAUSE le 21/08/2019 09:30
//RAF : SELECT_BY_ID, SELECT_BY_ETAT
public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String SELECT_ALL = "SELECT  no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie  FROM ArticleVendu;";

	private static final String SELECT_BY_USER_ID = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres,a.date_fin_encheres,"
			+ "a.prix_initial, a.prix_vente, a.no_categorie, e.montant_enchere  FROM ArticleVendu a INNER JOIN ENCHERES e ON a.no_article = e.no_article "
			+ " WHERE no_Utilisateur = ?;";

	private static final String SELECT_BY_ID = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres,a.date_fin_encheres,"
			+ "a.prix_initial, a.prix_vente, a.no_categorie, e.montant_enchere  FROM ArticleVendu a INNER JOIN ENCHERES e ON a.no_article = e.no_article "
			+ "JOIN RETRAIT r ON a.no_article = r.no_article WHERE no_Utilisateur = ?;";

	private static final String SELECT_BY_NOM = "SELECT  no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie  FROM ArticleVendu WHERE nom_article LIKE '%?%';";

	private static final String SELECT_BY_NOM_ET_CATEGORIE = "SELECT  no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie  FROM ArticleVendu WHERE nom_article LIKE '%?%' AND no_categorie = '?';";

	private static final String SELECT_BY_CATEGORIE = "SELECT  no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie  FROM ArticleVendu WHERE no_categorie = '?';";

	private static final String SELECT_BY_ETAT = "SELECT  no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie  FROM ArticleVendu WHERE no_article IN (SELECT null FROM ENCHERES)"
			+ "AND date_debut_encheres = '?' AND date_fin_encheres = '?' ;";

	private static final String INSERT_ARTICLE_VENDU = "INSERT INTO ARTICLE_VENDU(noArticle,nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial,  no_utilisateur, no_categorie  FROM ArticleVendu) VALUES ('?','?','?','?','?','?','?','?');";

	private static final String INSERT_RETRAIT = "INSERT INTO RETRAIT(rue, code_postal, ville) VALUES('?','?','?');";
	
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDU SET nom_article = '?', description = '?', date_debut_encheres = '?', "
			+ "date_fin_encheres = '?',prix_initial = '?',  no_utilisateur = '?', no_categorie = '?';";
	
	private static final String DELETE_ARTICLE_VENDU = "DELETE FROM ARTICLES_VENDU WHERE no_article = ?;";

	
	@Override
	public void insert(ArticleVendu articleVendu) throws BusinessException {
		// INSERTION D'UN ARTICLE_VENDU
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
				if (articleVendu.getEncheres().size() == 1) { // SI AJOUT OK ALORS AJOUT DANS LA TABLE RETRAIT DE
																// L'ADRESSE DE L'EMETTEUR
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
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id_article = rs.getInt("no_article");
				String nom_Article = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate date_debut_enchere = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate date_fin_enchere = rs.getDate("date_fin_encheres").toLocalDate();
				int mise_a_prix = rs.getInt("prix_initial");
				EtatVente etat_vente = null;

				if (date_debut_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.EN_ATTENTE;
				} else if (date_fin_enchere.isAfter(LocalDate.now())) {
					etat_vente = EtatVente.EN_COURS;
				} else if (date_debut_enchere.isAfter(LocalDate.now()) && date_fin_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.TERMINEE;
				}

				Utilisateur utilisateur;
				utilisateur = ((ArticleVendu) articleVendu).getProprietaire();

				Categorie categorie;
				categorie = ((ArticleVendu) articleVendu).getCategorie();

				articleVendu.add(new ArticleVendu(id_article, nom_Article, description, date_debut_enchere,
						date_fin_enchere, mise_a_prix, etat_vente, utilisateur, categorie));
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
		// SELECTION DES ARTICLES PAR NOM
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NOM);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id_article = rs.getInt("no_article");
				String description = rs.getString("description");
				LocalDate date_debut_enchere = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate date_fin_enchere = rs.getDate("date_fin_encheres").toLocalDate();
				int mise_a_prix = rs.getInt("prix_initial");
				EtatVente etat_vente = null;

				if (date_debut_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.EN_ATTENTE;
				} else if (date_fin_enchere.isAfter(LocalDate.now())) {
					etat_vente = EtatVente.EN_COURS;
				} else if (date_debut_enchere.isAfter(LocalDate.now()) && date_fin_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.TERMINEE;
				}

				Utilisateur utilisateur;
				utilisateur = ((ArticleVendu) articleVendu).getProprietaire();

				Categorie categorie;
				categorie = ((ArticleVendu) articleVendu).getCategorie();

				articleVendu.add(new ArticleVendu(id_article, nom, description, date_debut_enchere, date_fin_enchere,
						mise_a_prix, etat_vente, utilisateur, categorie));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	public List<ArticleVendu> selectByUserId(Utilisateur utilisateur) throws BusinessException {
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_USER_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id_article = rs.getInt("no_article");
				String nom_Article = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate date_debut_enchere = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate date_fin_enchere = rs.getDate("date_fin_encheres").toLocalDate();
				int mise_a_prix = rs.getInt("prix_initial");
				EtatVente etat_vente = null;

				if (date_debut_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.EN_ATTENTE;
				} else if (date_fin_enchere.isAfter(LocalDate.now())) {
					etat_vente = EtatVente.EN_COURS;
				} else if (date_debut_enchere.isAfter(LocalDate.now()) && date_fin_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.TERMINEE;
				}

				
				utilisateur = ((ArticleVendu) articleVendu).getProprietaire();

				Categorie categorie;
				categorie = ((ArticleVendu) articleVendu).getCategorie();

				articleVendu.add(new ArticleVendu(id_article, nom_Article, description, date_debut_enchere,
						date_fin_enchere, mise_a_prix, etat_vente, utilisateur, categorie));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	public ArticleVendu select_by_id(int id) throws BusinessException {
		ArticleVendu articleVendu = new ArticleVendu();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String nom_Article = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate date_debut_enchere = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate date_fin_enchere = rs.getDate("date_fin_encheres").toLocalDate();
				int mise_a_prix = rs.getInt("prix_initial");
				EtatVente etat_vente = null;

				if (date_debut_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.EN_ATTENTE;
				} else if (date_fin_enchere.isAfter(LocalDate.now())) {
					etat_vente = EtatVente.EN_COURS;
				} else if (date_debut_enchere.isAfter(LocalDate.now()) && date_fin_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.TERMINEE;
				}

				Utilisateur utilisateur;
				utilisateur = articleVendu.getProprietaire();

				Categorie categorie;
				categorie = articleVendu.getCategorie();

				articleVendu = new ArticleVendu(id, nom_Article, description, date_debut_enchere, date_fin_enchere,
						mise_a_prix, etat_vente, utilisateur, categorie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		if (articleVendu.getNoArticle() == 0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_INEXISTANTE);
			throw businessException;
		}
		return articleVendu;
	}

	public List<ArticleVendu> select_by_etat(LocalDate date_debut_enchere, LocalDate date_fin_enchere)
			throws BusinessException {
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ETAT);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id_article = rs.getInt("no_article");
				String nom_Article = rs.getString("nom_article");
				String description = rs.getString("description");
				int mise_a_prix = rs.getInt("prix_initial");
				EtatVente etat_vente = null;

				if (date_debut_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.EN_ATTENTE;
				} else if (date_fin_enchere.isAfter(LocalDate.now())) {
					etat_vente = EtatVente.EN_COURS;
				} else if (date_debut_enchere.isAfter(LocalDate.now()) && date_fin_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.TERMINEE;
				}

				Utilisateur utilisateur;
				utilisateur = ((ArticleVendu) articleVendu).getProprietaire();

				Categorie categorie;
				categorie = ((ArticleVendu) articleVendu).getCategorie();

				articleVendu.add(new ArticleVendu(id_article, nom_Article, description, date_debut_enchere,
						date_fin_enchere, mise_a_prix, etat_vente, utilisateur, categorie));
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
	public List<ArticleVendu> select_by_nom_and_categorie(String nom, Categorie categorie) throws BusinessException {
		// SELECTION DES ARTICLES PAR NOM ET PAR CATEGORIES
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NOM_ET_CATEGORIE);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id_article = rs.getInt("no_article");
				String nom_Article = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate date_debut_enchere = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate date_fin_enchere = rs.getDate("date_fin_encheres").toLocalDate();
				int mise_a_prix = rs.getInt("prix_initial");
				EtatVente etat_vente = null;

				if (date_debut_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.EN_ATTENTE;
				} else if (date_fin_enchere.isAfter(LocalDate.now())) {
					etat_vente = EtatVente.EN_COURS;
				} else if (date_debut_enchere.isAfter(LocalDate.now()) && date_fin_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.TERMINEE;
				}

				Utilisateur utilisateur;
				utilisateur = ((ArticleVendu) articleVendu).getProprietaire();

				articleVendu.add(new ArticleVendu(id_article, nom_Article, description, date_debut_enchere,
						date_fin_enchere, mise_a_prix, etat_vente, utilisateur, categorie));
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
	public List<ArticleVendu> select_by_categorie(Categorie categorie) throws BusinessException {
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_CATEGORIE);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id_article = rs.getInt("no_article");
				String nom_Article = rs.getString("nom_article");
				String description = rs.getString("description");
				LocalDate date_debut_enchere = rs.getDate("date_debut_encheres").toLocalDate();
				LocalDate date_fin_enchere = rs.getDate("date_fin_encheres").toLocalDate();
				int mise_a_prix = rs.getInt("prix_initial");
				EtatVente etat_vente = null;

				if (date_debut_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.EN_ATTENTE;
				} else if (date_fin_enchere.isAfter(LocalDate.now())) {
					etat_vente = EtatVente.EN_COURS;
				} else if (date_debut_enchere.isAfter(LocalDate.now()) && date_fin_enchere.isBefore(LocalDate.now())) {
					etat_vente = EtatVente.TERMINEE;
				}

				Utilisateur utilisateur;
				utilisateur = ((ArticleVendu) articleVendu).getProprietaire();

				articleVendu.add(new ArticleVendu(id_article, nom_Article, description, date_debut_enchere,
						date_fin_enchere, mise_a_prix, etat_vente, utilisateur, categorie));
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
	public void update(ArticleVendu articleVendu) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt;

			pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getMiseAPrix());
			pstmt.setInt(6, articleVendu.getProprietaire().getNoUtilisateur());
			pstmt.setInt(7, articleVendu.getCategorie().getNoCategorie());

			int res = pstmt.executeUpdate();
			if (res == 0) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
				throw businessException;
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void delete(int id) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE_VENDU);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	private void setParameter(PreparedStatement stm, ArticleVendu articleVendu) throws SQLException {
		// Paramètres pour la requete d'insertion dans la table ARTICLE_VENDU
		stm.setInt(1, articleVendu.getNoArticle());
		stm.setString(2, articleVendu.getNomArticle());
		stm.setString(3, articleVendu.getDescription());
		stm.setDate(4, Date.valueOf(articleVendu.getDateDebutEncheres()));
		stm.setDate(5, Date.valueOf(articleVendu.getDateFinEncheres()));
		stm.setInt(6, articleVendu.getMiseAPrix());
		stm.setInt(7, articleVendu.getProprietaire().getNoUtilisateur());
		stm.setString(8, articleVendu.getCategorie().getLibelle());
	}

}
