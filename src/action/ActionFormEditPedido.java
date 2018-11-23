package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDao;
import dao.PedidoDao;
import model.Pedido;


public class ActionFormEditPedido implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strId = request.getParameter("id");
		
		Pedido pedido = new Pedido();
		ClienteDao clienteDao = new ClienteDao();
		
		if (strId != null) {
			int id = Integer.parseInt(strId);
			PedidoDao dao = new PedidoDao();
			pedido = dao.getByKey(id);
		}
		
		request.setAttribute("clientes", clienteDao.getAll());
		request.setAttribute("pedido", pedido);
		
		return "/WEB-INF/jsp/adiciona-pedido.jsp";
		
		
	}

}
