package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Endereco;
import model.Marca;
import model.Produto;

public class ProdutoDao implements Dao<Produto> {

	private static final String GET_BY_ID = "SELECT * FROM produto WHERE id = ?";
	private static final String GET_ALL = "SELECT * FROM produto";
	private static final String INSERT = "INSERT INTO produto (nome, marca_id, precoUnitario) " + "VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE produto SET nome = ?, marca_id = ?, "
			+ "precoUnitario = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM produto WHERE id = ?";

	public ProdutoDao() {
		try {
			createTable();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar tabela no banco.", e);
			// e.printStackTrace();
		}
	}

	private void createTable() throws SQLException {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS cliente" 
				+ "  (id           INTEGER,"
				+ "   nome            VARCHAR(255)," 
				+ "   marca_id			  INTEGER,"
				+ "   precoUnitario   	DOUBLE    ," 
				+ "   FOREIGN KEY (marca_id) REFERENCES marca(id),"
				+ "   PRIMARY KEY (id))";

		Connection conn = DbConnection.getConnection();

		Statement stmt = conn.createStatement();
		stmt.execute(sqlCreate);

		close(conn, stmt, null);
	}

	private Produto getProdutoFromRS(ResultSet rs) throws SQLException {
		Produto produto = new Produto();

		produto.setId(rs.getInt("id"));
		produto.setNome(rs.getString("nome"));
		produto.setMarca(new Marca(rs.getInt("marca_id"), rs.getString("nome")));
		return produto;
		
	}

	@Override
	public Produto getByKey(int id) {
		Connection conn = DbConnection.getConnection();

		Produto produto = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(GET_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				produto = getProdutoFromRS(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter o produto pela chave.", e);
		} finally {
			close(conn, stmt, rs);
		}

		return produto;
	}

	@Override
	public List<Produto> getAll() {
		Connection conn = DbConnection.getConnection();

		List<Produto> produtos = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(GET_ALL);

			while (rs.next()) {
				produtos.add(getProdutoFromRS(rs));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter todos os produtos.", e);
		} finally {
			close(conn, stmt, rs);
		}

		return produtos;
	}

	@Override
	public void insert(Produto produto) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getMarca().getId());
			stmt.setDouble(3, produto.getPrecoUnitario());

			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				produto.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir produto.", e);
		} finally {
			close(conn, stmt, rs);
		}

	}

	@Override
	public void delete(int id) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(DELETE);

			stmt.setInt(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover produto.", e);
		} finally {
			close(conn, stmt, null);
		}
	}

	@Override
	public void update(Produto produto) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getMarca().getId());
			stmt.setDouble(3, produto.getPrecoUnitario());
			stmt.setInt(4, produto.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar produto.", e);
		} finally {
			close(conn, stmt, null);
		}
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar recursos.", e);
		}

	}
}
