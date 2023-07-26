package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Autenticador {
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Autenticador.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public String autenticar(String login, String senha) throws Exception {
		
		try (Connection c = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/coursera","postgres","")){
				PreparedStatement ps = c.prepareStatement(
				"SELECT nome from usuario WHERE login = ? and senha = ?");
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString("nome");
			}else {
				throw new Exception("Nao foi possivel autenticar");
			}
		
		}
	}
}
