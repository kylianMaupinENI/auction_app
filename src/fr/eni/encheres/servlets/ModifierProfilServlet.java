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
@WebServlet("/ModifierProfilServlet")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
		
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
		
		int noUtilisateur = utilisateur.getNoUtilisateur();
		
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

		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(ServletUtils.VUE_ACCUEIL);
		
		int credit = utilisateur.getCredit();
		boolean administrateur = utilisateur.isAdministrateur();

		try {
			utilisateurManager.modifieUtilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, confirmation, credit, administrateur);
			rd = this.getServletContext().getRequestDispatcher(ServletUtils.VUE_PROFIL);
			
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}

		rd.forward(request, response);
	}

}
