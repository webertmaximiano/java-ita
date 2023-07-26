<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistema de Login</title>
</head>
<body>
	<h1>Sistema de Login</h1>
	<form method="post" action="/login">
		Login: <input type="text" name="login" />
		Senha: <input type="password" name="senha" />
		<input type="submit" value="Entrar">
	</form>
</body>
</html>
