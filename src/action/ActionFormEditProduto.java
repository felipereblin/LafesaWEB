package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarcaDao;
import dao.ProdutoDao;
import model.Produto;
import service.ProdutoService;


public class ActionFormEditProduto implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strId = request.getParameter("id");
		
		Produto produto = new Produto();
		
		if (strId != null) {
			int id = Integer.parseInt(strId);
			ProdutoService productService = new ProdutoService();
			produto = productService.getProductByKey(id);
			request.setAttribute("selectedMarca", produto.getMarca().getId());
		}
		
		MarcaDao marcaDao = new MarcaDao();
		request.setAttribute("marcas", marcaDao.getAll());
		request.setAttribute("produto", produto);
		
		return "/WEB-INF/jsp/adiciona-produto.jsp";
		
		
	}

}
