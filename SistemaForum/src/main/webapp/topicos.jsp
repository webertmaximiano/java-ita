<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Topico" %>
<%@ page import="model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tópicos</title>
    <!-- Link para o arquivo CSS do Tailwind CSS -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="flex items-center justify-center h-screen">
        <div class="bg-white p-8 rounded shadow-md w-96">
            <h1 class="text-2xl font-semibold mb-6">Tela de Tópicos</h1>
            <table class="table-auto w-full">
                <thead>
                    <tr>
                        <th class="px-4 py-2">ID</th>
                        <th class="px-4 py-2">Título</th>
                        <th class="px-4 py-2">Usuário</th>
                    </tr>
                </thead>
<tbody>
    <%
    List<Topico> topicos = (List<Topico>) request.getAttribute("topicos");
    if (topicos != null && !topicos.isEmpty()) {
        for (Topico topico : topicos) {
    %>
    <tr>
        <td class="border px-4 py-2">
            <%= topico.getId_topico() %>
        </td>
        <td class="border px-4 py-2">
            <a href="exibeTopico?id=<%= topico.getId_topico() %>">
                <%= topico.getTitulo() %>
            </a>
        </td>
        <td class="border px-4 py-2">
            <%= topico.getUsuario().getNome() %>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3" class="border px-4 py-2">Nenhum tópico encontrado.</td>
    </tr>
    <% } %>
</tbody>


            </table>
            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/ranking" class="text-blue-500 hover:underline">Ir para a Tela Ranking</a>
            </div>
            <div class="mt-2">
                <a href="${pageContext.request.contextPath}/inserirTopico" class="text-blue-500 hover:underline">Ir para a Tela de Inserir Tópico</a>
            </div>
        </div>
    </div>
</body>
</html>
