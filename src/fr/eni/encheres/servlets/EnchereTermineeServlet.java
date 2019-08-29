package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class EnchereTermineeServlet
 */
@WebServlet("/terminee")
public class EnchereTermineeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnchereTermineeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String noArticleStr = request.getParameter(ServletUtils.ATT_ARTICLE_GAGNANT);
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		EnchereManager enchereManager = new EnchereManager();

		Utilisateur utilisateurCo = null;
		HttpSession session = request.getSession();
		if (session != null) {
			utilisateurCo = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
		}

		Utilisateur gagnant = null;
		ArticleVendu article = null;
		try {
			int noUtilisateur = utilisateurCo.getNoUtilisateur();
			int noArticle = Integer.parseInt(noArticleStr);
			article = articleVenduManager.selectById(noArticle);
			int montant = article.getPrixVente();
			int noGagnant = enchereManager.selectGagnant(noArticle, montant);
			gagnant = utilisateurManager.selectById(noGagnant);
			
			if (gagnant.getNoUtilisateur() != noUtilisateur) {
				int prixAchat = article.getPrixVente();
				utilisateurManager.modifieSoldeUtilisateur(noUtilisateur, prixAchat, false);
			}
		} catch (BusinessException e) {
			e.printStackTrace();

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		request.setAttribute(ServletUtils.ATT_USER_GAGNANT, gagnant);
		request.setAttribute(ServletUtils.ATT_ARTICLE_GAGNANT, article);
		this.getServletContext().getRequestDispatcher(ServletUtils.JSP_ENCHERE_REMPORTEE).forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
