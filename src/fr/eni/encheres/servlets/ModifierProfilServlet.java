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
 * Servlet implementation class ModifierServlet
 */
@WebServlet("/modifier")
public class ModifierProfilServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UtilisateurManager utilisateurManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierProfilServlet() {
		super();
		utilisateurManager = new UtilisateurManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//this.getServletContext().getRequestDispatcher(ServletUtils.JSP_INSCRIPTION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Utilisateur utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);

		String pseudo = request.getParameter(ServletUtils.CHAMP_PSEUDO_INSCRIPTION);
		String nom = request.getParameter(ServletUtils.CHAMP_NOM_INSCRIPTION);
		String prenom = request.getParameter(ServletUtils.CHAMP_PRENOM_INSCRIPTION);
		String email = request.getParameter(ServletUtils.CHAMP_EMAIL_INSCRIPTION);
		String telephone = request.getParameter(ServletUtils.CHAMP_TELEPHONE_INSCRIPTION);
		String rue = request.getParameter(ServletUtils.CHAMP_RUE_INSCRIPTION);
		String codePostal = request.getParameter(ServletUtils.CHAMP_CODE_POSTAL_INSCRIPTION);
		String ville = request.getParameter(ServletUtils.CHAMP_VILLE_INSCRIPTION);
		String motDePasse = request.getParameter(ServletUtils.CHAMP_MOT_DE_PASSE_ACTUEL);
		String nouveauMotDePasse =  request.getParameter(ServletUtils.CHAMP_MOT_DE_PASSE_INSCRIPTION);
		String confirmation = request.getParameter(ServletUtils.CHAMP_CONFIRMATION_INSCRIPTION);

		int noUtilisateur = utilisateur.getNoUtilisateur();
		String choixUtilisateur = request.getParameter("ChoixBouton");
		System.out.println("choix u : " + choixUtilisateur);
		String pseudoUtilisateur;


		if(choixUtilisateur.equals("Supprimer mon compte")) {
			try {
				pseudoUtilisateur = request.getParameter("pseudoUtilisateur"); //pseudo a passer en parametre
				System.out.println("pseudo : " + pseudoUtilisateur);
				utilisateurManager.supprimerUtilisateur(pseudoUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher(ServletUtils.DECONNEXION);
			rd.forward(request, response);
		}else if (choixUtilisateur.equals("Enregistrer")) {
			if ((pseudo == null) || pseudo.equals("")) {
				pseudo = utilisateur.getPseudo();
			}
			if ((nom == null) || nom.equals("")) {
				nom = utilisateur.getNom();
			}
			if ((prenom == null) || prenom.equals("")) {
				prenom = utilisateur.getPrenom();
			}
			if ((email == null) || email.equals("")) {
				email = utilisateur.getEmail();
			}
			if ((telephone == null) || telephone.equals("")) {
				telephone = utilisateur.getTelephone();
			}
			if ((rue == null) || rue.equals("")) {
				rue = utilisateur.getAdresse().getRue();
			}
			if ((codePostal == null) || codePostal.equals("")) {
				codePostal = utilisateur.getAdresse().getCodePostal();
			}
			if ((ville == null) || ville.equals("")) {
				ville = utilisateur.getAdresse().getVille();
			}
			if ((motDePasse == null) || motDePasse.equals("")) {
				motDePasse = utilisateur.getMotDePasse();
			}
			if((nouveauMotDePasse == null) || nouveauMotDePasse.equals("")){
				nouveauMotDePasse = utilisateur.getMotDePasse();
			}
			if((confirmation == null) || confirmation.equals("")){
				confirmation = utilisateur.getMotDePasse();
			}
	
	
			int credit = utilisateur.getCredit();
			boolean administrateur = utilisateur.isAdministrateur();
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher(ServletUtils.ACCUEIL);

			try {
				utilisateurManager.modifieUtilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal,
						ville, nouveauMotDePasse, confirmation, credit, administrateur);
				rd = this.getServletContext().getRequestDispatcher(ServletUtils.DETAILS_PROFIL);
	
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				e.printStackTrace();
			}
			rd.forward(request, response);
		}
	}

}
