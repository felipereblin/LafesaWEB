package model;

public class Marca {
	
	private int id;
	private String nome;
	

	public Marca(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	
	public Marca() {
		super();
	}
	
	public Marca(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
