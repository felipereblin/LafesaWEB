package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Pedido;
import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;


public class ActionFormEditPedido implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strId = request.getParameter("id");
		
		Pedido pedido = new Pedido();
		ClienteService clienteService = new ClienteService();
		List<Cliente> clientes = clienteService.getAllClientes();
		
		if (strId != null) {
			int id = Integer.parseInt(strId);
			PedidoService pedidoService = new PedidoService();
			pedido = pedidoService.getPedidoByKey(id);
			request.setAttribute("selectedCliente", pedido.getCliente().getId());
		}else {
			if(clientes.get(0) != null) {
				request.setAttribute("selectedCliente", clientes.get(0).getId());
			}
		}
		
		
		ProdutoService produtoService = new ProdutoService();
		request.setAttribute("clientes", clientes);
		request.setAttribute("produtos", produtoService.getAllProducts());
		request.setAttribute("pedido", pedido);
		
		return "/WEB-INF/jsp/adiciona-pedido.jsp";
		
		
	}

}
