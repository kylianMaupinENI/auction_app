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
	
	private static final String VUE = "/creationDeCompte.jsp";
	private static final String CHAMP_PSEUDO = "";
	private static final String CHAMP_NOM = "";
	private static final String CHAMP_PRENOM = "";
	private static final String CHAMP_EMAIL = "";
	private static final String CHAMP_TELEPHONE = "";
	private static final String CHAMP_CODE_POSTAL = "";
	private static final String CHAMP_RUE = "";
	private static final String CHAMP_CONFIRMATION = "";
	private static final String CHAMP_MOT_DE_PASSE = "";
	private static final String CHAMP_VILLE = "";
	
	UtilisateurManager utilisateurManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
        utilisateurManager = new UtilisateurManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter(CHAMP_PSEUDO);
		String nom = request.getParameter(CHAMP_NOM);
		String prenom = request.getParameter(CHAMP_PRENOM);
		String email = request.getParameter(CHAMP_EMAIL);
		String telephone = request.getParameter(CHAMP_TELEPHONE);
		String rue = request.getParameter(CHAMP_RUE);
		String codePostal = request.getParameter(CHAMP_CODE_POSTAL);
		String ville = request.getParameter(CHAMP_VILLE);
		String motDePasse = request.getParameter(CHAMP_MOT_DE_PASSE);
		String confirmation = request.getParameter(CHAMP_CONFIRMATION);
		
		try {
			if(motDePasse.equals(confirmation)) {
				
			}
			utilisateurManager.ajouteUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, false);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}

}
