package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Produto_Selecionar {
	
	public boolean verificarProdutoExiste(int id_Produto) {
		boolean retorno=false;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT * FROM produto WHERE id_Produto="+id_Produto;
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
	
	public double valorProduto(int id_Produto) {
		double retorno=0.0;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT valor FROM produto WHERE id_Produto="+id_Produto;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno=resultado.getDouble("valor");
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
	
	public String nomeProduto(int id_Produto) {
		String retorno="";
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				sql ="SELECT nome FROM produto WHERE id_Produto="+id_Produto;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					retorno=resultado.getString("nome");
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
	
	public Produto_Selecionar() {
		
	}
}
