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
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(ServletUtils.JSP_CONNEXION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pseudo = request.getParameter(ServletUtils.CHAMP_PSEUDO_CONNEXION);
		String motDePasse = request.getParameter(ServletUtils.CHAMP_MOT_DE_PASSE_CONNEXION);

		HttpSession session = request.getSession();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		RequestDispatcher rd = null;

		try {
			if (utilisateurManager.seConnecter(pseudo, motDePasse)) {
				Utilisateur utilisateur = utilisateurManager.selectionUtilisateur(pseudo);
				session.setAttribute(ServletUtils.ATT_SESSION_USER, utilisateur);
				rd = this.getServletContext().getRequestDispatcher(ServletUtils.ACCUEIL);
			} else {
				session.setAttribute(ServletUtils.ATT_SESSION_USER, null);
			}

		} catch (BusinessException e) {
			request.setAttribute(ServletUtils.ATT_LISTE_ERREURS, e.getListeCodesErreur());
			e.printStackTrace();
			rd = this.getServletContext().getRequestDispatcher(ServletUtils.CONNEXION);

		} finally {
			rd.forward(request, response);
		}

	}

}
