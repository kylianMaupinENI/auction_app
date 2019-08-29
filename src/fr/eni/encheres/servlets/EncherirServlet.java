package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.UtilisateurManager;
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
		UtilisateurManager utilisateurManager = new UtilisateurManager();
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
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			System.err.println(e.getListeCodesErreur());
		}
		int idUtilisateur = utilisateur.getNoUtilisateur();
		try {
			System.out.println(idUtilisateur);
			System.out.println(prixVente);
			
			utilisateurManager.modifieSoldeUtilisateur(idUtilisateur, prixVente, true);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			System.err.println(e.getListeCodesErreur());
		}

		request.setAttribute("idArticle", idArticle);
		this.getServletContext()
				.getRequestDispatcher(ServletUtils.DETAILS_ARTICLE + ServletUtils.ID_ARTICLE_PARAM + idArticle)
				.forward(request, response);
	}
}