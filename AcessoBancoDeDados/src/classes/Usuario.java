package classes;
public class Usuario {
	private String login;
	private String nome;
	private String email;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String usuario) {
		this.login = usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Usuario [login=" + login + ", nome=" + nome + ", email=" + email + "]";
	}
	
}
