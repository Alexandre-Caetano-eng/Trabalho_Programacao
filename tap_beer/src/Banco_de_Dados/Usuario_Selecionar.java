package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario_Selecionar {
	
	public double saldoUsuario(String cpf) {
		double retorno=0.0;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT saldo FROM usuario_cartao WHERE cpf=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setString(1, cpf);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno=resultado.getDouble("saldo");
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public boolean verificaUsuarioExiste(String cpf) {
		boolean retorno=false;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM usuario_cartao WHERE cpf=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setString(1, cpf);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno=true;
				}
			}
		}catch(SQLException e) {
			return retorno;
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				return retorno;
			}
		}
		return retorno;
	}
	
	public Usuario_Selecionar() {
		
	}
}
