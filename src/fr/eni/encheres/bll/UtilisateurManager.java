package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.bll.CodesResultatBLL;
import fr.eni.encheres.BusinessException;

public class UtilisateurManager {

	private static final String EMAIL_PATTERN = "^(.+)@(.+)$";
	/*
	 * Expression régulière permettant la validation du mot de passe.
	 * 
	 * Pour être valide, le mot de passe doit: - Être composé d'au moins 8
	 * caractères. - Contenir au moins une lettre miniscule, une lettre majuscule,
	 * un chiffre et un caractère spécial. - Ne contenir aucun espace ou
	 * tabulations.
	 */
	private static final String MOT_DE_PASSE_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	private static final String CODE_POSTAL_PATTERN = "^(([0-8][0-9])|(9[0-5]))[0-9]{3}$";
	private static final String TELEPHONE_PATTERN = "^(\\+33|0|0033)[0-9]{9}$";

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur ajouteUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, String confirmation)
			throws BusinessException {

		BusinessException businessException = new BusinessException();

		validerPseudo(pseudo, businessException);
		validerNom(nom, businessException);
		validerPrenom(prenom, businessException);
		validerEmail(email, businessException);
		validerTelephone(telephone, businessException);
		validerRue(rue, businessException);
		validerCodePostal(codePostal, businessException);
		validerVille(ville, businessException);
		validerMotDePasse(motDePasse, confirmation, businessException);

		int credit = 0;
		boolean administrateur = false;

		if (businessException.hasErreurs()) {
			throw businessException;
		}

		Adresse adresse = new Adresse(rue, codePostal, ville);
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, adresse, motDePasse, credit,
				administrateur);

		utilisateurDAO.insert(utilisateur);
		
		return utilisateur;
	}

	public void supprimerUtilisateur(String pseudo) throws BusinessException {
		utilisateurDAO.delete(pseudo);
	}

	public void modifieUtilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, String motDePasse, String confirmation,
			int credit, boolean administrateur) throws BusinessException {

		BusinessException businessException = new BusinessException();

		validerPseudo(pseudo, businessException);
		validerNom(nom, businessException);
		validerPrenom(prenom, businessException);
		validerEmail(email, businessException);
		validerTelephone(telephone, businessException);
		validerRue(rue, businessException);
		validerCodePostal(codePostal, businessException);
		validerVille(ville, businessException);
		validerMotDePasse(motDePasse, confirmation, businessException);

		if (businessException.hasErreurs()) {
			throw businessException;
		}

		Adresse adresse = new Adresse(rue, codePostal, ville);
		// Nouveaux paramètres de l'utilisateur
		Utilisateur utilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, adresse, motDePasse);

		utilisateurDAO.update(utilisateur);
	}

	public Utilisateur selectionUtilisateur(String pseudo) throws BusinessException {
		return utilisateurDAO.selectByPseudo(pseudo);
	}

	private void validerVille(String ville, BusinessException businessException) {
		if ((ville == null) || (ville.equals(""))) {
			businessException.ajouterErreur(CodesResultatBLL.VILLE_INVALIDE);
		}
	}

	private void validerCodePostal(String codePostal, BusinessException businessException) {
		if ((codePostal != null) && (!codePostal.equals(""))) {
			if(!codePostal.matches(CODE_POSTAL_PATTERN)) {
				businessException.ajouterErreur(CodesResultatBLL.CODE_POSTAL_INVALIDE);
			}
		} else {
			businessException.ajouterErreur(CodesResultatBLL.CODE_POSTAL_NON_RENSEIGNE);
		}
	}

	private void validerRue(String rue, BusinessException businessException) {
		if ((rue == null) || (rue.equals(""))) {
			businessException.ajouterErreur(CodesResultatBLL.RUE_INVALIDE);
		}
	}

	private void validerTelephone(String telephone, BusinessException businessException) {
		if ((telephone != null) && (!telephone.equals(""))) {
			if(!telephone.matches(TELEPHONE_PATTERN)) {
				businessException.ajouterErreur(CodesResultatBLL.TELEPHONE_INVALIDE);
			}
		} else {
			businessException.ajouterErreur(CodesResultatBLL.TELEPHONE_NON_RENSEIGNE);
		}
	}

	private void validerPrenom(String prenom, BusinessException businessException) {
		if ((prenom == null) || (prenom.equals(""))) {
			businessException.ajouterErreur(CodesResultatBLL.PRENOM_INVALIDE);
		}
	}

	private void validerNom(String nom, BusinessException businessException) {
		if ((nom == null) || (nom.equals(""))) {
			businessException.ajouterErreur(CodesResultatBLL.NOM_INVALIDE);
		}
	}

	private void validerPseudo(String pseudo, BusinessException businessException) throws BusinessException {
		if (utilisateurDAO.selectByPseudo(pseudo) != null) {
			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_UTILISE);
		}
		if ((pseudo == null) || (pseudo.equals(""))) {
			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_INVALIDE);
		}
	}

	private void validerEmail(String email, BusinessException businessException) {
		if (!email.matches(EMAIL_PATTERN)) {
			businessException.ajouterErreur(CodesResultatBLL.EMAIL_INVALIDE);
		}
	}

	private void validerMotDePasse(String motDePasse, String confirmation, BusinessException businessException) {
		if (!motDePasse.matches(MOT_DE_PASSE_PATTERN)) {
			businessException.ajouterErreur(CodesResultatBLL.MOT_DE_PASSSE_INVALIDE);
		}
		if (!motDePasse.equals(confirmation)) {
			businessException.ajouterErreur(CodesResultatBLL.CONFIRMATION_INVALIDE);
		}
	}

	public boolean seConnecter(String pseudo, String motDePasse) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = utilisateurDAO.selectByPseudo(pseudo);
		
		if(utilisateur == null) {
			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_INCONNU);
		} else if (utilisateur.getMotDePasse().equals(motDePasse)) {
			return true;
		} else {
			businessException.ajouterErreur(CodesResultatBLL.MOT_DE_PASSE_INVALIDE);
		}
		
		if (businessException.hasErreurs()) {
			throw businessException;
		}
		return false;
	}
}
