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
 * Servlet implementation class InscriptionServlet
 */
@WebServlet(urlPatterns = { "/inscription", "/modificationProfil"})
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UtilisateurManager utilisateurManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
		super();
		utilisateurManager = new UtilisateurManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("blablabla");
		this.getServletContext().getRequestDispatcher(ServletUtils.JSP_INSCRIPTION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String pseudo = request.getParameter(ServletUtils.CHAMP_PSEUDO_INSCRIPTION);
		String nom = request.getParameter(ServletUtils.CHAMP_NOM_INSCRIPTION);
		String prenom = request.getParameter(ServletUtils.CHAMP_PRENOM_INSCRIPTION);
		String email = request.getParameter(ServletUtils.CHAMP_EMAIL_INSCRIPTION);
		String telephone = request.getParameter(ServletUtils.CHAMP_TELEPHONE_INSCRIPTION);
		String rue = request.getParameter(ServletUtils.CHAMP_RUE_INSCRIPTION);
		String codePostal = request.getParameter(ServletUtils.CHAMP_CODE_POSTAL_INSCRIPTION);
		String ville = request.getParameter(ServletUtils.CHAMP_VILLE_INSCRIPTION);
		String motDePasse = request.getParameter(ServletUtils.CHAMP_MOT_DE_PASSE_INSCRIPTION);
		String confirmation = request.getParameter(ServletUtils.CHAMP_CONFIRMATION_INSCRIPTION);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(ServletUtils.JSP_INSCRIPTION);

		try {
			Utilisateur utilisateur = utilisateurManager.ajouteUtilisateur(pseudo, nom, prenom, email, telephone, rue,
					codePostal, ville, motDePasse, confirmation);
			session.setAttribute(ServletUtils.ATT_SESSION_USER, utilisateur);
			rd = this.getServletContext().getRequestDispatcher(ServletUtils.ACCUEIL);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}

		rd.forward(request, response);
	}

}
