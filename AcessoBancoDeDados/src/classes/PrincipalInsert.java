package classes;

public class PrincipalInsert {

	public static void main(String[] args) {
		Usuario u = new Usuario();
		u.setLogin("piupiu");
		u.setNome("Everaldo Ramos");
		u.setEmail("everaldo@gmail.com");
		
		UsuarioDAO.inserirUsuario(u);

	}

}
