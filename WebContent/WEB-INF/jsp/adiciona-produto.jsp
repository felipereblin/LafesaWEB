<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Edita Produto</title>
    <link href="css/jquery-ui.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/custom.js"></script>
  </head>
  <body>
    <h1>Adiciona Produtos</h1>
    <hr/>
    <form action="controller?action=ActionCreateEditProduto" method="POST">
    	Nome: <input type="text" name="nome" value="${produto.nome}"><br>
    	Preço Unitário: <input type="text" name="precoUnitario" value="${produto.precoUnitario}"><br>
    	Marca: <select id="list_marca" onchange="changeSelect()">
    		<option value="" disabled></option>
    		<c:forEach var="marca" items="${marcas}" varStatus="id">
				<option value="${marca.id}">${marca.nome}</option></h4>
			</c:forEach>
    	</select>

		<input type="hidden" name="marca_id" id="marca_id" value="${selectedMarca}">
    	<c:if test="${ produto.id != 0 }">
    		<input type="hidden" name="id" value="${produto.id}">
    		
    	</c:if>
    	<input type="submit" value="Gravar">
    </form>
  </body>
</html>