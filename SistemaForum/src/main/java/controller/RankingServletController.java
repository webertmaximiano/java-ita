package controller;

import java.io.IOException;
import java.util.List;

import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

@WebServlet("/ranking")
public class RankingServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO;
       
    public RankingServletController() {
        super();
        usuarioDAO = new UsuarioDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> ranking = usuarioDAO.ranking();
		
		request.setAttribute("ranking", ranking);
		request.getRequestDispatcher("ranking.jsp").forward(request, response);
	}

}
