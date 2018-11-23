<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Edita Pedidos</title>
    <link href="css/jquery-ui.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/jquery-ui.js"></script>
  </head>
  <body>
    <h1>Adiciona Pedidos</h1>
    <hr/>
    <form action="controller?action=ActionCreateEditCliente" method="POST">
    	<%-- Nome: <input type="text" name="nome" value="${cliente.nome}"><br>
    	CPF: <input type="text" name="cpf" value="${cliente.cpf}"><br>
    	Rua: <input type="text" name="rua" value="${cliente.endereco.rua}"><br>
    	Cidade: <input type="text" name="cidade" value="${cliente.endereco.cidade}"><br>
    	UF: <input type="text" name="uf" value="${cliente.endereco.uf}"><br>
    	CEP: <input type="text" name="cep" value="${cliente.endereco.cep}"><br> --%>
    	
    	<c:forEach var="cliente" items="${clientes}" varStatus="id">
    	<h4>${cliente.nome}</h4>
    	</c:forEach>
    	
    	<%-- <c:if test="${ cliente.id != 0 }">
    		<input type="hidden" name="id" value="${cliente.id}">
    		<input type="hidden" name="endereco_id" value="${cliente.endereco.id}">
    	</c:if> --%>
    	<input type="submit" value="Gravar">
    </form>
  </body>
</html>