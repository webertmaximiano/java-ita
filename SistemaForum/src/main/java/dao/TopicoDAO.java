package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseConection;
import model.Topico;
import model.Usuario;

public class TopicoDAO {

	public List<Topico> todosTopicos(String loginUsuarioLogado) {
	    List<Topico> topicos = new ArrayList<>();

	    try (Connection conn = DataBaseConection.getConnection()) {
	        String sql = "SELECT t.id_topico, t.titulo, t.conteudo, u.nome AS nome_usuario FROM topico t JOIN usuario u ON t.login = u.login WHERE t.login = ?";

	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, loginUsuarioLogado);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Topico topico = new Topico();
	            topico.setId_topico(resultSet.getInt("id_topico"));
	            topico.setTitulo(resultSet.getString("titulo"));
	            topico.setConteudo(resultSet.getString("conteudo"));

	            Usuario usuario = new Usuario();
	            usuario.setNome(resultSet.getString("nome_usuario"));
	            topico.setUsuario(usuario);

	            topicos.add(topico);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return topicos;
	}



    public void inserirTopico(Topico topico) {
        try (Connection conn = DataBaseConection.getConnection()) {
            String sql = "INSERT INTO topico (titulo, conteudo, login) VALUES (?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, topico.getTitulo());
            statement.setString(2, topico.getConteudo());
            statement.setString(3, topico.getUsuario().getLogin());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Topico buscarTopico(int topicoId) {
        Topico topico = null; // Inicializamos como null para indicar que não encontramos o tópico ainda.

        try (Connection conn = DataBaseConection.getConnection()) {
            String sql = "SELECT * FROM topico t JOIN usuario u ON t.login = u.login where t.id_topico = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, topicoId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                topico = new Topico();
                topico.setId_topico(resultSet.getInt("id_topico"));
                topico.setTitulo(resultSet.getString("titulo"));
                topico.setConteudo(resultSet.getString("conteudo"));

                Usuario usuario = new Usuario();
                usuario.setLogin(resultSet.getString("login"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setPontos(resultSet.getInt("pontos"));

                topico.setUsuario(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topico;
    }
}
