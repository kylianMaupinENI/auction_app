package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	Utilisateur selectByPseudo();

	void insert(Utilisateur utilisateur);

	void delete(int noUtilisateur);

	void update(Utilisateur utilisateur);

}
