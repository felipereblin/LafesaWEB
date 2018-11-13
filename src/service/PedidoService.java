package service;

import dao.PedidoDao;
import model.Item;
import model.Pedido;

public class PedidoService {

	PedidoDao pedidoDao = new PedidoDao();
	//ItemDao itemDao = new ItemDao();
	
	public void savePedidoItem(Pedido pedido) {
		pedidoDao.insert(pedido);
		
		for(Item item: pedido.getItens()) {
		//	itemDao.insert(item);
		}
	}
	
}
