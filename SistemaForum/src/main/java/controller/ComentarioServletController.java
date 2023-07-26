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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Verifica se o usuário está autenticado antes de permitir a adição do comentário
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null) {
            // Redireciona para a página de login caso o usuário não esteja autenticado
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Obtém o ID do tópico e o texto do comentário do request
        String topicoIdString = request.getParameter("topicoId");
        String textoComentario = request.getParameter("comentario");

        // Verifica se os parâmetros são válidos
        if (topicoIdString == null || textoComentario == null || textoComentario.isEmpty()) {
            // Redireciona para a página de exibição de erro caso os parâmetros sejam inválidos
            response.sendRedirect(request.getContextPath() + "/erro.jsp");
            return;
        }

        // Converte o ID do tópico para um valor numérico
        int topicoId;
        try {
            topicoId = Integer.parseInt(topicoIdString);
        } catch (NumberFormatException e) {
            // Redireciona para a página de exibição de erro caso o ID do tópico não seja um número válido
            response.sendRedirect(request.getContextPath() + "/erro.jsp");
            return;
        }

        // Cria um novo objeto Comentario, preenchendo suas informações
        Comentario comentario = new Comentario();
        comentario.setComentario(textoComentario);

        Topico topico = new Topico();
        topico.setId_topico(topicoId);
        comentario.setTopico(topico);

        comentario.setUsuario(usuario);

        // Insere o novo comentário no banco de dados usando o ComentarioDAO
        comentarioDAO.inserirNovo(comentario);

        // Adiciona pontos ao usuário que fez o comentário, usando o UsuarioDAO
        usuarioDAO.adicionarPontos(usuario.getLogin(), 3);

        // Redireciona o usuário de volta para a página de exibição do tópico após adicionar o comentário
        response.sendRedirect(request.getContextPath() + "/exibeTopico?id=" + topicoId);
    }
}
