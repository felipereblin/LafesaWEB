package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Endereco;

public class EnderecoDao implements Dao<Endereco> {

	private static final String GET_BY_ID = "SELECT * FROM endereco WHERE id = ?";
	private static final String GET_ALL = "SELECT * FROM endereco";
	private static final String INSERT = "INSERT INTO endereco (rua, cidade, uf, CEP) "
			+ "VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE endereco SET rua = ?, cidade = ?, uf = ?, "
			+ "CEP = ?  WHERE id = ?";
	private static final String DELETE = "DELETE FROM endereco WHERE id = ?";
	
	public EnderecoDao() {
		try {
			createTable();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar tabela no banco.", e);
			//e.printStackTrace();
		}
	}
	
	private void createTable() throws SQLException {
	    String sqlCreate = "CREATE TABLE IF NOT EXISTS endereco"
	            + "  (id           	INTEGER,"
	            + "   rua           VARCHAR(50),"
	            + "   cidade	    VARCHAR(50),"
	            + "   uf		 	VARCHAR(50),"
	            + "   cep           VARCHAR(50),"
	            + "   PRIMARY KEY (id))";
	    
	    Connection conn = DbConnection.getConnection();


	    Statement stmt = conn.createStatement();
	    stmt.execute(sqlCreate);
	    
	    close(conn, stmt, null);
	}
	
	
	private Endereco getEnderecoFromRS(ResultSet rs) throws SQLException
    {
		Endereco endereco = new Endereco();
			
		endereco.setId( rs.getInt("id") );
		endereco.setRua( rs.getString("rua") );
		endereco.setCidade( rs.getString("cidade") );
		endereco.setUf( rs.getString("uf") );
		endereco.setCep( rs.getString("cep") );
	
		return endereco;
    }
	
	@Override
	public Endereco getByKey(int id) {
		Connection conn = DbConnection.getConnection();
		
		Endereco endereco = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(GET_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				endereco = getEnderecoFromRS(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter o endereco pela chave.", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return endereco;
	}

	@Override
	public List<Endereco> getAll() {
		Connection conn = DbConnection.getConnection();
		
		List<Endereco> enderecos = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(GET_ALL);
			
			while (rs.next()) {
				enderecos.add(getEnderecoFromRS(rs));
			}			
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter todos os clientes.", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return enderecos;
	}

	@Override
	public void insert(Endereco endereco) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getCidade());
			stmt.setString(3, endereco.getUf());
			stmt.setString(4, endereco.getCep());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				endereco.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir endereço.", e);
		}finally {
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
			throw new RuntimeException("Erro ao remover endereço.", e);
		} finally {
			close(conn, stmt, null);
		}
	}

	@Override
	public void update(Endereco endereco) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getCidade());
			stmt.setString(3, endereco.getUf());
			stmt.setString(4, endereco.getCep());
			stmt.setInt(5, endereco.getId());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar endereços.", e);
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
