package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public void insert(Utilisateur utilisateur) throws BusinessException;
	public void update(Utilisateur utilisateur) throws BusinessException;
	public void delete(String pseudo) throws BusinessException;
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException;

}
