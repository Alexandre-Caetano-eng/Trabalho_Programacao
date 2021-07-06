package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transacao_Selecionar {
	
	public String[][] verificaTransacao(int id_Transacao) {
		String[][] retorno = new String[1][6];
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM transacao WHERE id_Transacao="+id_Transacao;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno[0][0]=String.valueOf(resultado.getInt("id_Transacao"));
					retorno[0][1]=resultado.getString("cpf_Usuario");
					retorno[0][2]=resultado.getString("nome_Produto");
					retorno[0][3]=String.valueOf(resultado.getDouble("quant_Prod"));
					retorno[0][4]=String.valueOf(resultado.getDouble("valor_Pago"));
					retorno[0][5]=String.valueOf(resultado.getDate("data_Transacao"));
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
	
	public String[][] verificaTransacaoTodas() {
		String[][] retorno = new String[200][6];
		int i=0;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM transacao ";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					retorno[i][0]=String.valueOf(resultado.getInt("id_Transacao"));
					retorno[i][1]=resultado.getString("cpf_Usuario");
					retorno[i][2]=resultado.getString("nome_Produto");
					retorno[i][3]=String.valueOf(resultado.getDouble("quant_Prod"));
					retorno[i][4]=String.valueOf(resultado.getDouble("valor_Pago"));
					retorno[i][5]=String.valueOf(resultado.getDate("data_Transacao"));
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
	
	public String[][] verificaTransacaoUsuario(String cpf) {
		String[][] retorno = new String[200][6];
		int i=0;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM transacao WHERE cpf_Usuario="+cpf;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					retorno[i][0]=String.valueOf(resultado.getInt("id_Transacao"));
					retorno[i][1]=resultado.getString("cpf_Usuario");
					retorno[i][2]=resultado.getString("nome_Produto");
					retorno[i][3]=String.valueOf(resultado.getDouble("quant_Prod"));
					retorno[i][4]=String.valueOf(resultado.getDouble("valor_Pago"));
					retorno[i][5]=String.valueOf(resultado.getDate("data_Transacao"));
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
	
	public Transacao_Selecionar() {
		
	}
}
