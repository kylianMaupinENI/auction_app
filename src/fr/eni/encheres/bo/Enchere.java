package fr.eni.encheres.bo;

import java.time.LocalDate;

public class Enchere {
	private int noEnchere;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private Utilisateur emmeteur;
	private ArticleVendu article;
	
	public Enchere() {
		
	}

	public Enchere(int noEnchere, LocalDate dateEnchere, int montantEnchere, Utilisateur emmeteur,
			ArticleVendu article) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.emmeteur = emmeteur;
		this.article = article;
	}
	
	public Enchere(LocalDate dateEnchere, int montantEnchere, Utilisateur emmeteur,
			ArticleVendu article) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.emmeteur = emmeteur;
		this.article = article;
	}

	public int getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Utilisateur getEmmeteur() {
		return emmeteur;
	}

	public void setEmmeteur(Utilisateur emmeteur) {
		this.emmeteur = emmeteur;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere
				+ ", emmeteur=" + emmeteur + ", article=" + article + "]";
	}
	
}
