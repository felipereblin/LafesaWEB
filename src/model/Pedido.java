package model;

import java.util.List;

public class Pedido {

	private int id;
	private double valorTotal;
	private List<Item> itens;
	
	public Pedido(int id, double valorTotal) {
		this.id = id;
		this.valorTotal = valorTotal;
	}

	public Pedido() {
	}

	public int getId() {
		return id;
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
