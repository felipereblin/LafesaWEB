package service;

import dao.ClienteDao;
import dao.EnderecoDao;
import model.Cliente;

public class ClienteService {
	
	ClienteDao clienteDao = new ClienteDao();
	EnderecoDao enderecoDao = new EnderecoDao();
	
	public void saveClienteEndereco(Cliente cliente) {
		clienteDao.insert(cliente);
		enderecoDao.insert(cliente.getEndereco());
		
	}

}
