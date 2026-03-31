package com.meuapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

	private static final String URL = "jdbc:postgresql://localhost:5432/TrabalhoJDBC";

	private static final String USER = "SUE_USUARIO_AQUI";
	private static final String PASSWORD = "SUA_SENHA_AQUI";

	public static Connection getConexao() throws SQLException {
		try {
			System.out.println("Conexão feita com sucesso!!");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.err.println("Erro na conexão com o banco de dados!!");
			throw new RuntimeException(e);
		}
	}
}