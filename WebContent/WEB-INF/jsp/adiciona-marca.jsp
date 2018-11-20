<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Edita Marca</title>
    <link href="css/jquery-ui.css" rel="stylesheet">
    <script src="js/jquery.js"></script>
    <script src="js/jquery-ui.js"></script>
  </head>
  <body>
    <h1>Adiciona Marcas</h1>
    <hr/>
    <form action="controller?action=ActionCreateEditMarca" method="POST">
    	Nome: <input type="text" name="nome" value="${marca.nome}"><br>
    	<c:if test="${ marca.id != 0 }">
    		<input type="hidden" name="id" value="${marca.id}">
    	</c:if>
    	<input type="submit" value="Gravar">
    </form>
  </body>
</html>