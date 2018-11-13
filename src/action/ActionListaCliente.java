package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDao;
import model.Cliente;

public class ActionListaCliente implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Cliente> clientes = new ClienteDao().getAll();
		
		request.setAttribute("clientes", clientes);
		
		return "/WEB-INF/jsp/lista-clientes.jsp";
	}

}
