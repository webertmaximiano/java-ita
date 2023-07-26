<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %> <!-- Importação necessária para List -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tela de Ranking</title>
    <!-- Link para o arquivo CSS do Tailwind CSS -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto">
        <!-- ... restante do código ... -->
        <table class="w-full table-auto">
            <thead>
                <tr>
                    <th class="px-4 py-2 bg-blue-500 text-white">#</th>
                    <th class="px-4 py-2 bg-blue-500 text-white">Nome</th>
                    <th class="px-4 py-2 bg-blue-500 text-white">Login</th>
                    <th class="px-4 py-2 bg-blue-500 text-white">Pontos</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<model.Usuario> ranking = (List<model.Usuario>) request.getAttribute("ranking");
                if (ranking != null && !ranking.isEmpty()) {
                    for (int i = 0; i < ranking.size(); i++) {
                        model.Usuario usuario = ranking.get(i);
                %>
                <tr>
                    <td class="px-4 py-2"><%= i + 1 %></td>
                    <td class="px-4 py-2"><%= usuario.getNome() %></td>
                    <td class="px-4 py-2"><%= usuario.getLogin() %></td>
                    <td class="px-4 py-2"><%= usuario.getPontos() %></td>
                </tr>
                <% 
                    }
                } else {
                %>
                <tr>
                    <td colspan="4" class="px-4 py-2">Nenhum usuário encontrado</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <!-- Botão para voltar à página de tópicos -->
		<div class="mt-4">
		    <a href="${pageContext.request.contextPath}/topicos" class="text-blue-500 hover:underline">Voltar para Tela Tópicos</a>
		</div>
    </div>
</body>
</html>
