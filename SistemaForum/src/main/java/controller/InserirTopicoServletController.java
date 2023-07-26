package controller;

import java.io.IOException;

import dao.TopicoDAO;
import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Topico;
import model.Usuario;

@WebServlet("/inserirTopico")
	public class InserirTopicoServletController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private TopicoDAO topicoDAO;
		private UsuarioDAO usuarioDAO;
	       
	    public InserirTopicoServletController() {
	        super();
	        topicoDAO = new TopicoDAO();
	        usuarioDAO = new UsuarioDAO();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("inserirTopico.jsp").forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			
			Topico topico = new Topico();
			topico.setTitulo(request.getParameter("titulo"));
			topico.setConteudo(request.getParameter("conteudo"));
			
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			topico.setUsuario(usuario);
			
			topicoDAO.inserirTopico(topico);
			usuarioDAO.adicionarPontos(usuario.getLogin(), 10);
			
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/topicos"));
		}

	}