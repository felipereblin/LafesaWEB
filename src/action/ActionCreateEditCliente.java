package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Endereco;
import service.ClienteService;

public class ActionCreateEditCliente implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();
		cliente.setNome(request.getParameter("nome"));
		cliente.setCpf(Long.parseLong(request.getParameter("cpf")));
		endereco.setCep(request.getParameter("cep"));
		endereco.setCidade(request.getParameter("cidade"));
		endereco.setRua(request.getParameter("rua"));
		endereco.setUf(request.getParameter("uf"));
		cliente.setEndereco(endereco);
		
		ClienteService clienteService = new ClienteService();
		
		if (request.getParameter("id") != "" && request.getParameter("id") != null) {
			String id = request.getParameter("id");
			String endereco_id = request.getParameter("endereco_id");
			cliente.setId(Integer.parseInt(id));
			cliente.getEndereco().setId(Integer.parseInt(endereco_id));
			clienteService.updateClienteEndereco(cliente);
			
			request.setAttribute("mensagem", "Cliente alterado com sucesso.");
			
		} else {
			clienteService.saveClienteEndereco(cliente);
			request.setAttribute("mensagem", "Cliente criado com sucesso");
		}
		
		return "controller?action=ActionListaCliente";
	}

}
