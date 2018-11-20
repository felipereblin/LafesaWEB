package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Marca;



public class MarcaDao implements Dao<Marca> {
	
	private static final String GET_BY_ID = "SELECT * FROM marca WHERE id = ?";
	private static final String GET_ALL = "SELECT * FROM marca";
	private static final String INSERT = "INSERT INTO marca (nome) "+ "VALUES (?)";
	private static final String UPDATE = "UPDATE marca SET nome = ? where id = ?";
	private static final String DELETE = "DELETE FROM marca WHERE id = ?";
	
	public MarcaDao() {
		try {
			createTable();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar tabela no banco.", e);
			//e.printStackTrace();
		}
	}
	
	private void createTable() throws SQLException {
	    String sqlCreate = "CREATE TABLE IF NOT EXISTS marca"
	            + "  (id           INTEGER,"
	            + "   nome            VARCHAR(50),"
	            + "   PRIMARY KEY (id))";
	    
	    Connection conn = DbConnection.getConnection();


	    Statement stmt = conn.createStatement();
	    stmt.execute(sqlCreate);
	    
	    close(conn, stmt, null);
	}
	
	
	private Marca getMarcaFromRS(ResultSet rs) throws SQLException
    {
		Marca marca = new Marca();
			
		marca.setId( rs.getInt("id") );
		marca.setNome( rs.getString("nome"));
	
		return marca;
    }
	
	@Override
	public Marca getByKey(int id) {
		Connection conn = DbConnection.getConnection();
		
		Marca marca = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(GET_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				marca = getMarcaFromRS(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter marca pela chave.", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return marca;
	}

	@Override
	public List<Marca> getAll() {
		Connection conn = DbConnection.getConnection();
		
		List<Marca> marcas = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(GET_ALL);
			
			while (rs.next()) {
				marcas.add(getMarcaFromRS(rs));
			}			
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter todos os marcas.", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return marcas;
	}

	@Override
	public void insert(Marca marca) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, marca.getNome());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				marca.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir marca.", e);
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
			throw new RuntimeException("Erro ao remover marca.", e);
		} finally {
			close(conn, stmt, null);
		}
	}

	@Override
	public void update(Marca marca) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, marca.getNome());
			stmt.setInt(2, marca.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar marca.", e);
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