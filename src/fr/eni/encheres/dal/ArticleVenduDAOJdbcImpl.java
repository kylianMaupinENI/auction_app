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

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT_ARTICLE_VENDU = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial,  no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?);";

	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS(no_article,rue, code_postal, ville) VALUES(?,?,?,?);";

	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, "
			+ "date_fin_encheres = ?,prix_initial = ?,  no_categorie = ?;";

	private static final String DELETE_ARTICLE_VENDU = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?;";

	private static final String SELECT = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, a.no_utilisateur,"
			+ "a.no_categorie FROM ARTICLES_VENDUS a";

	private static final String COND_RECHERCHE_RENSEIGNEE = "a.nom_article LIKE '%?%'";

	private static final String COND_CATEGORIE_RENSEIGNEE = "a.no_categorie = ?";

	private static final String COND_UTILISATEUR_CONNECTE = "a.no_utilisateur = ?";

	private static final String COND_EN_COURS = "a.date_debut_encheres < ? AND a.date_fin_encheres > ?";

	private static final String COND_NON_DEBUTEES = "a.date_debut_encheres > ?";

	private static final String COND_TERMINEES = "a.date_fin_encheres < ?";

	private static final String COND_ACHATS = "INNER JOIN ENCHERES e ON a.no_utilisateur = e.no_utilisateur";

	public List<ArticleVendu> select(String recherche, Categorie categorie, Utilisateur utilisateur, boolean isVente,
			EtatVente etatVente) throws BusinessException {
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = null;

			boolean isFirst = true;
			int cpt = 0;
			String query = SELECT;

			if (!utilisateur.equals(null)) {

				if (!isVente) {
					if (isFirst == false) {
						query += " AND";
					} else {
						isFirst = false;
					}

					query += " " + COND_ACHATS + " WHERE ";
				}
				LocalDate dateEncheres = LocalDate.now();
				switch (etatVente) {
				case EN_ATTENTE:
					query += COND_NON_DEBUTEES;
					cpt++;
					pstmt.setDate(cpt, Date.valueOf(dateEncheres));
					break;
				case EN_COURS:
					query += COND_EN_COURS;
					cpt++;
					cpt++;
					pstmt.setDate(cpt, Date.valueOf(dateEncheres));
					pstmt.setDate(cpt, Date.valueOf(dateEncheres));
					break;
				case TERMINEE:
					query += COND_TERMINEES;
					cpt++;
					pstmt.setDate(cpt, Date.valueOf(dateEncheres));
					break;
				}

				if (isFirst == false) {

					query += " AND";

				} else {

					isFirst = false;

				}
				query += " " + COND_UTILISATEUR_CONNECTE;
				cpt++;
				pstmt.setInt(cpt, utilisateur.getNoUtilisateur());
			} else {
				query += " WHERE";
			}

			if (!recherche.equals("") || (recherche != null)) {
				if (isFirst == false) {
					query += " AND";
				} else {
					isFirst = false;
				}
				query += " " + COND_RECHERCHE_RENSEIGNEE;
				cpt++;
				pstmt.setString(cpt, recherche);
			}

			if (!categorie.equals(Categorie.TOUTES)) {
				if (isFirst == false) {
					query += " AND";
				} else {
					isFirst = false;
				}
				query += " " + COND_CATEGORIE_RENSEIGNEE;
				cpt++;
				pstmt.setInt(cpt, categorie.getNoCategorie());
			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

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

				pstmt = cnx.prepareStatement(INSERT_ARTICLE_VENDU, PreparedStatement.RETURN_GENERATED_KEYS);
				setParameter(pstmt, articleVendu);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					articleVendu.setNoArticle(rs.getInt(1));
				}
				rs.close();
				pstmt.close();

				// SI AJOUT OK ALORS AJOUT DANS LA TABLE RETRAIT DE
				// L'ADRESSE DE L'EMETTEUR SI NON RENSEIGNÉE LORS DE L'AJOUT D'UNE VENTE
				pstmt = cnx.prepareStatement(INSERT_RETRAIT);
				pstmt.setInt(1, articleVendu.getNoArticle());
				pstmt.setString(2, articleVendu.getLieuRetrait().getRue());
				pstmt.setString(3, articleVendu.getLieuRetrait().getCodePostal());
				pstmt.setString(4, articleVendu.getLieuRetrait().getVille());
				pstmt.executeUpdate();
				pstmt.close();

				cnx.commit();
			} catch (SQLException e) {
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
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	@Override
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
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	public List<ArticleVendu> selectByUserIdAchat(Utilisateur utilisateur) throws BusinessException {
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_USER_ID_ACHAT);
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
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	public List<ArticleVendu> selectByUserIdVente(Utilisateur utilisateur) throws BusinessException {
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_USER_ID_VENTE);
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
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	/*
	 * public ArticleVendu select_by_id(int id) throws BusinessException {
	 * ArticleVendu articleVendu = new ArticleVendu(); try (Connection cnx =
	 * ConnectionProvider.getConnection()) { PreparedStatement pstmt =
	 * cnx.prepareStatement(SELECT_BY_ID); pstmt.setInt(1, id); ResultSet rs =
	 * pstmt.executeQuery(); while (rs.next()) { String nom_Article =
	 * rs.getString("nom_article"); String description =
	 * rs.getString("description"); LocalDate date_debut_enchere =
	 * rs.getDate("date_debut_encheres").toLocalDate(); LocalDate date_fin_enchere =
	 * rs.getDate("date_fin_encheres").toLocalDate(); int mise_a_prix =
	 * rs.getInt("prix_initial"); EtatVente etat_vente = null;
	 * 
	 * if (date_debut_enchere.isBefore(LocalDate.now())) { etat_vente =
	 * EtatVente.EN_ATTENTE; } else if (date_fin_enchere.isAfter(LocalDate.now())) {
	 * etat_vente = EtatVente.EN_COURS; } else if
	 * (date_debut_enchere.isAfter(LocalDate.now()) &&
	 * date_fin_enchere.isBefore(LocalDate.now())) { etat_vente =
	 * EtatVente.TERMINEE; }
	 * 
	 * Utilisateur utilisateur; utilisateur = articleVendu.getProprietaire();
	 * 
	 * Categorie categorie; categorie = articleVendu.getCategorie();
	 * 
	 * articleVendu = new ArticleVendu(id, nom_Article, description,
	 * date_debut_enchere, date_fin_enchere, mise_a_prix, etat_vente, utilisateur,
	 * categorie); } } catch (SQLException e) { e.printStackTrace();
	 * BusinessException businessException = new BusinessException();
	 * businessException.ajouterErreur(CodesResultatDAL.
	 * LECTURE_LISTE_ARTICLE_VENDU_ECHEC); throw businessException; } if
	 * (articleVendu.getNoArticle() == 0) { BusinessException businessException =
	 * new BusinessException(); businessException.ajouterErreur(CodesResultatDAL.
	 * LECTURE_LISTE_ARTICLE_VENDU_INEXISTANTE); throw businessException; } return
	 * articleVendu; }
	 */

	/*
	 * public List<ArticleVendu> select_by_etat(LocalDate date_debut_enchere,
	 * LocalDate date_fin_enchere) throws BusinessException { List<ArticleVendu>
	 * articleVendu = new ArrayList<ArticleVendu>(); try (Connection cnx =
	 * ConnectionProvider.getConnection()) { PreparedStatement pstmt =
	 * cnx.prepareStatement(SELECT_BY_ETAT); ResultSet rs = pstmt.executeQuery();
	 * while (rs.next()) { int id_article = rs.getInt("no_article"); String
	 * nom_Article = rs.getString("nom_article"); String description =
	 * rs.getString("description"); int mise_a_prix = rs.getInt("prix_initial");
	 * EtatVente etat_vente = null;
	 * 
	 * if (date_debut_enchere.isBefore(LocalDate.now())) { etat_vente =
	 * EtatVente.EN_ATTENTE; } else if (date_fin_enchere.isAfter(LocalDate.now())) {
	 * etat_vente = EtatVente.EN_COURS; } else if
	 * (date_debut_enchere.isAfter(LocalDate.now()) &&
	 * date_fin_enchere.isBefore(LocalDate.now())) { etat_vente =
	 * EtatVente.TERMINEE; }
	 * 
	 * Utilisateur utilisateur; utilisateur = ((ArticleVendu)
	 * articleVendu).getProprietaire();
	 * 
	 * Categorie categorie; categorie = ((ArticleVendu)
	 * articleVendu).getCategorie();
	 * 
	 * articleVendu.add(new ArticleVendu(id_article, nom_Article, description,
	 * date_debut_enchere, date_fin_enchere, mise_a_prix, etat_vente, utilisateur,
	 * categorie)); } } catch (SQLException e) { e.printStackTrace();
	 * BusinessException businessException = new BusinessException();
	 * businessException.ajouterErreur(CodesResultatDAL.
	 * LECTURE_LISTE_ARTICLE_VENDU_ECHEC); throw businessException; } return
	 * articleVendu; }
	 */
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
		} catch (SQLException e) {
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
		} catch (SQLException e) {
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
			pstmt.setInt(6, articleVendu.getCategorie().getNoCategorie());

			int res = pstmt.executeUpdate();
			if (res == 0) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
				throw businessException;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void delete(int id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
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
		stm.setString(1, articleVendu.getNomArticle());
		stm.setString(2, articleVendu.getDescription());
		stm.setDate(3, Date.valueOf(articleVendu.getDateDebutEncheres()));
		stm.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
		stm.setInt(5, articleVendu.getMiseAPrix());
		stm.setInt(6, 1);
		stm.setInt(7, articleVendu.getCategorie().getNoCategorie());
	}

	public List<ArticleVendu> selectByUserIdAndCategorie(Utilisateur utilisateur, Categorie categorie)
			throws BusinessException {
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_USER_ID_AND_CATEGORIE_VENTE);
			ResultSet rs = pstmt.executeQuery();
			utilisateur = ((ArticleVendu) articleVendu).getProprietaire();
			pstmt.setInt(1, ((ArticleVendu) articleVendu).getProprietaire().getNoUtilisateur());
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

				articleVendu.add(new ArticleVendu(id_article, nom_Article, description, date_debut_enchere,
						date_fin_enchere, mise_a_prix, etat_vente, utilisateur, categorie));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

	public List<ArticleVendu> selectByUserIdVentesEnCours(Utilisateur utilisateur) throws BusinessException {
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_USER_ID_EN_COURS_VENTE);
			ResultSet rs = pstmt.executeQuery();
			utilisateur = ((ArticleVendu) articleVendu).getProprietaire();
			pstmt.setInt(1, ((ArticleVendu) articleVendu).getProprietaire().getNoUtilisateur());
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
				Categorie categorie;
				categorie = ((ArticleVendu) articleVendu).getCategorie();

				articleVendu.add(new ArticleVendu(id_article, nom_Article, description, date_debut_enchere,
						date_fin_enchere, mise_a_prix, etat_vente, utilisateur, categorie));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articleVendu;
	}

}
