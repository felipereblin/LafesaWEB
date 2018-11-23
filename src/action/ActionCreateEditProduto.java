package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarcaDao;
import dao.ProdutoDao;
import model.Marca;
import model.Produto;

public class ActionCreateEditProduto implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Produto produto = new Produto();
		Marca marca = new Marca();
		MarcaDao marcaDao = new MarcaDao();
		
		int marca_id = Integer.parseInt(request.getParameter("marca_id"));

		marca = marcaDao.getByKey(marca_id);
		produto.setNome(request.getParameter("nome"));
		produto.setPrecoUnitario(Double.parseDouble(request.getParameter("precoUnitario")));
		produto.setMarca(marca);
		
		ProdutoDao produtoDao = new ProdutoDao();
		
		if (request.getParameter("id") != "" && request.getParameter("id") != null) {
			String id = request.getParameter("id");
			produto.setId(Integer.parseInt(id));
			produtoDao.update(produto);
			
			request.setAttribute("mensagem", "Produto alterado com sucesso.");
			
		} else {
			produtoDao.insert(produto);
			request.setAttribute("mensagem", "Produto criado com sucesso");
		}
		
		return "controller?action=ActionListaProduto";
	}

}
