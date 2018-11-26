package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pedido;
import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;


public class ActionFormEditPedido implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strId = request.getParameter("id");
		
		Pedido pedido = new Pedido();
		
		if (strId != null) {
			int id = Integer.parseInt(strId);
			PedidoService pedidoService = new PedidoService();
			pedido = pedidoService.getPedidoByKey(id);
			request.setAttribute("selectedCliente", pedido.getCliente().getId());
		}
		
		ClienteService clienteService = new ClienteService();
		ProdutoService produtoService = new ProdutoService();
		request.setAttribute("clientes", clienteService.getAllClientes());
		request.setAttribute("produtos", produtoService.getAllProducts());
		request.setAttribute("pedido", pedido);
		
		return "/WEB-INF/jsp/adiciona-pedido.jsp";
		
		
	}

}
