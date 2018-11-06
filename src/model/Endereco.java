package model;

public class Endereco {

	private int id;
	private String rua;
	private String cidade;  
	private String uf; 
	private String cep;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Endereco(int id, String rua, String cidade, String uf, String cep) {
		super();
		this.id = id;
		this.rua = rua;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
	} 

}
