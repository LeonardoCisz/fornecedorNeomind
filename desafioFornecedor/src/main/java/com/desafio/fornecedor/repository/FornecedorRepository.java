package com.desafio.fornecedor.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafio.fornecedor.entities.Fornecedor;
import com.desafio.fornecedor.model.ConexaoPostgresql;

public class FornecedorRepository {
	private final ConexaoPostgresql conexao;

	public FornecedorRepository() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoPostgresql();
	}

	public List<Fornecedor> listAll() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT id, name, email, comment, cnpj FROM fornecedor ORDER BY name";

		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			
			List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
			
			while(rs.next()) {
				fornecedores.add(parser(rs));
			}
			
			return fornecedores;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int excluir(int id) throws SQLException, ClassNotFoundException {
		int result = 0;
		String sqlQuery = "DELETE FROM Fornecedor WHERE id = ?";

		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			stmt.setInt(1, id);
			result = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return result;
	}

	public Fornecedor select(int id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT id, name, email, comment, cnpj FROM fornecedor WHERE id = ?";
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return parser(rs);
			}
		}catch (SQLException e) {
			throw e;
		}
		return null;
	}
	
	public int inserir(Fornecedor fornec) throws SQLException, ClassNotFoundException {
		int id = 0;
		
		String sqlQuery = "INSERT INTO fornecedor (name, email, comment, cnpj) VALUES (?,?,?,?) RETURNING id";
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			stmt.setString(1, fornec.getName());
			stmt.setString(2, fornec.getEmail());
			stmt.setString(3, fornec.getComment());
			stmt.setString(4, fornec.getCnpj());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("id");
			}
			
			this.conexao.commit();
			
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		
		return id;
		
	}

	public int alterar(Fornecedor fornec) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE fornecedor SET name = ?, email = ?, comment = ?, cnpj = ? WHERE id = ?";
		int result = 0;
		
		try {
			PreparedStatement stmt = this.conexao.getConexao().prepareStatement(sqlQuery);
			stmt.setString(1, fornec.getName());
			stmt.setString(2, fornec.getEmail());
			stmt.setString(3, fornec.getComment());
			stmt.setString(4, fornec.getCnpj());
			stmt.setInt(5, fornec.getId());
			
			result = stmt.executeUpdate();
			
			this.conexao.commit();
			
			
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		
		return result;
	}

	private Fornecedor parser(ResultSet resultSet) throws SQLException {
		Fornecedor fornec = new Fornecedor();

		fornec.setId(resultSet.getInt("id"));
		fornec.setName(resultSet.getString("name"));
		fornec.setEmail(resultSet.getString("email"));
		fornec.setComment(resultSet.getString("comment"));
		fornec.setCnpj(resultSet.getString("cnpj"));

		return fornec;
	}

}
