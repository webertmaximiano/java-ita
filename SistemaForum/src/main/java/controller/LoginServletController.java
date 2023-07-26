package controller;

import java.io.IOException;

import dao.UsuarioDAO;
import exception.AutenticadorException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

@WebServlet("/")
public class LoginServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO;
       
    public LoginServletController() {
        super();
        usuarioDAO = new UsuarioDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Exibir a página de login
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		try {
			Usuario usuario = usuarioDAO.autenticar(login, senha);
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/topicos"));
			
		} catch (AutenticadorException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}