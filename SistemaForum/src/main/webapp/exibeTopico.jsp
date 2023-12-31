<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Comentario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exibe Tópico</title>
</head>
<body>
    <h1><%= request.getAttribute("topicoTitulo") %></h1>
    <p>Autor: <%= request.getAttribute("topicoAutor") %></p>
    <p><%= request.getAttribute("topicoTexto") %></p>
    <h2>Comentários</h2>
    <ul>
        <% List<Comentario> comentarios = (List<Comentario>) request.getAttribute("comentarios"); %>
        <% for (Comentario comentario : comentarios) { %>
            <li>
                <p><%= comentario.getUsuario().getNome() %></p>
                <p><%= comentario.getComentario() %></p>
            </li>
        <% } %>
    </ul>
    <form method="post" action="${pageContext.request.contextPath}/comentario">
	    <input type="hidden" name="topicoId" value="<%= request.getAttribute("topicoId") %>">
	    <textarea name="comentario"></textarea>
	    <input type="submit" value="Adicionar Comentário">
	</form>
    <br>
    <a href="${pageContext.request.contextPath}/topicos" class="text-blue-500 hover:underline">Voltar para Tela Tópicos</a>
</body>
</html>
