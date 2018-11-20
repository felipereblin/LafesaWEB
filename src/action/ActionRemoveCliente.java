package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDao;
import model.Cliente;

public class ActionRemoveCliente implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		
		ClienteDao dao = new ClienteDao();
		dao.delete(id);
		
		System.out.println("Excluindo contato ... ");
		
		request.setAttribute("mensagem", "Contato removido com sucesso.");
		
		return "controller?action=ActionListaCliente";
	}

}
