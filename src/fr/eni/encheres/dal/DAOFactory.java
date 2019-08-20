package fr.eni.encheres.dal;

public abstract class DAOFactory {
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();	
	}
}
