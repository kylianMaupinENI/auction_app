package fr.eni.encheres.bo;

public enum Categorie {
	INFORMATIQUE(1, "Informatique"),
	AMEUBLEMENT(2, "Ameublement"),
	VETEMENT(3, "Vêtement"),
	SPORT_LOISIRS(4, "Sport & Loisirs"),
	TOUTES(5, "Toutes");
	
	private int noCategorie;
	private String libelle;

	Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
