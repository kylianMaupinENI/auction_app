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
	public List<ArticleVendu> select_all()throws BusinessException;
	public List<ArticleVendu>select_by_nom(String nom)throws BusinessException;
	public List<ArticleVendu>select_by_nom_and_categorie(String nom, Categorie categorie)throws BusinessException;
	public List<ArticleVendu> selectByUserId(Utilisateur utilisateur) throws BusinessException;
	public ArticleVendu select_by_id(int id) throws BusinessException;
	public List<ArticleVendu> selectByUserIdVente(Utilisateur utilisateur) throws BusinessException;
	public List<ArticleVendu> selectByUserIdAchat(Utilisateur utilisateur) throws BusinessException;
	public List<ArticleVendu> select_by_etat(LocalDate date_debut_enchere, LocalDate date_fin_enchere) throws BusinessException;
	public List<ArticleVendu>select_by_categorie(Categorie categorie)throws BusinessException;
	List<ArticleVendu> selectByUserIdAndCategorie(Utilisateur utilisateur, Categorie categorie) throws BusinessException;
	
}
