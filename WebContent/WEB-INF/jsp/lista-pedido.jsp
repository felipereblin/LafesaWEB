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
  <a href="controller?action=ActionFormEditProduto">| Novo Pedido - </a>
  <a href="controller?action=ActionListaProduto">Lista Pedido |</a>
  <a href="controller?action=ActionFormEditMarca">| Nova Marca - </a>
  <a href="controller?action=ActionListaMarca">Lista Marca |</a>
  <a href="controller?action=ActionFormEditCliente">| Novo Cliente - </a>
  <a href="controller?action=ActionListaCliente">Lista Cliente |</a>
  <a href="controller?action=ActionFormEditPedido">| Novo Pedido - </a>
  <a href="controller?action=ActionListaPedido">Lista Pedido |</a>

    <c:forEach var="pedido" items="${pedidos}" varStatus="id">
    	<div style="border: 1px solid black">
  			<table>
    			<thead>
			      <tr>
			        <th>Id</th>
			        <th>Valor Total</th>
			        <th>Cliente</th>
			      </tr>
			    </thead>
			  	  <tr bgcolor="#${id.count %2 == 0 ? 'aaee88' : 'ffffff' }">
			  	    <td>${pedido.id}</td>
			  	    <td>R$${pedido.valorTotal}</td>
					<td>${pedido.cliente.nome}</td>
			  	  </tr>
			  	<table>
			  		<thead>
			  			<tr>
			  				<th>Itens / Produtos</th>	
			  			</tr>
			  			<tr>
			        		<th>Id</th>
			        		<th>Nome</th>
			        		<th>Preço Unitário</th>
			        		<th>Quantidade</th>
			      		</tr>
			  		</thead>
			  		<tbody>
			  			<c:forEach var="item" items="${pedido.itens}" varStatus="id">
			  				<tr>
			  					<td>${item.produto.id}</td>
			  					<td>${item.produto.nome}</td>
			  					<td>${item.produto.precoUnitario}</td>
			  					<td>${item.quantidade}</td>
			  				</tr>
			  			</c:forEach>
			  		</tbody>
			  	</table>
			</table>
		</div>
  	</c:forEach>
</body>
</html>