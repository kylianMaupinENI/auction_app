package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {

	private EnchereDAO enchereDAO;

	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public int selectGagnant(int noArticle, int montant) throws BusinessException {
		return enchereDAO.selectGagnant(noArticle, montant);
	}
}