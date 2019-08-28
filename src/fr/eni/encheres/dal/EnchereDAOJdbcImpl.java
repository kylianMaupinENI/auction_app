package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String SELECT_GAGNANT = "SELECT no_utilisateur FROM ENCHERES WHERE no_article = ? and montant_enchere = ?";

	@Override
	public int selectGagnant(int noArticle, int montant) throws BusinessException {
		int noGagnant = 0;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_GAGNANT);
			pstmt.setInt(1, noArticle);
			pstmt.setInt(2, montant);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				noGagnant = rs.getInt("no_utilisateur");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTE_ARTICLE_VENDU_ECHEC);
			throw businessException;
		}
		return noGagnant;
	}
	

}
