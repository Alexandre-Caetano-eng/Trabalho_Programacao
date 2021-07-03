package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario_Alterar {
	
	public boolean adicionarSaldo(String cpf, double saldo) {
		//cadastra um produto em uma determinada torneira
		boolean retorno=false;
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="UPDATE `usuario_cartao` SET `saldo`=? WHERE `cpf`=?";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setDouble(1,saldo);
				comando.setString(2,cpf);
				if(comando.executeUpdate()>0) {
					retorno = true;
				}
			}
		}catch(SQLException e) {
			return false;
		}finally{
			try {
				comando.close();
			}catch(SQLException es) {
				return false;
			}
		}
		return retorno;
	}
	
	public boolean alterarNome(String cpf, String nome) {
		//cadastra um produto em uma determinada torneira
		boolean retorno=false;
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="UPDATE `usuario_cartao` SET `nome`=? WHERE `cpf`=?";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setString(1,nome);
				comando.setString(2,cpf);
				if(comando.executeUpdate()>0) {
					retorno = true;
				}
			}
		}catch(SQLException e) {
			return false;
		}finally{
			try {
				comando.close();
			}catch(SQLException es) {
				return false;
			}
		}
		return retorno;
	}
	
	public Usuario_Alterar() {
		
	}
}
