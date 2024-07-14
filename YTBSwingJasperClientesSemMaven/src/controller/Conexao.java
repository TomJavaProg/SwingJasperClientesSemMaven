package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Conexao instancia;
	private static String DRIVER = "org.sqlite.JDBC";
	private static String BD = "jdbc:sqlite:resources/clientes.db";
	private static Connection conexao;

	private Conexao() {

	}

	public static Conexao getInstancia() {
		if(instancia == null) {
			instancia = new Conexao();
		}
		return instancia;
	}

	// tom: modificado
	public Connection abrirConexao() {

		// Buscando drive de conexão
		try {
			Class.forName(DRIVER);

		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			System.out.println("Erro! DRIVER JDBC não encontrado: " + e.getMessage());
		}

		// Buscando bando de dados
		try {
			conexao = DriverManager.getConnection(BD);

		} catch (SQLException e) {
			System.out.println("Erro! Banco de Dados não encontrado: " + e.getMessage());
		}

		// desabilitando o auto-commit(padrão jdbc = true)
		try {
			conexao.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println("Erro! Conexão com o banco de dados não realizada: " + e.getMessage());
		}

		return conexao;

	}

	public void fecharConexao() {
		try {
			if(conexao != null && !conexao.isClosed()) {
				conexao.close();
			}

		}catch(SQLException e) {
			System.out.println("Erro! Fechamento de conexão falhou: " + e.getMessage());

		}finally {
			conexao = null;
		}
	}

}
