package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Item;
import model.Pedido;
import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;

public class ActionCreateEditPedido implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Pedido pedido = new Pedido();
		Cliente cliente = new Cliente();
		ClienteService  clienteService = new ClienteService();
		ProdutoService produtoService = new ProdutoService();
		
		int cliente_id = Integer.parseInt(request.getParameter("cliente_id"));
		
		String selectedProducts = request.getParameter("selectedProducts");
		String[] selectedProductsArray = selectedProducts.split(";");
		String[] selectedProductsArraySplitted = null;
		List<Item> itens = new ArrayList<>();
		
		for (String productItem : selectedProductsArray) {
			selectedProductsArraySplitted = productItem.split("-");
			if(!selectedProductsArraySplitted[0].isEmpty()) {
				Item item = new Item();
				item.setQuantidade(Integer.parseInt(selectedProductsArraySplitted[0]));
				item.setProduto(produtoService.getProductByKey(Integer.parseInt(selectedProductsArraySplitted[1])));
				item.setPedido(pedido);
				itens.add(item);
			}
		}
		
		Double valorTotal = 0.0;
		cliente = clienteService.getClienteById(cliente_id);
		for (Item item: itens) {
			valorTotal = valorTotal + (item.getProduto().getPrecoUnitario() * item.getQuantidade());
		}
		
		pedido.setItens(itens);
		pedido.setValorTotal(valorTotal);
		pedido.setCliente(cliente);
		
		PedidoService pedidoService = new PedidoService();
		
//		if (request.getParameter("id") != "" && request.getParameter("id") != null) {
//			String id = request.getParameter("id");
//			pedido.setId(Integer.parseInt(id));
//			pedidoService.update(pedido);
//			
//			request.setAttribute("mensagem", "Produto alterado com sucesso.");
//			
//		} else {
			pedidoService.savePedidoItem(pedido);
			request.setAttribute("mensagem", "Produto criado com sucesso");
//		}
		
		return "controller?action=ActionListaProduto";
	}

}
