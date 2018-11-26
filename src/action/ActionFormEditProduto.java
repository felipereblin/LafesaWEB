package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MarcaDao;
import model.Marca;
import model.Produto;
import service.ProdutoService;


public class ActionFormEditProduto implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strId = request.getParameter("id");
		
		Produto produto = new Produto();
		MarcaDao marcaDao = new MarcaDao();
		List<Marca> marcas = marcaDao.getAll();

		if (strId != null) {
			int id = Integer.parseInt(strId);
			ProdutoService productService = new ProdutoService();
			produto = productService.getProductByKey(id);
			request.setAttribute("selectedMarca", produto.getMarca().getId());
		}else {
			if(marcas.get(0) != null) {
				request.setAttribute("selectedMarca", marcas.get(0).getId());
			}
		}
		
		request.setAttribute("marcas", marcas);
		request.setAttribute("produto", produto);
		
		return "/WEB-INF/jsp/adiciona-produto.jsp";
		
		
	}

}
