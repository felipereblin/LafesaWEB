package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Produto;
import service.ProdutoService;

public class ActionListaProduto implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProdutoService produtoService = new ProdutoService();
		List<Produto> produtos = produtoService.getAllProducts();
		
		request.setAttribute("produtos", produtos);
		
		return "/WEB-INF/jsp/lista-produto.jsp";
	}

}
