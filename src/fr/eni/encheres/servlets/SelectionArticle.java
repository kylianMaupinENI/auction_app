package fr.eni.encheres.servlets;

import java.io.IOException;
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
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class SelectionArticle
 */
@WebServlet("/selectionarticle")
public class SelectionArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ATT_SESSION_USER = "sessionUtilisateur";

	private static final String CHAMP_CHOIX_VENTE = "choix_vente";
	private static final String CHAMP_MOT_CLE = "search";
	private static final String CHAMP_CATEGORIE = "selectCategoriesAccueilDeco";
	public static final String ACCUEIL_CONNECTE = "/index.jsp";
	public static final String ACCUEIL_DECONNECTE = "/index.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectionArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int choix = Integer.parseInt(request.getParameter(CHAMP_CHOIX_VENTE));
		String mot_cle = request.getParameter(CHAMP_MOT_CLE);
		String categorie = request.getParameter(CHAMP_CATEGORIE);
		RequestDispatcher rd;
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		Utilisateur utilisateur = null;
		
		HttpSession session = request.getSession();
		utilisateur = (Utilisateur) session.getAttribute(ATT_SESSION_USER);

		try {
			articleVendu = articleVenduManager.SelectionArticleVendu(mot_cle, categorie);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		request.setAttribute("articleVendu", articleVendu);

		if (utilisateur.equals(null)) {

			rd = this.getServletContext().getRequestDispatcher(ACCUEIL_DECONNECTE);
		} else {
			rd = this.getServletContext().getRequestDispatcher(ACCUEIL_CONNECTE);
		}

		rd.forward(request, response);

	}

}
