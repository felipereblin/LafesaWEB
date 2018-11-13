package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Item;
import model.Pedido;


public class ItemDao implements Dao<Item>{
	
	private static final String GET_BY_ID = "SELECT * FROM item NATURAL JOIN cliente WHERE id = ?";
	private static final String GET_ALL = "SELECT * FROM item NATURAL JOIN cliente";
	private static final String INSERT = "INSERT INTO item (pedido_id, produto_id, quantidade) "	+ "VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE item SET pedido_id = ?, produto_id = ?, quantidade = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM item WHERE id = ?";
	
	public ItemDao() {
		try {
			createTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createTable() throws SQLException {
	    final String sqlCreate = "CREATE TABLE IF NOT EXISTS item"
	            + "  (id           INTEGER,"
	            + "   pedido_id      INTEGER,"
	            + "   produto_id   INTEGER,"
	            + "   quantidade	   INTEGER,"
	            + "   FOREIGN KEY (pedido_id) REFERENCES pedido(id),"
	            + "   FOREIGN KEY (produto_id) REFERENCES produto(id),"
	            + "   PRIMARY KEY (id))";
	    
	    Connection conn = DbConnection.getConnection();

	    Statement stmt = conn.createStatement();
	    stmt.execute(sqlCreate);
	}
	
	
	private Item getItemFromRS(ResultSet rs) throws SQLException
    {
		Item item = new Item();
			
		item.setId( rs.getInt("id") );
		item.setCliente( new Pedido(rs.getArray(columnIndex)) );
		item.setId( rs.getInt("quantidade") );
	
		return item;
    }
	
	@Override
	public Item getByKey(int id) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Item item = null;
		
		try {
			stmt = conn.prepareStatement(GET_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				item = getItemFromRS(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		
		return item;
	}

	@Override
	public List<Item> getAll() {
		Connection conn = DbConnection.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Item> item = new ArrayList<>();
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(GET_ALL);
			
			while (rs.next()) {
				item.add(getItemFromRS(rs));
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		
		return item;
	}

	@Override
	public void insert(Item item) {
		Connection conn = DbConnection.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, item.getPedido().getId());
			stmt.setInt(2, item.getProduto().getId());
			stmt.setInt(3, item.getQuantidade());

			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				item.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		} finally {
			close(conn, stmt, null);
		}
	}

	@Override
	public void update(Item item) {
		Connection conn = DbConnection.getConnection();
		
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(UPDATE);
			stmt.setInt(1, item.getPedido().getId());
			stmt.setInt(2, item.getProduto().getId());
			stmt.setInt(3, item.getQuantidade());
			stmt.setInt(4, item.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close (conn, stmt, null);
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