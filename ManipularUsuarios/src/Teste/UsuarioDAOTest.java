package Teste;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Classe.Usuario;
import Classe.UsuarioDAO;
import Classe.UsuarioDAOImple;

public class UsuarioDAOTest {

    private UsuarioDAO usuarioDAO;

    @Before
    public void setUp() throws Exception {
        usuarioDAO = new UsuarioDAOImple();

        // Configurar a conexão com o banco de dados
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coursera", "postgres",
                "")) {
            // Criar a tabela de usuário para os testes
            String createTableSql = "CREATE TABLE usuario (login TEXT PRIMARY KEY, email TEXT, nome TEXT, senha TEXT, pontos INTEGER)";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableSql);
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        // Remover a tabela de usuário após os testes
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/coursera", "postgres",
                "")) {
            String dropTableSql = "DROP TABLE usuario";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(dropTableSql);
            }
        }
    }

    @Test
    public void testInserir() {
        Usuario usuario = new Usuario();
        usuario.setLogin("user1");
        usuario.setNome("Usuário 1");
        usuario.setEmail("user1@example.com");
        usuario.setSenha("senha1");
        usuario.setPontos(100);

        usuarioDAO.inserir(usuario);

        // Recuperar o usuário pelo login e verificar se foi inserido corretamente
        Usuario usuarioRecuperado = usuarioDAO.recuperar("user1");
        assertNotNull(usuarioRecuperado);
        assertEquals("user1", usuarioRecuperado.getLogin());
        assertEquals("Usuário 1", usuarioRecuperado.getNome());
        assertEquals("user1@example.com", usuarioRecuperado.getEmail());
        assertEquals("senha1", usuarioRecuperado.getSenha());
        assertEquals(100, usuarioRecuperado.getPontos());
    }

    @Test
    public void testRecuperar() {
        // Inserir um usuário para teste
        Usuario usuario = new Usuario();
        usuario.setLogin("user2");
        usuario.setNome("Usuário 2");
        usuario.setEmail("user2@example.com");
        usuario.setSenha("senha2");
        usuario.setPontos(200);

        usuarioDAO.inserir(usuario);

        // Recuperar o usuário pelo login e verificar se os dados estão corretos
        Usuario usuarioRecuperado = usuarioDAO.recuperar("user2");
        assertNotNull(usuarioRecuperado);
        assertEquals("user2", usuarioRecuperado.getLogin());
        assertEquals("Usuário 2", usuarioRecuperado.getNome());
        assertEquals("user2@example.com", usuarioRecuperado.getEmail());
        assertEquals("senha2", usuarioRecuperado.getSenha());
        assertEquals(200, usuarioRecuperado.getPontos());

        // Tentar recuperar um usuário que não existe (deve retornar null)
        Usuario usuarioNaoEncontrado = usuarioDAO.recuperar("user3");
        assertNull(usuarioNaoEncontrado);
    }

    @Test
    public void testAdicionarPontos() {
        // Inserir um usuário para teste
        Usuario usuario = new Usuario();
        usuario.setLogin("user4");
        usuario.setNome("Usuário 4");
        usuario.setEmail("user4@example.com");
        usuario.setSenha("senha4");
        usuario.setPontos(400);

        usuarioDAO.inserir(usuario);

        // Adicionar pontos ao usuário e verificar se foi atualizado corretamente
        usuarioDAO.adicionarPontos("user4", 100);
        Usuario usuarioAtualizado = usuarioDAO.recuperar("user4");
        assertNotNull(usuarioAtualizado);
        assertEquals(500, usuarioAtualizado.getPontos());
    }

    @Test
    public void testRanking() {
        // Inserir mais usuários para teste
        for (int i = 1; i <= 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setLogin("user" + i);
            usuario.setNome("Usuário " + i);
            usuario.setEmail("user" + i + "@example.com");
            usuario.setSenha("senha" + i);
            usuario.setPontos(i * 10);
            usuarioDAO.inserir(usuario);
        }

        // Verificar se o ranking retorna os usuários ordenados por pontos (maior primeiro)
        List<Usuario> ranking = usuarioDAO.ranking();
        assertEquals(10, ranking.size()); // Inserimos 12 usuários (2 do dataset + 10 adicionais)
        for (int i = 0; i < ranking.size() - 1; i++) {
            int pontosAtual = ranking.get(i).getPontos();
            int pontosProximo = ranking.get(i + 1).getPontos();
            assertTrue(pontosAtual >= pontosProximo);
        }
    }
}
