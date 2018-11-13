package action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDao;
import model.Cliente;

public class ActionCreateEditCliente implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Cliente cliente = new Cliente();
		cliente.setNome(request.getParameter("nome"));
		cliente.setCpf(Long.parseLong(request.getParameter("cpf")));
		
		ClienteDao dao = new ClienteDao();
		
		
		if (request.getParameter("id") != "" && request.getParameter("id") != null) {
			String id = request.getParameter("id");
			cliente.setId(Integer.parseInt(id));
			dao.update(cliente);
			
			request.setAttribute("mensagem", "Cliente alterado com sucesso.");
			
		} else {
			dao.insert(cliente);
			request.setAttribute("mensagem", "Cliente criado com sucesso");
		}
		
		return "controller?action=ActionListaCliente";
	}

}
