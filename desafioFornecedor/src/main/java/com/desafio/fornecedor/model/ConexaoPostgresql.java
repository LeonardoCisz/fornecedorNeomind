package com.desafio.fornecedor.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoPostgresql implements ConexaoJDBC {

	private Connection connection = null;
	private String jdbcURL = "jdbc:postgresql://localhost:5432/fornecedoresDB";
	private String username = "postgres";
	private String password = "12345";

	public static void main(String[] args) {

		ConexaoPostgresql sqlConnect = new ConexaoPostgresql();
		sqlConnect.connect();

	}

	public void connect() {
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connected to PostgreSQL");	
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT VERSION ()");
			if(rs.next()) { 
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("Error connecting to PostgreSQL");
			e.printStackTrace();
		}

	}

	@Override
	public Connection getConexao() {
		return this.connection;
	}

	@Override
	public void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				System.out.println("Database error");
			}
		}
	}

	@Override
	public void rollback() {
		try {
			this.connection.rollback();
		} catch (SQLException e) {
			System.out.println("Database error");
		} finally {
			this.close();
		}
	}

	@Override
	public void commit() throws SQLException {
		this.connection.commit();
		this.close();
	}


}