package service;

import java.util.List;

import dao.ClienteDao;
import dao.ItemDao;
import dao.PedidoDao;
import model.Item;
import model.Pedido;

public class PedidoService {

	PedidoDao pedidoDao = new PedidoDao();
	ItemDao itemDao = new ItemDao();
	ClienteDao clienteDao = new ClienteDao();
	ProdutoService produtoService = new ProdutoService();
	
	public void savePedidoItem(Pedido pedido) {
		pedidoDao.insert(pedido);
		
		for(Item item: pedido.getItens()) {
			itemDao.insert(item);
		}
	}
	
	public Pedido getPedidoByKey(int id) {
		Pedido pedido = pedidoDao.getByKey(id);
		pedido.setCliente(clienteDao.getByKey(pedido.getCliente().getId()));
		
		return pedido;
	}
	
	public List<Pedido> getAllPedidos(){
		List<Pedido> pedidos = pedidoDao.getAll();
		for (Pedido pedido : pedidos) {
			pedido.setCliente(clienteDao.getByKey(pedido.getCliente().getId()));
			List<Item> itens = itemDao.getByPedidoKey(pedido.getId());
			for (Item item : itens) {
				item.setProduto(produtoService.getProductByKey(item.getProduto().getId()));
			}
			pedido.setItens(itens);
		}
		
		return pedidos;
	}
	
}
