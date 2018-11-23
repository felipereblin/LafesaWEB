package service;

import java.util.ArrayList;
import java.util.List;

import dao.MarcaDao;
import dao.ProdutoDao;
import model.Produto;

public class ProdutoService {
	
	ProdutoDao produtoDao = new ProdutoDao();
	MarcaDao marcaDao = new MarcaDao();
	
	public Produto getProductByKey(int id) {
		Produto produto = produtoDao.getByKey(id);
		produto.setMarca(marcaDao.getByKey(produto.getMarca().getId()));
		
		return produto;
	}
	
	public List<Produto> getAllProducts() {
		List<Produto> produtos = new ArrayList<>();
		produtos.addAll(produtoDao.getAll());
		for (Produto produto : produtos) {
			produto.setMarca(marcaDao.getByKey(produto.getMarca().getId()));
		}
		return produtos;
	}

}
