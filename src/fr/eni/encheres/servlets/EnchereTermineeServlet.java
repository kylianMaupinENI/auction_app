package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noArticleStr = request.getParameter(ServletUtils.ATT_ARTICLE_GAGNANT);
		System.out.println(noArticleStr);
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		ArticleVendu article;
		Utilisateur gagnant = null;
		try {
			int noArticle = Integer.parseInt(noArticleStr);
			article = articleVenduManager.selectById(noArticle);
			int noUtilisateur = article.getProprietaire().getNoUtilisateur();
			gagnant = utilisateurManager.selectById(noUtilisateur);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		request.setAttribute(ServletUtils.ATT_USER_GAGNANT, gagnant);
		this.getServletContext().getRequestDispatcher(ServletUtils.JSP_ENCHERE_REMPORTEE).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}