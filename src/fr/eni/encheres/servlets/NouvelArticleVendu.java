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
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class NouvelArticleVendu
 */
@WebServlet("/nouveau")
public class NouvelArticleVendu extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(ServletUtils.JSP_NOUVELLE_VENTE);
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
		String dateDebutStr;
		String dateFinStr;
		LocalDate dateDebutEnchere = null;
		LocalDate dateFinEnchere = null;
		Utilisateur proprietaire = null;
		Adresse adresse = null;

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		proprietaire = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);

		nom = request.getParameter(ServletUtils.CHAMP_NOM_ARTICLE);
		description = request.getParameter(ServletUtils.CHAMP_DESCRIPTION_ARTICLE);
		String categorieStr = request.getParameter(ServletUtils.CHAMP_CATEGORIE_ARTICLE);
		int prixVente = 0;
		int prixInitial = 0;
		String prixInitialStr = request.getParameter(ServletUtils.CHAMP_PRIX_INITIAL_ARTICLE);
		String rue = request.getParameter(ServletUtils.CHAMP_RUE_ARTICLE);
		String codePostal = request.getParameter(ServletUtils.CHAMP_CODE_POSTAL_ARTICLE);
		String ville = request.getParameter(ServletUtils.CHAMP_VILLE_ARTICLE);
		categorie = Categorie.valueOf(categorieStr);
		dateDebutStr = request.getParameter(ServletUtils.CHAMP_DATE_DEBUT_ARTICLE);
		dateFinStr = request.getParameter(ServletUtils.CHAMP_DATE_FIN_ARTICLE);

		List<Integer> listeCodeErreur = new ArrayList<>();

		try {
			prixInitial = Integer.parseInt(prixInitialStr);
			DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateDebutEnchere = LocalDate.parse(dateDebutStr, dft);
			dateFinEnchere = LocalDate.parse(dateFinStr, dft);
			// TODO V�rifier si l'article est en cours ou en attente de vente

			if (ville.equals("") || rue.equals("") || codePostal.equals("")) {
				rue = proprietaire.getAdresse().getRue();
				codePostal = proprietaire.getAdresse().getCodePostal();
				ville = proprietaire.getAdresse().getVille();
			}

			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			articleVenduManager.ajouteArticleVendu(nom, description, dateDebutEnchere, dateFinEnchere, prixInitial,
					prixVente, rue, codePostal, ville, proprietaire, categorie);
			RequestDispatcher rd = request.getRequestDispatcher(ServletUtils.ACCUEIL);

			rd.forward(request, response);

		} catch (DateTimeException e) {
			e.printStackTrace();
			listeCodeErreur.add(CodesResultatServlets.FORMAT_DATE_ERREUR);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodeErreur.add(CodesResultatServlets.FORMAT_PRIX_ERREUR);
		} catch (NullPointerException e) {
			e.printStackTrace();
			listeCodeErreur.add(CodesResultatServlets.FORMAT_PRIX_ERREUR);
			listeCodeErreur.add(CodesResultatServlets.FORMAT_DATE_ERREUR);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("lstErreurs", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher(ServletUtils.JSP_NOUVELLE_VENTE);
			rd.forward(request, response);

		}
	}
}
