package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EtatVente;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class NouvelArticleVendu
 */
@WebServlet("/nouvellevente")
public class NouvelArticleVendu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	public static final String ACCUEIL_CONNECTE = "/accueil.jsp";
	public static final String ACCUEIL_DECONNECTE = "/accueil.jsp";
	public static final String NOUVELLE_VENTE = "/nouvelle_vente.jsp";
	

	public static final String CHAMP_NOM = "nom_vente";
	public static final String CHAMP_DESCRIPTION = "description_vente";
	public static final String CHAMP_CATEGORIE = "selectCategoriesAccueilDeco";
	public static final String CHAMP_PRIX_INITIAL = "prix_initial";
	public static final String CHAMP_DATE_DEBUT = "date_debut_enchere";
	public static final String CHAMP_DATE_FIN = "date_fin_enchere";
	public static final String CHAMP_RUE = "rue_proprietaire";
	public static final String CHAMP_CODE_POSTAL = "code_postal_proprietaire";
	public static final String CHAMP_VILLE = "ville_proprietaire";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NouvelArticleVendu() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(NOUVELLE_VENTE);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = null;
		String description = null;
		Categorie categorie = null;
		int prix_initial = 0;
		LocalDate date_debut_enchere = null;
		LocalDate date_fin_enchere = null;
		Utilisateur proprietaire = null;
		Adresse adresse = null;

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		proprietaire = (Utilisateur) session.getAttribute(ATT_SESSION_USER);

		nom = request.getParameter(CHAMP_NOM);
		description = request.getParameter(CHAMP_DESCRIPTION);
		String categ = request.getParameter(CHAMP_CATEGORIE);
		String rue = request.getParameter(CHAMP_RUE);
		String code_postal = request.getParameter(CHAMP_CODE_POSTAL);
		String ville = request.getParameter(CHAMP_VILLE);
		System.out.println(nom);
		System.out.println(description);
		System.out.println(categ);
		switch (categ) {
			case "Informatique":
				categorie = Categorie.INFORMATIQUE;
				break;
			case "Ameublement":
				categorie = Categorie.AMEUBLEMENT;
				break;
			case "V�tement":
				categorie = Categorie.VETEMENT;
				break;
			case "Sport et loisirs":
				categorie = Categorie.SPORT_LOISIRS;
				break;
			case "Toutes":
				categorie = Categorie.TOUTES;
		}

		prixInitial = Integer.parseInt(request.getParameter(CHAMP_PRIX_INITIAL));
		System.out.println(prix_initial);
		List<Integer> listeCodeErreur = new ArrayList<>();
		try {
			DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateDebutEnchere = LocalDate.parse(request.getParameter(CHAMP_DATE_DEBUT), dft);
			dateFinEnchere = LocalDate.parse(request.getParameter(CHAMP_DATE_FIN), dft);
		} catch (DateTimeException e) {
			e.printStackTrace();
			listeCodeErreur.add(CodesResultatServlets.FORMAT_DATE_ERREUR);
		}
		
		//TODO V�rifier si l'article est en cours ou en attente de vente
		
		if(!ville.equals("") || ! rue.equals("") || ! codePostal.equals("")) {
			adresse = new Adresse (rue,codePostal,ville);
		}else {
			adresse = new Adresse (proprietaire.getAdresse().getRue(), proprietaire.getAdresse().getCodePostal(), proprietaire.getAdresse().getVille());
		}
		
		if (listeCodeErreur.size() > 0) {
			request.setAttribute("lstErreurs", listeCodeErreur);
			RequestDispatcher rd = request.getRequestDispatcher(ACCUEIL_CONNECTE);
			rd.forward(request, response);
		} else {
			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			try {
				articleVenduManager.ajouteArticleVendu(nom, description, dateDebutEnchere, dateFinEnchere, prixInitial, prixVente, adresse,  proprietaire, categorie);
				RequestDispatcher rd = request.getRequestDispatcher(ACCUEIL_CONNECTE);

				rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("lstErreurs", e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher(ACCUEIL_CONNECTE);
				rd.forward(request, response);
			}
		}
	}
}
