package controller;

import java.io.IOException;
import java.util.List;

import dao.ComentarioDAO;
import dao.TopicoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comentario;
import model.Topico;

@WebServlet("/exibeTopico")
public class ExibeTopicoServletController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TopicoDAO topicoDAO;
    private ComentarioDAO comentarioDAO;

    public ExibeTopicoServletController() {
        super();
        topicoDAO = new TopicoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicoId = Integer.parseInt(request.getParameter("id")); // Supondo que o ID do tópico seja passado na URL como um parâmetro

        // Buscar informações do tópico pelo ID usando o método do TopicoDAO
        Topico topico = topicoDAO.buscarTopico(topicoId);

        // Buscar os comentários do tópico usando o método do TopicoDAO
        List<Comentario> comentarios = comentarioDAO.comentariosPorTopico(topicoId);

        // Definir os atributos para a página exibeTopico.jsp
        request.setAttribute("topicoTitulo", topico.getTitulo());
        request.setAttribute("topicoAutor", topico.getUsuario().getNome());
        request.setAttribute("topicoTexto", topico.getConteudo());

        // Verificar se a lista de comentários não é nula antes de enviá-la para o JSP
        if (comentarios != null) {
            request.setAttribute("comentarios", comentarios);
        }

        // Encaminhar para a página exibeTopico.jsp
        request.getRequestDispatcher("exibeTopico.jsp").forward(request, response);
    }
}
