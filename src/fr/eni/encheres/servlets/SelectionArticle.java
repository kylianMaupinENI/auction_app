package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class SelectionArticle
 */
@WebServlet("/accueil")
public class SelectionArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectionArticle() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		List<ArticleVendu> articles = new ArrayList<>();

		Utilisateur utilisateur = null;

		HttpSession session = request.getSession();
		if (session != null) {
			utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
		}
		
		try {
			articles = articleVenduManager.selectEncheresOuvertes("", Categorie.TOUTES);
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}

		request.setAttribute(ServletUtils.ATT_LISTE_ARTICLES, articles);
		
		this.getServletContext().getRequestDispatcher(ServletUtils.JSP_ACCUEIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String recherche = request.getParameter(ServletUtils.CHAMP_MOT_CLE_ACCUEIL);
		String categorieStr = request.getParameter(ServletUtils.CHAMP_CATEGORIE_ACCUEIL);
		
		Categorie categorie = Categorie.fromString(categorieStr);

		System.out.println(recherche);
		System.out.println(categorie);
		
		
		RequestDispatcher rd;

		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		Utilisateur utilisateur = null;

		HttpSession session = request.getSession();
		if (session != null) {
			utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
		}
		
		if (utilisateur != null) {
			
			String type = request.getParameter("radioCategories");
			if(type == null) {
				type = "radioAchat";
			}
			if(type.equals("radioAchat")) {
				String [] typesAchat;
				if(request.getParameterValues("typesAchat") == null) {
					typesAchat  = new String []{"encheresOuvertes"};
				}else {
					typesAchat  = request.getParameterValues("typesAchat");
				}
				
				for (int i = 0; i < typesAchat.length; i++) {
					List<ArticleVendu> l = new ArrayList<>();
					
					if(typesAchat[i].equals("encheresOuvertes")) {
						try {
							l = articleVenduManager.selectEncheresOuvertes(recherche, categorie);
						} catch (BusinessException e) {
							request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
							e.printStackTrace();
						}
						
					} else if(typesAchat[i].equals("mesEncheresEnCours")) {
						try {
							l = articleVenduManager.selectMesAchatsEnCours(recherche, categorie, utilisateur);
						} catch (BusinessException e) {
							request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
							e.printStackTrace();
						}
						
					}  else if(typesAchat[i].equals("mesEncheresRemportees")) {
						try {
							l = articleVenduManager.selectMesAchatsRemportes(recherche, categorie, utilisateur);
						} catch (BusinessException e) {
							request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
							e.printStackTrace();
						}
						
					}
					articles.addAll(l);
				}
				
			} else {
				String typesVente [] = request.getParameterValues("typesVente");
				
				for (int i = 0; i < typesVente.length; i++) {
					List<ArticleVendu> l = new ArrayList<>();
					
					if(typesVente[i].equals("mesVentesEnCours")) {
						try {
							l = articleVenduManager.selectMesVentesEnCours(recherche, categorie, utilisateur);
						} catch (BusinessException e) {
							request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
							e.printStackTrace();
						}
						articles.addAll(l);
						
					} else if(typesVente[i].equals("mesVenetsNonDebutees")) {
						try {
							l = articleVenduManager.selectionVentesNonDebutees(recherche, categorie, utilisateur);
						} catch (BusinessException e) {
							request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
							e.printStackTrace();
						}
						articles.addAll(l);
						
					}  else if(typesVente[i].equals("mesVentesTerminees")) {
						try {
							l = articleVenduManager.selectionVentesTerminees(recherche, categorie, utilisateur);
						} catch (BusinessException e) {
							request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
							e.printStackTrace();
						}
						articles.addAll(l);
					}
				}
			}
		} else {
			try {
				articles = articleVenduManager.selectEncheresOuvertes(recherche, categorie);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				e.printStackTrace();
			}
		}
		request.setAttribute(ServletUtils.ATT_LISTE_ARTICLES, articles);
		this.getServletContext().getRequestDispatcher(ServletUtils.JSP_ACCUEIL).forward(request, response);

	}
}
