package Banco_de_Dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClasseConexao {
	private static Connection conexao=null;
	//M�todo para fazer a conex�o mysql
	public static Connection Conectar(String url, String user, String password) {
		try {
			//Verifica a conex�o
			if(conexao==null) {
				conexao = DriverManager.getConnection(url,user,password);
			}
		}catch(SQLException e) {
			System.out.println("Conex�o n�o estabelecida.");
		}
		return conexao;
	}
	public static void FecharConexao(Connection c) {
		try {
			if(c!=null) {
				c.close();
			}
		}catch(SQLException e) {
			System.out.println("Erro ao fechar.");
		}
	}
}
