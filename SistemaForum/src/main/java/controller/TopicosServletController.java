package controller;

import java.io.IOException;
import java.util.List;

import dao.TopicoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Topico;
import model.Usuario;

@WebServlet("/topicos")
public class TopicosServletController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TopicoDAO topicoDAO;

    public TopicosServletController() {
        super();
        topicoDAO = new TopicoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obter o login do usuário autenticado armazenado na sessão
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuario");
        String loginUsuarioLogado = usuarioLogado.getLogin();

        // Buscar os tópicos do usuário logado utilizando o método modificado do TopicoDAO
        List<Topico> topicos = topicoDAO.todosTopicos(loginUsuarioLogado);

        request.setAttribute("topicos", topicos);
        request.getRequestDispatcher("topicos.jsp").forward(request, response);
    }

}

