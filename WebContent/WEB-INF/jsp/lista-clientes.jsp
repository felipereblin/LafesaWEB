<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Clientes</title>
</head>
<body>
  <h2>${mensagem}</h2>
  <a href="controller?action=ActionFormEditCliente">Novo Cliente</a>
  <table>
    <thead>
      <tr>
        <th>Id</th>
        <th>Nome</th>
        <th>CPF</th>
        <th>Rua</th>
        <th>Cidade</th>
        <th>UF</th>
        <th>CEP</th>
      </tr>
    </thead>
    <c:forEach var="cliente" items="${clientes}" varStatus="id">
  	  <tr bgcolor="#${id.count %2 == 0 ? 'aaee88' : 'ffffff' }">
  	    <td>${cliente.id}</td>
  	    <td>${cliente.nome}</td>
		<td>${cliente.cpf}</td>
	    <td>${cliente.endereco.rua}</td>
	    <td>${cliente.endereco.cidade}</td>
	    <td>${cliente.endereco.uf}</td>
	    <td>${cliente.endereco.cep}</td>
	    
	    <td><a href="controller?action=ActionFormEditContato&id=${cliente.id}">Editar</a></td>
	    <td><a href="controller?action=ActionRemoveContato&id=${cliente.id}">Remover</a></td>
  	  </tr>
  	</c:forEach>
  </table>
</body>
</html>