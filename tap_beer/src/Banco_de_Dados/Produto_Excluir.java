package Banco_de_Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Produto_Excluir {
	
	public boolean excluirProduto(int id_Produto) {
		boolean retorno=false;
		PreparedStatement comando = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="DELETE FROM `produto` WHERE id_Produto=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setInt(1, id_Produto);
				if(comando.executeUpdate()>0) {
					retorno=true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				
			}
		}
		return retorno;
	}
	
	public Produto_Excluir() {
		
	}
}
