package dao;

import java.sql.Connection;

import controller.Conexao;

public class BD {

	private static Connection connection = null;


	public static void main(String[] args) {
		try {
			connection = Conexao.getInstancia().abrirConexao();
			// cria o arquivo em resources, como definido em "jdbc:sqlite:resources/clientes.db"

			System.out.println("Base criada com sucesso");

			Conexao.getInstancia().fecharConexao();

		}catch(Exception e) {
			System.out.println(e.getMessage());

			System.exit(0); // Encerrando execução
		}
	}

}
