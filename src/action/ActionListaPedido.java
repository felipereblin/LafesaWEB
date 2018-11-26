package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pedido;
import service.PedidoService;

public class ActionListaPedido implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PedidoService pedidoService = new PedidoService();
		List<Pedido> pedidos = pedidoService.getAllPedidos();
		
		request.setAttribute("pedidos", pedidos);
		
		return "/WEB-INF/jsp/lista-pedido.jsp";
	}

}
