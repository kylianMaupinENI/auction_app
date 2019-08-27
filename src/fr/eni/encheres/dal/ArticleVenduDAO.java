package fr.eni.encheres.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

public interface ArticleVenduDAO {
	public void insert(ArticleVendu articleVendu)throws BusinessException;
	public void update(ArticleVendu articleVendu)throws BusinessException;
	public void delete(int id)throws BusinessException;
	public List<ArticleVendu> selectEncheresOuvertes(String recherche, Categorie categorie) throws BusinessException;
	public List<ArticleVendu> selectMesAchats(String recherche, Categorie categorie, int noUtilisateur)
			throws BusinessException;
}