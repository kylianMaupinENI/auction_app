package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/signin")
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
		this.getServletContext().getRequestDispatcher(ServletUtils.VUE_INSCRIPTION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String pseudo = request.getParameter(ServletUtils.CHAMP_PSEUDO);
		String nom = request.getParameter(ServletUtils.CHAMP_NOM);
		String prenom = request.getParameter(ServletUtils.CHAMP_PRENOM);
		String email = request.getParameter(ServletUtils.CHAMP_EMAIL);
		String telephone = request.getParameter(ServletUtils.CHAMP_TELEPHONE);
		String rue = request.getParameter(ServletUtils.CHAMP_RUE);
		String codePostal = request.getParameter(ServletUtils.CHAMP_CODE_POSTAL);
		String ville = request.getParameter(ServletUtils.CHAMP_VILLE);
		String motDePasse = request.getParameter(ServletUtils.CHAMP_MOT_DE_PASSE);
		String confirmation = request.getParameter(ServletUtils.CHAMP_CONFIRMATION);

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(ServletUtils.VUE_INSCRIPTION);
		
		System.out.println("servlet " + telephone);

		try {
			Utilisateur utilisateur = utilisateurManager.ajouteUtilisateur(pseudo, nom, prenom, email, telephone, rue,
					codePostal, ville, motDePasse, confirmation);
			session.setAttribute(ServletUtils.ATT_SESSION_USER, utilisateur);
			rd = this.getServletContext().getRequestDispatcher(ServletUtils.VUE_ACCUEIL);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}

		rd.forward(request, response);
	}

}
