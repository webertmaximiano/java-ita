package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Autenticador;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Autenticador a = new Autenticador();
		
		try {
			String nomeUsuario = a.autenticar(login, senha);
			request.setAttribute("nome", nomeUsuario);
			request.getRequestDispatcher("sucesso.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("falha.jsp").forward(request, response);
		}
		
	}

}
