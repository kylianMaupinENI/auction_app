package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

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
 * Servlet implementation class EncherirServlet
 */
@WebServlet("/encherir")
public class EncherirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleVenduManager articleVenduManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EncherirServlet() {
		super();
		articleVenduManager = new ArticleVenduManager();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur utilisateur = null;
		HttpSession session = request.getSession();
		if (session != null) {
			utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
		}

		int prixVente = Integer.parseInt(request.getParameter("propositionEnchere"));

		int idArticle = Integer.parseInt(request.getParameter("noArticle"));
		System.out.println(idArticle);
		LocalDate dateEnchere = LocalDate.now();
		System.out.println(dateEnchere);

		try {
			articleVenduManager.updatePrixVenteEnchere(prixVente, idArticle, dateEnchere, utilisateur);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		idArticle = 18;
		request.setAttribute("idArticle", idArticle);
		this.getServletContext()
				.getRequestDispatcher(ServletUtils.DETAIL_ENCHERE + ServletUtils.ID_ARTICLE_PARAM + idArticle)
				.forward(request, response);
		;

	}

}
