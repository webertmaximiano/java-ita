<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inserir Tópico</title>
    <!-- Link para o arquivo CSS do Tailwind CSS -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto">
        <h1 class="text-2xl font-semibold mb-6">Inserir Tópico</h1>
        <!-- Formulário para inserir um novo tópico -->
        <form method="post" action="${pageContext.request.contextPath}/inserirTopico">
            <div class="mb-4">
                <label for="titulo" class="block text-gray-700">Título:</label>
                <input type="text" id="titulo" name="titulo" required class="border border-gray-300 px-2 py-1 rounded w-full focus:outline-none focus:ring focus:border-blue-300">
            </div>
            <div class="mb-6">
                <label for="conteudo" class="block text-gray-700">Conteúdo:</label>
                <textarea id="conteudo" name="conteudo" required class="border border-gray-300 px-2 py-1 rounded w-full focus:outline-none focus:ring focus:border-blue-300"></textarea>
            </div>
            <div>
                <input type="submit" value="Adicionar Tópico" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-300">
            </div>
        </form>
        <!-- Link para voltar para a Tela Tópicos -->
        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/topicos" class="text-blue-500 hover:underline">Voltar para Tela Tópicos</a>
        </div>
    </div>
</body>
</html>
