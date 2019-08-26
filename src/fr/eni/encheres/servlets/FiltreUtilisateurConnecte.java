package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet Filter implementation class FiltreUtilisateurConnecte
 */
@WebFilter(
		urlPatterns = { "/profil", "/nouveau" })
public class FiltreUtilisateurConnecte implements Filter {

    /**
     * Default constructor. 
     */
    public FiltreUtilisateurConnecte() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
				
		HttpSession session = req.getSession(false);
		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute(ServletUtils.ATT_SESSION_USER);
		
		if(session == null || utilisateur == null) {
			res.sendRedirect(ServletUtils.CONNEXION);
		} else{
			chain.doFilter(request, response);
		}
	}

	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
