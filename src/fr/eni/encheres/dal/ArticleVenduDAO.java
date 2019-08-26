package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public interface ArticleVenduDAO {
	public void insert(ArticleVendu articleVendu)throws BusinessException;
	public void update(ArticleVendu articleVendu)throws BusinessException;
	public void delete(int id)throws BusinessException;
	public List<ArticleVendu> selectEncheresOuvertes(String recherche, Categorie categorie) throws BusinessException;
	public ArticleVendu selectById(int noArticle) throws BusinessException;
	public void updatePrixVente(int prixVente, int noArticle) throws BusinessException;
	List<ArticleVendu> selectMesAchatsRemportes(String recherche, Categorie categorie, int noUtilisateur)
			throws BusinessException;
	List<ArticleVendu> selectMesAchatsEnCours(String recherche, Categorie categorie, int noUtilisateur)
			throws BusinessException;
}