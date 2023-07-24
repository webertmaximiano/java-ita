package Classe;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImple();

        // Inserindo 10 usuários
        for (int i = 1; i <= 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setLogin("user" + i);
            usuario.setNome("Usuário " + i);
            usuario.setEmail("user" + i + "@example.com");
            usuario.setSenha("senha" + i); // senha aleatoria 
            usuario.setPontos(i * 10); // Definindo pontuação 
            usuarioDAO.inserir(usuario);
        }
        
        // Exibindo o ranking dos usuários
        System.out.println("==== RANKING DE USUÁRIOS ====");
        List<Usuario> ranking = usuarioDAO.ranking();
        for (int i = 0; i < ranking.size(); i++) {
            Usuario usuario = ranking.get(i);
            System.out.println((i + 1) + "º Lugar");
            System.out.println("Login: " + usuario.getLogin());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Pontos: " + usuario.getPontos());
            System.out.println();
        }
        
        // Testando o método recuperar(String login)
        System.out.println("==== RECUPERANDO LOGIN USUÁRIOS ====");
        String loginParaBuscar = "user5";
        Usuario usuarioRecuperado = usuarioDAO.recuperar(loginParaBuscar);
        if (usuarioRecuperado != null) {
            System.out.println("Usuário encontrado:");
            System.out.println("Login: " + usuarioRecuperado.getLogin());
            System.out.println("Nome: " + usuarioRecuperado.getNome());
            System.out.println("Email: " + usuarioRecuperado.getEmail());
            System.out.println("Pontos: " + usuarioRecuperado.getPontos());
        } else {
            System.out.println("Usuário não encontrado para o login: " + loginParaBuscar);
        }

        // Testando o método adicionarPontos(String login, int pontos)
        System.out.println("==== ADICIONANDO PONTOS A USUÁRIOS ====");
        String loginParaAdicionarPontos = "user7";
        int pontosParaAdicionar = 500;
        usuarioDAO.adicionarPontos(loginParaAdicionarPontos, pontosParaAdicionar);
        System.out.println("\nAdicionados " + pontosParaAdicionar + " pontos para o usuário com login: " + loginParaAdicionarPontos);

        
    }
    
    
}
