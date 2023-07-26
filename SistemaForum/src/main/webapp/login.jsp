<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SistemaForum - Login</title>
    <!-- Link para o arquivo CSS do Tailwind CSS -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="flex items-center justify-center h-screen">
        <div class="bg-white p-8 rounded shadow-md w-96">
            <h1 class="text-2xl font-semibold mb-6">SistemaForum - Login</h1>
            <%-- Verifica se existe uma mensagem de erro no escopo da requisição --%>
            <% String erro = (String) request.getAttribute("erro"); %>
            <% if (erro != null && !erro.isEmpty()) { %>
                <p class="text-red-500 mb-4"><%= erro %></p>
            <% } %>
            <form method="post" action="${pageContext.request.contextPath}/login">
                <div class="mb-4">
                    <label for="login" class="block text-gray-700">Login:</label>
                    <input type="text" id="login" name="login" required class="border border-gray-300 px-2 py-1 rounded w-full focus:outline-none focus:ring focus:border-blue-300">
                </div>
                <div class="mb-6">
                    <label for="senha" class="block text-gray-700">Senha:</label>
                    <input type="password" id="senha" name="senha" required class="border border-gray-300 px-2 py-1 rounded w-full focus:outline-none focus:ring focus:border-blue-300">
                </div>
                <div>
                    <input type="submit" value="Entrar" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-300">
                </div>
            </form>
            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/cadastro" class="text-blue-500 hover:underline">Realize seu Cadastro</a>
            </div>
        </div>
    </div>
</body>
</html>
