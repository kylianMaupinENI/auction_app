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
@WebServlet("/login")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE = "/connexion.jsp";
	public static final String ACCUEIL = "/accueil.jsp";

	private static final String CHAMP_PSEUDO = "identifiantConnexion";
	private static final String CHAMP_MOT_DE_PASSE = "motDePasseConnexion";

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
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter(CHAMP_PSEUDO);
		String motDePasse = request.getParameter(CHAMP_MOT_DE_PASSE);

		HttpSession session = request.getSession();

		UtilisateurManager utilisateurManager = new UtilisateurManager();

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(VUE);
		
		try {
			if (utilisateurManager.seConnecter(pseudo, motDePasse)) {
				Utilisateur utilisateur = utilisateurManager.selectionUtilisateur(pseudo);
				session.setAttribute(ATT_SESSION_USER, utilisateur);
				rd = this.getServletContext().getRequestDispatcher(ACCUEIL);
			} else {
				session.setAttribute(ATT_SESSION_USER, null);
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

}
