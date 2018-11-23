<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Pedidos</title>
</head>
<body>
  <h2>${mensagem}</h2>
  <a href="controller?action=ActionFormEditProduto">| Novo Produto - </a>
  <a href="controller?action=ActionListaProduto">Lista Produto |</a>
  <a href="controller?action=ActionFormEditMarca">| Nova Marca - </a>
  <a href="controller?action=ActionListaMarca">Lista Marca |</a>
  <a href="controller?action=ActionFormEditCliente">| Novo Cliente - </a>
  <a href="controller?action=ActionListaCliente">Lista Marca |</a>
  <a href="controller?action=ActionFormEditPedido">| Novo Pedido - </a>
  <a href="controller?action=ActionListaPedido">Lista Pedido |</a>
  <table>
    <thead>
      <tr>
        <th>Id</th>
        <th>Valor Total</th>
        <th>Nome Cliente</th>
       	<c:forEach var="item" items="${pedidos.itens}" varStatus="id">
	    	<th>Nome Produto</th>
	    </c:forEach>
        
      </tr>
    </thead>
    <c:forEach var="pedido" items="${pedidos}" varStatus="id">
  	  <tr bgcolor="#${id.count %2 == 0 ? 'aaee88' : 'ffffff' }">
  	    <td>${pedido.id}</td>
  	    <td>${pedido.valorTotal}</td>
		<td>${pedido.cliente.nome}</td>
		<c:forEach var="item" items="${pedidos.itens}" varStatus="id">
	    	<td>${item.produto.nome}</td>
	    </c:forEach>
	  <!--  <td><a href="controller?action=ActionFormEditCliente&id=${pedido.id}">Editar</a></td> -->
	    <td><a href="controller?action=ActionRemoveCliente&id=${pedido.id}">Remover</a></td>
  	  </tr>
  	</c:forEach>
  </table>
</body>
</html>