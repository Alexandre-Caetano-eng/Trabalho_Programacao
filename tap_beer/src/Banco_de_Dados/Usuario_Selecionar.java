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
				sql ="SELECT saldo FROM usuario_cartao WHERE cpf LIKE "+cpf;
				comando = BancoDeDados.conexao.prepareStatement(sql);
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
				sql ="SELECT * FROM `usuario_cartao` WHERE `cpf` LIKE "+cpf;
				comando = BancoDeDados.conexao.prepareStatement(sql);
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
	
	public String[][] selecionaUsuarios() {
		String[][] retorno=new String[200][3];
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM `usuario_cartao`";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				int i=0;
				while(resultado.next()) {
					retorno[i][0]=resultado.getString("cpf");
					retorno[i][1]=resultado.getString("nome");
					retorno[i][2]=String.valueOf(resultado.getDouble("saldo"));
					i++;
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
