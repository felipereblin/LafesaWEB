package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Endereco;
import model.Pedido;


	public class PedidoDao implements Dao<Pedido> {
		
		private static final String GET_BY_ID = "SELECT * FROM pedido WHERE id = ?";
		private static final String GET_ALL = "SELECT * FROM pedido";
		private static final String INSERT = "INSERT INTO pedido (valorTotal, cliente_id) VALUES (?, ?)";
		private static final String UPDATE = "UPDATE pedido SET valorTotal = ? WHERE id = ?";
		private static final String DELETE = "DELETE FROM pedido WHERE id = ?";
		
		public PedidoDao() {
			try {
				createTable();
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao criar tabela no banco.", e);
				//e.printStackTrace();
			}
		}
		
		private void createTable() throws SQLException {
		    String sqlCreate = "CREATE TABLE IF NOT EXISTS pedido"
		            + "  (id           INTEGER,"
		            + "   valorTotal   DOUBLE,"
		            + "   cliente_id        INTEGER,"
		            + "   PRIMARY KEY (id))";
		    
		    Connection conn = DbConnection.getConnection();

		    Statement stmt = conn.createStatement();
		    stmt.execute(sqlCreate);
		    
		    close(conn, stmt, null);
		}
		
		
		private Pedido getClienteFromRS(ResultSet rs) throws SQLException
	    {
			Pedido pedido = new Pedido();
				
			pedido.setId( rs.getInt("id") );
			pedido.setValorTotal(rs.getDouble("valorTotal"));
			pedido.setCliente(new Cliente(rs.getInt("cliente_id")));
			return pedido;
	    }
		
		@Override
		public Pedido getByKey(int id) {
			Connection conn = DbConnection.getConnection();
			
			Pedido pedido = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = conn.prepareStatement(GET_BY_ID);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				if (rs.next()) {
					pedido = getClienteFromRS(rs);
				}
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao obter pedido pela chave.", e);
			} finally {
				close(conn, stmt, rs);
			}
			
			return pedido;
		}

		@Override
		public List<Pedido> getAll() {
			Connection conn = DbConnection.getConnection();
			
			List<Pedido> pedidos = new ArrayList<>();
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(GET_ALL);
				
				while (rs.next()) {
					pedidos.add(getClienteFromRS(rs));
				}			
				
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao obter todos os clientes.", e);
			} finally {
				close(conn, stmt, rs);
			}
			
			return pedidos;
		}

		@Override
		public void insert(Pedido pedido) {
			Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				stmt.setDouble(1, pedido.getValorTotal());
				stmt.setInt(2, pedido.getCliente().getId());
				
				stmt.executeUpdate();
				rs = stmt.getGeneratedKeys();
				
				if (rs.next()) {
					pedido.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao inserir pedido.", e);
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
				throw new RuntimeException("Erro ao remover pedido.", e);
			} finally {
				close(conn, stmt, null);
			}
		}

		@Override
		public void update(Pedido pedido) {
			Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = null;
			
			try {
				stmt = conn.prepareStatement(UPDATE);
				stmt.setDouble(1, pedido.getValorTotal());
				stmt.setInt(2, pedido.getId());
				
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao atualizar pedido.", e);
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

