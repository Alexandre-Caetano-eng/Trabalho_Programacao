package Banco_de_Dados;

import java.sql.Connection;

public class BancoDeDados {
	public static Connection conexao=null;
	
	public BancoDeDados(){
		
	}
	
	public void IniciaConexao() {
		String endereco="jdbc:mysql://localhost/tap_beer";
		String usuario="root";
		String senha="vertrigo";
		conexao = ClasseConexao.Conectar(endereco, usuario, senha);
	}
	
	public void EncerraConexao() {
		ClasseConexao.FecharConexao(conexao);
	}
}
