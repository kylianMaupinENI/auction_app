package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
<<<<<<< HEAD
 * Servlet implementation class Dï¿½connexionServlet
 */
@WebServlet("/logout")
public class DeconnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ACCUEIL = "/index.jsp";
       
=======
 * Servlet implementation class DéconnexionServlet
 */
@WebServlet("/deconnexion")
public class DeconnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
>>>>>>> 09d4bfd1920fa775af1f232660e03474404b916e
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeconnexionServlet() {
        super();
<<<<<<< HEAD
        // TODO Auto-generated constructor stub
=======
>>>>>>> 09d4bfd1920fa775af1f232660e03474404b916e
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
<<<<<<< HEAD
		session.setAttribute(ATT_SESSION_USER, null);
		this.getServletContext().getRequestDispatcher(ACCUEIL).forward(request, response);
=======
		
		if(session.getAttribute(ServletUtils.ATT_SESSION_USER) != null) {
			session.setAttribute(ServletUtils.ATT_SESSION_USER, null);
		}
		
		session.invalidate();
		
		this.getServletContext().getRequestDispatcher(ServletUtils.ACCUEIL).forward(request, response);
>>>>>>> 09d4bfd1920fa775af1f232660e03474404b916e
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
