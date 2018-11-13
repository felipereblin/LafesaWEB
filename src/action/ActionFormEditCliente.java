package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDao;
import model.Cliente;


public class ActionFormEditCliente implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strId = request.getParameter("id");
		
		Cliente cliente = new Cliente();
		
		if (strId != null) {
			int id = Integer.parseInt(strId);
			ClienteDao dao = new ClienteDao();
			cliente = dao.getByKey(id);
		}
			
		request.setAttribute("cliente", cliente);
		
		return "/WEB-INF/jsp/adiciona-cliente.jsp";
		
		
	}

}
