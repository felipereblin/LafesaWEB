package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProdutoDao;

public class ActionRemovePedido implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		
		ProdutoDao dao = new ProdutoDao();
		dao.delete(id);
		
		System.out.println("Excluindo produto ... ");
		
		request.setAttribute("mensagem", "Produto removido com sucesso.");
		
		return "controller?action=ActionListaProduto";
	}

}
