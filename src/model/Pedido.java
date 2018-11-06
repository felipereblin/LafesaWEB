package model;

import java.util.List;

public class Pedido {

	private int id;
	private List<Item> itens;
	private double valorTotal;
	
	public Pedido(int id, List<Item> itens, double valorTotal) {
		super();
		this.id = id;
		this.itens = itens;
		this.valorTotal = valorTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
