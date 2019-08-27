package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private Adresse adresse;
	private String motDePasse;
	private int credit;
	private boolean administrateur;
	private List<ArticleVendu> achats; //Appartiennent à un autre utilisateur
	private List<ArticleVendu> ventes; //Appartiennent à l'utilisateur
	private List<Enchere> encheres;	//Appartiennent à l'utilisateur
	
	public Utilisateur() {
	}

	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, 
			Adresse adresse, String motDePasse, int credit, boolean administrateur) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.motDePasse = motDePasse;
		this.administrateur = administrateur;
		this.credit = credit;
		this.achats = new ArrayList<ArticleVendu>();
		this.ventes = new ArrayList<ArticleVendu>();
		this.encheres = new ArrayList<Enchere>();
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			Adresse adresse, String motDePasse, int credit, boolean administrateur) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.motDePasse = motDePasse;
		this.administrateur = administrateur;
		this.credit = credit;
		this.achats = new ArrayList<ArticleVendu>();
		this.ventes = new ArrayList<ArticleVendu>();
		this.encheres = new ArrayList<Enchere>();
	}

	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			Adresse adresse, String motDePasse, int credit, boolean administrateur, List<ArticleVendu> achats,
			List<ArticleVendu> ventes, List<Enchere> encheres) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.achats = achats;
		this.ventes = ventes;
		this.encheres = encheres;
	}

	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, Adresse adresse,
			String motDePasse) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.motDePasse = motDePasse;
	}

	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email,
			String telephone, Adresse adresse, String motDePasse, int credit, boolean administrateur) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	public List<ArticleVendu> getAchats() {
		return achats;
	}

	public void setAchats(List<ArticleVendu> achats) {
		this.achats = achats;
	}

	public List<ArticleVendu> getVentes() {
		return ventes;
	}

	public void setVentes(List<ArticleVendu> ventes) {
		this.ventes = ventes;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", adresse=" + adresse + ", motDePasse="
				+ motDePasse + ", credit=" + credit + ", administrateur=" + administrateur + ", achats=" + achats
				+ ", ventes=" + ventes + ", encheres=" + encheres + "]";
	}
	
}
