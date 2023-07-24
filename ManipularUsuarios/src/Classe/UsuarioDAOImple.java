package Classe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImple implements UsuarioDAO {
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inserir(Usuario u) {
		try (Connection c = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/coursera","postgres","")){
					
			String sql = "INSERT INTO public.usuario(login, nome, email, senha, pontos) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setString(1, u.getLogin());
			stm.setString(2, u.getNome());
			stm.setString(3, u.getEmail());
			stm.setString(4, u.getSenha());
			stm.setInt(5, u.getPontos());
			stm.executeUpdate();
					
		} catch (SQLException e) {
			throw new RuntimeException("Nao foi possivel se conectar", e);
		}
	}

	@Override
	public Usuario recuperar(String login) {
		try (Connection c = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/coursera","postgres","")){
			
			String sql = "SELECT * FROM public.usuario WHERE login = ?";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setString(1, login);
			ResultSet rs = stm.executeQuery();
			
			if (rs.next()) {
				Usuario u = new Usuario();
				u.setLogin(rs.getString("login"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setPontos(rs.getInt("pontos"));
				return u;
			} else {
				return null;
			}
					
		} catch (SQLException e) {
			throw new RuntimeException("Nao foi possivel se conectar", e);
		}
	}

	@Override
	public void adicionarPontos(String login, int pontos) {
		try (Connection c = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/coursera","postgres","")){
			
			String sql = "UPDATE public.usuario SET pontos = pontos + ? WHERE login = ?";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setInt(1, pontos);
			stm.setString(2, login);
			stm.executeUpdate();
					
		} catch (SQLException e) {
			throw new RuntimeException("Nao foi possivel se conectar", e);
		}
	}

	@Override
	public List<Usuario> ranking() {
		List<Usuario> ranking = new ArrayList<>();
		try (Connection c = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/coursera","postgres","")){
			
			String sql = "SELECT * FROM public.usuario ORDER BY pontos DESC";
			PreparedStatement stm = c.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setLogin(rs.getString("login"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
				u.setPontos(rs.getInt("pontos"));
				ranking.add(u);
			}
					
		} catch (SQLException e) {
			throw new RuntimeException("Nao foi possivel se conectar", e);
		}
		return ranking;
	}
}
