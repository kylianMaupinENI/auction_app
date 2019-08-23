package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	public static final String SELECT_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal,"
			+ " ville, mot_de_passe, credit, administrateur FROM UTILISATEUR WHERE pseudo = '?'";

	public static final String INSERT = "INSERT INTO UTILISATEUR (no_utilisateur, pseudo, nom, prenom, email, telephone, rue, "
			+ "code_postal, ville, mot_de_passe, credit, administrateur) VALUES ('?','?','?','?','?','?','?','?','?','?','?','?');";

	public static final String UPDATE = "UPDATE UTILISATEUR SET pseudo = '?', nom = '?', prenom = '?', email = '?', telephone = '?'"
			+ ", rue = '?', code_postal = '?' ville = '?', mot_de_passe = '?', credit = '?', administrateur = '?'  WHERE no_utilisateur = ?;";

	public static final String DELETE = "DELETE FROM UTILISATEUR WHERE pseudo = '?';";

	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		// INSERTION D'UN ARTICLE_VENDU
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt;
			ResultSet rs;
			if (utilisateur.getNoUtilisateur() == 0) {
				pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				setParameter(pstmt, utilisateur);
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void update(Utilisateur utilisateur) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt;

			pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getAdresse().getRue());
			pstmt.setString(7, utilisateur.getAdresse().getCodePostal());
			pstmt.setString(8, utilisateur.getAdresse().getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
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
	public void delete(String pseudo) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setString(1, pseudo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				String nom = rs.getString("nom_utilisateur");
				String prenom = rs.getString("prenom_utilisateur");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				Adresse adresse = utilisateur.getAdresse();
				String motDePasse = rs.getString("mot_de_passe");
				int credit = rs.getInt("credit");
				boolean administrateur = utilisateur.isAdministrateur();

				utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, adresse, motDePasse, credit,
						administrateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		
		return utilisateur;
	}

	private void setParameter(PreparedStatement stm, Utilisateur utilisateur) throws SQLException {
		// Param�tres pour la requete d'insertion dans la table ARTICLE_VENDU
		stm.setInt(1, utilisateur.getNoUtilisateur());
		stm.setString(2, utilisateur.getPseudo());
		stm.setString(3, utilisateur.getNom());
		stm.setString(4, utilisateur.getPrenom());
		stm.setString(5, utilisateur.getEmail());
		stm.setString(6, utilisateur.getTelephone());
		stm.setString(7, utilisateur.getAdresse().getRue());
		stm.setString(8, utilisateur.getAdresse().getCodePostal());
		stm.setString(9, utilisateur.getAdresse().getVille());
		stm.setString(10, utilisateur.getMotDePasse());
		stm.setInt(11, utilisateur.getCredit());
		stm.setBoolean(12, utilisateur.isAdministrateur());
	}

}
