package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;

public interface EnchereDAO {

	public int selectGagnant(int noArticle, int montant) throws BusinessException;

}
