package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/profil")
public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilisateurManager utilisateurManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherProfilServlet() {
		super();
		utilisateurManager = new UtilisateurManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Utilisateur utilisateur = null;

		if (session != null) {
			utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
		}

		boolean modifiable = false;

		// Si le profil affiché est celui de l'utilisateur connecté, il peut le
		// modifier.
		request.setAttribute(ServletUtils.ATT_MODIFIABLE, modifiable);
		
		request.setAttribute("utilisateur", utilisateur);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(ServletUtils.VUE_PROFIL);
		rd.forward(request, response);
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
