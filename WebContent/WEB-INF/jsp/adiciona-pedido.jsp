<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Edita Pedido</title>
    <link href="css/jquery-ui.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/custom.js"></script>
  </head>
  <body>
    <h1>Adiciona Pedido</h1>
    <hr/>
    <form action="controller?action=ActionCreateEditPedido" method="POST">
    	Cliente: <select id="list_cliente" onchange="changeSelectCliente()">
    		<option value="" disabled></option>
    		<c:forEach var="cliente" items="${clientes}" varStatus="id">
				<option value="${cliente.id}">${cliente.nome}</option></h4>
			</c:forEach>
    	</select>
    	<br /><br />
    	Produtos:<br />
    	<c:forEach var="produto" items="${produtos}" varStatus="id">
    	 	<input type="number" min="1" class="productClass" data-product="${produto.id}" style="width: 35px; margin-right: 10px">${produto.id} - ${produto.nome} - ${produto.marca.nome} - ${produto.precoUnitario} <br/>
    	 </c:forEach>
    	 <br />

		<input type="hidden" name="cliente_id" id="cliente_id" value="${selectedCliente}">
		<input type="hidden" name="selectedProducts" id="selectedProducts">
    	<c:if test="${ pedido.id != 0 }">
    		<input type="hidden" name="id" value="${pedido.id}">
    		
    	</c:if>
    	<br />
    	<input type="submit" value="Gravar" onclick="sendProducts()">
    </form>
  </body>
</html>