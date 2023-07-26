package controller;

import java.io.IOException;

import dao.ComentarioDAO;
import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comentario;
import model.Topico;
import model.Usuario;

@WebServlet("/comentario")
public class ComentarioServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ComentarioDAO comentarioDAO;
	private UsuarioDAO usuarioDAO;
       
    public ComentarioServletController() {
        super();
        comentarioDAO = new ComentarioDAO();
        usuarioDAO = new UsuarioDAO();
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	
	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	int topicoId = Integer.parseInt(request.getParameter("topicoId"));
	String textoComentario = request.getParameter("comentario");
	
	Comentario comentario = new Comentario();
	comentario.setComentario(textoComentario);
	
	Topico topico = new Topico();
	topico.setId_topico(topicoId);
	comentario.setTopico(topico);
	
	comentario.setUsuario(usuario);
	
	comentarioDAO.inserirNovo(comentario);
	usuarioDAO.adicionarPontos(usuario.getLogin(), 3);
	
	response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/topico?topicoId=" + topicoId));
	}

}