package Classe;

import java.util.List;

public interface UsuarioDAO {
   
   // Insere um novo usuário no banco de dados
   void inserir(Usuario u);
   
   // Recupera o usuário pelo seu login
   Usuario recuperar(String login);
   
   // Adiciona os pontos para o usuário no banco
   void adicionarPontos(String login, int pontos);
   
   // Retorna a lista de usuários ordenada por pontos (maior primeiro)
   List<Usuario> ranking();
}
