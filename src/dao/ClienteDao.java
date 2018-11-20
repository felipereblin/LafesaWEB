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

	public class ClienteDao implements Dao<Cliente> {
		
		private static final String GET_BY_ID = "SELECT * FROM cliente c join endereco e on c.endereco_id = e.id WHERE c.id = ?";
		private static final String GET_ALL = "SELECT * FROM cliente c join endereco e on c.endereco_id = e.id";
		private static final String INSERT = "INSERT INTO cliente (nome, cpf, endereco_id) "
				+ "VALUES (?, ?, ?)";
		private static final String UPDATE = "UPDATE cliente SET nome = ?, cpf = ? "
				+ " WHERE id = ?";
		private static final String DELETE = "DELETE FROM cliente WHERE id = ?";
		
		public ClienteDao() {
			try {
				createTable();
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao criar tabela no banco.", e);
				//e.printStackTrace();
			}
		}
		
		private void createTable() throws SQLException {
		    String sqlCreate = "CREATE TABLE IF NOT EXISTS cliente"
		            + "  (id           INTEGER,"
		            + "   nome            VARCHAR(50),"
		            + "   cpf			  BIGINT,"
		            + "   endereco_id        INTEGER,"
		            + "   FOREIGN KEY (endereco_id) REFERENCES endereco(id),"
		            + "   PRIMARY KEY (id))";
		    
		    Connection conn = DbConnection.getConnection();

		    Statement stmt = conn.createStatement();
		    stmt.execute(sqlCreate);
		    
		    close(conn, stmt, null);
		}
		
		
		private Cliente getClienteFromRS(ResultSet rs) throws SQLException
	    {
			Cliente cliente = new Cliente();
				
			cliente.setId( rs.getInt("id") );
			cliente.setNome( rs.getString("nome") );
			cliente.setCpf( rs.getLong("cpf") );
			cliente.setEndereco(new Endereco(rs.getInt("endereco_id"), rs.getString("rua"), rs.getString("cidade"), 
					rs.getString("uf"), rs.getString("cep")));
			return cliente;
	    }
		
		@Override
		public Cliente getByKey(int id) {
			Connection conn = DbConnection.getConnection();
			
			Cliente cliente = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = conn.prepareStatement(GET_BY_ID);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				if (rs.next()) {
					cliente = getClienteFromRS(rs);
				}
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao obter cliente pela chave.", e);
			} finally {
				close(conn, stmt, rs);
			}
			
			return cliente;
		}

		@Override
		public List<Cliente> getAll() {
			Connection conn = DbConnection.getConnection();
			
			List<Cliente> clientes = new ArrayList<>();
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(GET_ALL);
				
				while (rs.next()) {
					clientes.add(getClienteFromRS(rs));
				}			
				
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao obter todos os clientes.", e);
			} finally {
				close(conn, stmt, rs);
			}
			
			return clientes;
		}

		@Override
		public void insert(Cliente cliente) {
			Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, cliente.getNome());
				stmt.setLong(2, cliente.getCpf());
				stmt.setInt(3, cliente.getEndereco().getId());
				
				stmt.executeUpdate();
				rs = stmt.getGeneratedKeys();
				
				if (rs.next()) {
					cliente.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao inserir cliente.", e);
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
				throw new RuntimeException("Erro ao remover cliente.", e);
			} finally {
				close(conn, stmt, null);
			}
		}

		@Override
		public void update(Cliente cliente) {
			Connection conn = DbConnection.getConnection();
			PreparedStatement stmt = null;
			
			try {
				stmt = conn.prepareStatement(UPDATE);
				stmt.setString(1, cliente.getNome());
				stmt.setLong(2, cliente.getCpf());
				//stmt.setInt(3, cliente.getEndereco().getId());
				stmt.setInt(3, cliente.getId());
				
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao atualizar cliente.", e);
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

