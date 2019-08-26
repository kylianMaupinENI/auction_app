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
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	//Visionner le détail d'une vente
	private static final String SELECT_BY_ID = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, "
			+ "a.prix_initial, a.prix_vente, a.no_categorie, r.rue, r.code_postal, r.ville, u.pseudo "
			+ "FROM ARTICLES_VENDUS a "
			+ "INNER JOIN RETRAITS r ON a.no_article = r.no_article "
			+ "INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "WHERE no_article = ?";
	
	//Page achats en cours (connecté / déconnecté)
	//Page ventes en cours connecté
	//Page ventes (vente en cours, vente non debutées, vente_terminee)
	private static final String SELECT_VENTE = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, "
			+ "a.prix_initial, a.prix_vente, u.pseudo, r.rue, r.code_postal, r.ville "
			+ "FROM ARTICLES_VENDUS a "
			+ "INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur"
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie"
			+ "INNER JOIN RETRAITS r ON r.no_article = a.no_artcle";
	
	//Page achats en cours
	//Page enchères remportées
	private static final String SELECT_ACHATS = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, "
			+ "a.prix_initial, a.prix_vente, u.pseudo "
			+ "FROM ARTICLES_VENDUS a "
			+ "INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur"
			+ "INNER JOIN ENCHERES e ON e.no_utilisateur = a.no_utilisateur"
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie";
	
	private static final String NON_DEBUTE = " WHERE a.date_debut_encheres > ?";
	
	private static final String EN_COURS = " WHERE a.date_debut_encheres < ? AND a.date_fin_encheres > ?";
	
	private static final String TERMINE = " WHERE a.date_fin_encheres < ?";

	private static final String COND_RECHERCHE = " AND a.nom_article LIKE '%?%'";
	
	private static final String COND_CATEGORIE = " AND c.libelle = ?";

	private static final String INSERT_ARTICLE_VENDU = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres,"
			+ "prix_initial,  no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?);";

	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS(no_article,rue, code_postal, ville) VALUES(?,?,?,?);";
	
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = '?', description = '?', date_debut_encheres = '?', "
			+ "date_fin_encheres = '?',prix_initial = '?',  no_utilisateur = '?', no_categorie = '?';";
	
	private static final String DELETE_ARTICLE_VENDU = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?;";

	
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
	
	public ArticleVendu selectById(int noArticle) throws BusinessException {
		ArticleVendu article = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, noArticle);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String nomArticle = rs.getString("a.nom_article");
				String description = rs.getString("a.description");
				LocalDate dateDebutEncheres = rs.getDate("a.date_debut_encheres").toLocalDate();
				LocalDate dateFinEncheres = rs.getDate("a.date_fin_encheres").toLocalDate();
				int prixInitial = rs.getInt("a.prix_initial");
				int prixVente = rs.getInt("a.prix_vente");
				String categorieStr = rs.getString("c.libelle");
				String rue = rs.getString("r.rue");
				String codePostal = rs.getString("r.code_postal");
				String ville = rs.getString("r.ville");
				String pseudo = rs.getString("u.pseudo");
				
				UtilisateurManager utilisateurManager = new UtilisateurManager();
				Utilisateur utilisateur = utilisateurManager.selectionUtilisateur(pseudo);
				Adresse adresse = new Adresse(rue, codePostal, ville);
				
				Categorie categorie = null ;
				switch(categorieStr.toUpperCase()) {
					case "INFORMATIQUE" : 
						categorie = Categorie.INFORMATIQUE; 
						break;
					case "AMEUBLEMENT" : 
						categorie = Categorie.AMEUBLEMENT; 
						break;
					case "VETEMENTS" : 
						categorie = Categorie.VETEMENT; 
						break;
					case "SPORTS&LOISIRS" : 
						categorie = Categorie.SPORT_LOISIRS; 
						break;
					case "TOUTES" : 
						categorie = Categorie.TOUTES; 
				}
				
				article = new ArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, adresse, utilisateur, categorie);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		
		return article;
	}

	@Override
	public List<ArticleVendu> selectEncheresOuvertes(String recherche, Categorie categorie) throws BusinessException {
		List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			String query = SELECT_VENTE;
			
			if((recherche != null) || (!recherche.equals(""))) {
				query += COND_RECHERCHE + recherche;
			}
			if(categorie != Categorie.TOUTES) {
				query += COND_CATEGORIE;
			}
			
			PreparedStatement pstmt = cnx.prepareStatement(query);
			
			int curParam = 1;
			
			if(query.contains(COND_RECHERCHE)) {
				pstmt.setString(curParam, recherche);
				curParam++;
			}
			if(query.contains(COND_CATEGORIE)) {
				pstmt.setString(curParam, categorie.getLibelle());
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int noArticle = rs.getInt("a.no_article");
				String nomArticle = rs.getString("a.nom_article");
				String description = rs.getString("a.description");
				LocalDate dateDebutEnchere = rs.getDate("a.date_debut_encheres").toLocalDate();
				LocalDate dateFinEnchere = rs.getDate("a.date_fin_encheres").toLocalDate();
				int prixInitial = rs.getInt("a.prix_initial");
				int prixVente = rs.getInt("a.prix_vente");
				String pseudo = rs.getString("u.pseudo");
				String rue = rs.getString("r.rue");
				String codePostal = rs.getString("r.code_postal");
				String ville = rs.getString("r.ville");
				
				UtilisateurManager utilisateurManager = new UtilisateurManager();
				Utilisateur utilisateur = utilisateurManager.selectionUtilisateur(pseudo);
				Adresse adresse = new Adresse(rue, codePostal, ville);

				articlesVendus.add(new ArticleVendu(noArticle, nomArticle, description, dateDebutEnchere,
						dateFinEnchere, prixInitial, prixVente, adresse, utilisateur, categorie));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return articlesVendus;
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

		} catch (SQLException e) {
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
		stm.setString(1, articleVendu.getNomArticle());
		stm.setString(2, articleVendu.getDescription());
		stm.setDate(3, Date.valueOf(articleVendu.getDateDebutEncheres()));
		stm.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
		stm.setInt(5, articleVendu.getMiseAPrix());
		stm.setInt(6, 1);
		stm.setInt(7, articleVendu.getCategorie().getNoCategorie());
	}

}
