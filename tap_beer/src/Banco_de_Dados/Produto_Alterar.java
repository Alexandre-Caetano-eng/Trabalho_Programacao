package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Produto_Alterar {
	
	public boolean alterarProdutoNome(int idP, String nome) {
		boolean retorno=false;
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="UPDATE `produto` SET `nome`=? WHERE `id_Produto`=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setString(1,nome);
				comando.setInt(2,idP);
				if(comando.executeUpdate()>0) {
					retorno=true;
				}
			}
		}catch(SQLException e) {
			
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				
			}
		}
		return retorno;
	}
	
	public boolean alterarProdutoPreco(int idP, double valor) {
		boolean retorno=false;
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="UPDATE `produto` SET `valor`=? WHERE `id_Produto`=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setDouble(1,valor);
				comando.setInt(2,idP);
				if(comando.executeUpdate()>0) {
					retorno=true;
				}
			}
		}catch(SQLException e) {
			
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				
			}
		}
		return retorno;
	}
	
	public Produto_Alterar() {
		
	}
}
