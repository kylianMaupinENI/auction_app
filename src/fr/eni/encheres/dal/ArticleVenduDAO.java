package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	public void insert(ArticleVendu articleVendu)throws BusinessException;
	public void update(ArticleVendu articleVendu)throws BusinessException;
	public void delete(int id)throws BusinessException;
	public List<ArticleVendu> select_all()throws BusinessException;
	public List<ArticleVendu>select_by_nom(String nom)throws BusinessException;
	public List<ArticleVendu>select_by_nom_and_categorie(String nom, String categorie)throws BusinessException;
	public List<ArticleVendu>select_by_categorie(String categorie)throws BusinessException;
	
}
