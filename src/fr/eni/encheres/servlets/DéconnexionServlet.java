package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DéconnexionServlet
 */
@WebServlet("/logout")
public class DéconnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DéconnexionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute(ServletUtils.ATT_SESSION_USER) != null) {
			session.setAttribute(ServletUtils.ATT_SESSION_USER, null);
		}
		
		session.invalidate();
		
		this.getServletContext().getRequestDispatcher(ServletUtils.JSP_ACCUEIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
