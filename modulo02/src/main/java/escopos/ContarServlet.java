package escopos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ContarServlet
 */
@WebServlet("/contar") //nomeia a rota
public class ContarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request
		if (request.getAttribute("contador")== null) {
			request.setAttribute("contador", new Contador("request"));
			((Contador)request.getAttribute("contador")).contar();
		}
		
		//session
		if (request.getSession().getAttribute("contador")== null) {
			request.getSession().setAttribute("contador", new Contador("session"));
			((Contador)request.getSession().getAttribute("contador")).contar();
		}
		//application
		if (getServletContext().getAttribute("contador")== null) {
			getServletContext().setAttribute("contador", new Contador("application"));
			((Contador)getServletContext().getAttribute("contador")).contar();
		}
		
		PrintWriter pw = response.getWriter();
		pw.append("<html>");
		pw.append("<h1>"+request.getAttribute("contador")+"</h1>");
		pw.append("<h1>"+request.getSession().getAttribute("contador")+"</h1>");
		pw.append("<h1>"+getServletContext().getAttribute("contador")+"</h1>");
		pw.append("</html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
