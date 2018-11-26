package model;

public class Cliente {
	private int id;
	private String nome;
	private long cpf; 
	private Endereco endereco; 

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
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Cliente(int id, String nome) {
		this.id = id;
		this.nome = nome;
	} 
	
	public Cliente(int id) {
		this.id = id;
	} 
	
	public Cliente() {};
}
