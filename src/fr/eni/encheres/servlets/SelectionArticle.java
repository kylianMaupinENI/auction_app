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
@WebServlet("/accueil")
public class SelectionArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectionArticle() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		List<ArticleVendu> articles = new ArrayList<>();

		Utilisateur utilisateur = null;

		HttpSession session = request.getSession();
		if (session != null) {
			utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
		}

		try {
			articles = articleVenduManager.selectionArticleVendu("", "", utilisateur, false);
			request.setAttribute(ServletUtils.ATT_LISTE_ARTICLES, articles);

		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher(ServletUtils.JSP_ACCUEIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mot_cle = request.getParameter(ServletUtils.CHAMP_MOT_CLE_ACCUEIL);
		String categorie = request.getParameter(ServletUtils.CHAMP_CATEGORIE_ACCUEIL);
		RequestDispatcher rd;
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		Utilisateur utilisateur = null;

		HttpSession session = request.getSession();
		if (session != null) {
			utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
		}

		try {
			articleVendu = articleVenduManager.selectionArticleVendu(mot_cle, categorie, utilisateur,false);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}

		request.setAttribute("articleVendu", articleVendu);
		this.getServletContext().getRequestDispatcher(ServletUtils.JSP_ACCUEIL).forward(request, response);

	}

}
