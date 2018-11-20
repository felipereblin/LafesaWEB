package service;

import dao.ClienteDao;
import dao.EnderecoDao;
import model.Cliente;

public class ClienteService {
	
	ClienteDao clienteDao = new ClienteDao();
	EnderecoDao enderecoDao = new EnderecoDao();
	
	public void saveClienteEndereco(Cliente cliente) {
		enderecoDao.insert(cliente.getEndereco());
		clienteDao.insert(cliente);
	}
	
	public void updateClienteEndereco(Cliente cliente) {
		enderecoDao.update(cliente.getEndereco());
		clienteDao.update(cliente);
		
		
	}

}
