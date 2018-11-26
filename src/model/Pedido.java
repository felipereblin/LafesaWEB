package model;

import java.util.List;

public class Pedido {

	private int id;
	private double valorTotal;
	private Cliente cliente;
	private List<Item> itens;
	
	public Pedido(int id, double valorTotal) {
		this.id = id;
		this.valorTotal = valorTotal;
	}

	public Pedido(int id) {
		this.id = id;
	}
	
	public Pedido() {
	}

	public int getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
}
